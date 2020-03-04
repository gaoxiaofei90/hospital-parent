package com.llw.hospital.ds.util;

import com.aeye.master.util.JSONUtils;
import com.alibaba.dubbo.rpc.RpcContext;
import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.dto.SysUserDto;

public class RpcUtil {
    public static void setUserToContext(RpcContext rpcContext, SysUserDto user) {
        rpcContext.setAttachment(UpmsConstant.LOGIN_USER_ID, user ==null?null: user.getUserId()+"");
        //所属机构
        if(null != user && null != user.getOrgId()){
            rpcContext.setAttachment(UpmsConstant.LOGIN_USER_ORG_ID,String.valueOf(user.getOrgId()));
        }
        rpcContext.setAttachment(UpmsConstant.LOGIN_USER, user ==null?null: JSONUtils.toJSONString(user));
    }
}
