package com.llw.hospital.bs.common.util;
import org.apache.shiro.SecurityUtils;

import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.ds.entity.LoginUser;

/**
 * 登录相关业务Util
 */
public class LoginUtil {

    /**
     * 获取当前登录的系统用户
     * @return
     */
    public static LoginUser getCurrentUser(){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getSession().getAttribute(UpmsConstant.LOGIN_USER);
            return sysUser;
    }
}
