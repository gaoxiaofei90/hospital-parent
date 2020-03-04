package com.llw.hospital.api.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.llw.hospital.api.shiro.JWTFilter;
import com.llw.hospital.ds.util.RpcUtil;
import com.llw.hospital.dto.SysUserDto;
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
        long start=System.currentTimeMillis();
        try {
            SysUserDto sysUserDto = JWTFilter.currentUser.get();
            if(null != sysUserDto){
                RpcUtil.setUserToContext(RpcContext.getContext(),sysUserDto);
            }
            Result result = invoker.invoke(invocation);
            return result;
        } finally {
            RpcContext.getContext().clearAttachments();
        }
    }
}
