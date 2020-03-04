package com.llw.hospital.api.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.isrsal.logging.ResponseWrapper;
import com.llw.hospital.api.SysOperLogService;
import com.llw.hospital.api.filter.component.ActionE;
import com.llw.hospital.api.filter.component.ActionMap;
import com.llw.hospital.api.filter.entity.ActionLayerLogEntity;
import com.llw.hospital.api.filter.wrapper.BufferedHttpRequestWrapper;
import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.common.util.DatabaseTimeUtil;
import com.llw.hospital.common.util.StringUtils;
import com.llw.hospital.dto.SysOperLogDto;
import com.llw.hospital.dto.SysUserDto;

public class ActionInvokeLog implements Filter {

    private static Logger logger= LoggerFactory.getLogger(ActionInvokeLog.class);
    public static ThreadLocal<String> choosedOrgId = new ThreadLocal<>();

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ActionLayerLogEntity tmpActionLayerLogEntity=new ActionLayerLogEntity();
        Session session=SecurityUtils.getSubject().getSession();
        tmpActionLayerLogEntity.setUserId(getUserId(session));
        tmpActionLayerLogEntity.setUsername(getUsername(session));
        tmpActionLayerLogEntity.setSessionId(getSessionId(session));
        //记录调用时长
        long start=System.currentTimeMillis();
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        boolean isMultipartContent= ServletFileUpload.isMultipartContent(httpServletRequest);
        if(isMultipartContent){
            chain.doFilter(request,response);
            return;
        }
        BufferedHttpRequestWrapper bufferedHttpRequestWrapper=new BufferedHttpRequestWrapper(httpServletRequest);
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        ResponseWrapper responseWrapper=new ResponseWrapper(Thread.currentThread().getId(),httpServletResponse);
        try{
            String choosedOrgIdStr = getChoosedOrg(bufferedHttpRequestWrapper);
            ActionInvokeLog.choosedOrgId.set(choosedOrgIdStr);
            chain.doFilter(bufferedHttpRequestWrapper,responseWrapper);
        }finally {
            ActionInvokeLog.choosedOrgId.remove();
            try{
                ActionLayerLogEntity actionLayerLogEntity=new ActionLayerLogEntity();
                //转换1
                fillIn(bufferedHttpRequestWrapper,responseWrapper,start,tmpActionLayerLogEntity,actionLayerLogEntity);
                SysOperLogDto sysOperLogDto=new SysOperLogDto();
                //转换2
                fillIn(actionLayerLogEntity,sysOperLogDto);
                //保存
                save(request,sysOperLogDto);
                logger.debug(Utils.toJSONStr(actionLayerLogEntity));
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }
        }
    }

    /**
     * 获取用户主动选择的机构id
     * @param request
     * @return
     */
    private String getChoosedOrg(ServletRequest request) {
        String ret = "";
        String choosedOrg = "choosedOrgId";
        ret = (String)request.getAttribute(choosedOrg);
        if(StringUtils.isEmpty(ret)){
            ret = request.getParameter(choosedOrg);
        }
        return ret;
    }

    private void save(ServletRequest request,SysOperLogDto sysOperLogDto) {
        try{
            SysOperLogService sysOperLogService=WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBean(SysOperLogService.class);
            sysOperLogService.insert(sysOperLogDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void fillIn(ActionLayerLogEntity actionLayerLogEntity, SysOperLogDto sysOperLogDto) {
        sysOperLogDto.setUserId(actionLayerLogEntity.getUserId());
        sysOperLogDto.setUsername(actionLayerLogEntity.getUsername());
        sysOperLogDto.setIp(actionLayerLogEntity.getRemoteAddress());
        ActionE actionE= ActionMap.getActionE(actionLayerLogEntity.getUrl());
        sysOperLogDto.setModules(actionE==null?actionLayerLogEntity.getUrl():actionE.getModule());
        sysOperLogDto.setAction(actionE==null?actionLayerLogEntity.getUrl():actionE.getAction());
        sysOperLogDto.setCreateTime(DatabaseTimeUtil.getCurrentTime());
        sysOperLogDto.setOrgId(actionLayerLogEntity.getOrgId());
    }

    private void fillIn(BufferedHttpRequestWrapper bufferedHttpRequestWrapper,ResponseWrapper responseWrapper,Long start,ActionLayerLogEntity tmpActionLayerLogEntity,ActionLayerLogEntity actionLayerLogEntity){
        Session session=SecurityUtils.getSubject().getSession();
        actionLayerLogEntity.url=bufferedHttpRequestWrapper.getRequestURI();
        actionLayerLogEntity.sessionId=getSessionId(session);
        if(actionLayerLogEntity.sessionId==null){
            actionLayerLogEntity.sessionId=tmpActionLayerLogEntity.getSessionId();
        }
        actionLayerLogEntity.userId=getUserId(session);
        if(actionLayerLogEntity.userId==null){
            actionLayerLogEntity.userId=tmpActionLayerLogEntity.getUserId();
        }
        actionLayerLogEntity.username=getUsername(session);
        if(actionLayerLogEntity.username==null){
            actionLayerLogEntity.username=tmpActionLayerLogEntity.getUsername();
        }
/*        if(actionLayerLogEntity.userId!=null){
            actionLayerLogEntity.orgId= UserUtil.getUser(actionLayerLogEntity.userId).getOrgId();
        }*/
        actionLayerLogEntity.sessionObj=toSessionObj(session);
        actionLayerLogEntity.reqParam=getReqParam(bufferedHttpRequestWrapper);
        actionLayerLogEntity.queryString=bufferedHttpRequestWrapper.getQueryString();
        actionLayerLogEntity.respParam=getRespParam(responseWrapper);
        actionLayerLogEntity.castTime=System.currentTimeMillis()-start;
        actionLayerLogEntity.remoteAddress=bufferedHttpRequestWrapper.getRemoteAddr();

    }

    private String getUsername(Session session) {
        if(session==null){
            return null;
        }
        SysUserDto sysUserDto= (SysUserDto) session.getAttribute(UpmsConstant.LOGIN_USER);
        if(sysUserDto!=null){
            return sysUserDto.getUsername();
        }
        return null;
    }

    private String getRespParam(ResponseWrapper responseWrapper) {
        byte[] respParamBytes = responseWrapper.toByteArray();
        if(respParamBytes!=null && respParamBytes.length>0){
            try {
                return new String(respParamBytes,responseWrapper.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return null;
    }

    private String getReqParam(BufferedHttpRequestWrapper bufferedHttpRequestWrapper) {
        byte[] reqParamBytes=bufferedHttpRequestWrapper.getBs();
        if(reqParamBytes!=null && reqParamBytes.length>0){
            return new String(reqParamBytes);
        }
        return null;
    }

    private String toSessionObj(Session session) {
        if(session==null){
            return null;
        }
        JSONObject jo=new JSONObject();
        for (Object key:session.getAttributeKeys()) {
            Object value=session.getAttribute(key);
            jo.put(key.toString(),value);
        }
        return jo.toJSONString();
    }

    private Long getUserId(Session session) {
        if(session==null){
            return null;
        }
        SysUserDto sysUserDto= (SysUserDto) session.getAttribute(UpmsConstant.LOGIN_USER);
        if(sysUserDto!=null){
            return sysUserDto.getUserId();
        }
        return null;
    }

    private String getSessionId(Session session) {
        if(session==null){
            return null;
        }
        return session.getId().toString();
    }

    @Override
    public void destroy() {

    }
}
