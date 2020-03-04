package com.llw.hospital.bs.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.isrsal.logging.ResponseWrapper;
import com.llw.hospital.api.SysOperLogService;
import com.llw.hospital.bs.filter.component.ActionE;
import com.llw.hospital.bs.filter.component.ActionMap;
import com.llw.hospital.bs.filter.entity.ActionLayerLogEntity;
import com.llw.hospital.bs.filter.wrapper.BufferedHttpRequestWrapper;
import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.common.util.DatabaseTimeUtil;
import com.llw.hospital.common.util.StringUtils;
import com.llw.hospital.dto.SysOperLogDto;
import com.llw.hospital.dto.SysUserDto;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ActionInvokeLog implements Filter {

    private static Logger logger= LoggerFactory.getLogger(ActionInvokeLog.class);
    public static ThreadLocal<Map<String,String>> choosedParamters = new ThreadLocal();

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ActionLayerLogEntity tmpActionLayerLogEntity=new ActionLayerLogEntity();
        Session session=SecurityUtils.getSubject().getSession();
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
            Map<String,String> choosed = getChoosedParamter(bufferedHttpRequestWrapper);;
            ActionInvokeLog.choosedParamters.set(choosed);
            chain.doFilter(bufferedHttpRequestWrapper,responseWrapper);
        }finally {
            ActionInvokeLog.choosedParamters.remove();
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

    private Map<String, String> getChoosedParamter(BufferedHttpRequestWrapper bufferedHttpRequestWrapper) {
        Map<String,String> choosedParamMap = new HashMap<>();
        Enumeration<String> parameterNames = bufferedHttpRequestWrapper.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            if(key.startsWith("choosed")){
                String value = bufferedHttpRequestWrapper.getParameter(key);
                if(!StringUtils.isEmpty(value)){
                    choosedParamMap.put(key,value);
                }
            }
        }
        return choosedParamMap;
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
        SysUserDto sysUserDto = getUser(session);
        if(null != sysUserDto){

            actionLayerLogEntity.userId=sysUserDto.getUserId();
            actionLayerLogEntity.username=sysUserDto.getUsername();
            actionLayerLogEntity.orgId= sysUserDto.getOrgId();
        }
        actionLayerLogEntity.sessionObj=toSessionObj(session);
        actionLayerLogEntity.reqParam=getReqParam(bufferedHttpRequestWrapper);
        actionLayerLogEntity.queryString=bufferedHttpRequestWrapper.getQueryString();
        actionLayerLogEntity.respParam=getRespParam(responseWrapper);
        actionLayerLogEntity.castTime=System.currentTimeMillis()-start;
        actionLayerLogEntity.remoteAddress=bufferedHttpRequestWrapper.getRemoteAddr();

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

    private SysUserDto getUser(Session session) {
        if(session==null){
            return null;
        }
        SysUserDto sysUserDto= (SysUserDto) session.getAttribute(UpmsConstant.LOGIN_USER);
        return sysUserDto;
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
