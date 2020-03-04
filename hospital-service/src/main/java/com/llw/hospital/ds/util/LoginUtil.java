package com.llw.hospital.ds.util;

import com.alibaba.dubbo.rpc.RpcContext;
import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.common.util.StringUtils;

public class LoginUtil {
    public static String getCurrentUserId(){
        return RpcContext.getContext().getAttachment(UpmsConstant.LOGIN_USER_ID);
    }

    public static Long getCurrentUserOrgId(){
        String orgId = RpcContext.getContext().getAttachment(UpmsConstant.LOGIN_USER_ORG_ID);
        if(!StringUtils.isEmpty(orgId)){
            return Long.parseLong(orgId);
        }else{
            return null;
        }
    }
}
