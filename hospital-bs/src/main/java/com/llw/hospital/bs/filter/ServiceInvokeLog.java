package com.llw.hospital.bs.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.ds.util.RpcUtil;
import com.llw.hospital.dto.SysUserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;

/**
 * 隐式向dubbo-service端传 userId
 * 记录调用 的service,method,arg,return,耗时，userId,sessionId
 */
@Activate
@Component
public class ServiceInvokeLog implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Session session= SecurityUtils.getSubject().getSession();
        SysUserDto user= (SysUserDto) session.getAttribute(UpmsConstant.LOGIN_USER);
        RpcContext rpcContext=RpcContext.getContext();
        //放入前端传递的数据权限控制相关的数据
        if(null != ActionInvokeLog.choosedParamters.get() && ActionInvokeLog.choosedParamters.get().size() > 0){
            for(String key:ActionInvokeLog.choosedParamters.get().keySet()){
                rpcContext.setAttachment(UpmsConstant.LOGIN_USER+"_"+key,ActionInvokeLog.choosedParamters.get().get(key));
            }
            rpcContext.setAttachments(ActionInvokeLog.choosedParamters.get());
        }
        //放入当前用户的相关数据
        if(null != user){
            RpcUtil.setUserToContext(RpcContext.getContext(),user);
        }
        try {
            //调用dubbo服务
            Result result = invoker.invoke(invocation);
            return result;
        } finally {
            RpcContext.getContext().clearAttachments();
        }
    }

}
