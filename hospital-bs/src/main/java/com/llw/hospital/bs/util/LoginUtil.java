package com.llw.hospital.bs.util;

import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.dto.SysUserDto;

import org.apache.shiro.SecurityUtils;

/**
 * 登录相关业务Util
 */
public class LoginUtil {

    /**
     * 获取当前登录的系统用户
     * @return
     */
    public static SysUserDto getCurrentUser(){
        SysUserDto sysUser = (SysUserDto) SecurityUtils.getSubject().getSession().getAttribute(UpmsConstant.LOGIN_USER);
        return sysUser;
    }
    
    /**
     * 获取当前登录的系统用户 // update 20191227
     * @return
     */
    public static LoginUser getCurrentLoginUser(){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getSession().getAttribute(UpmsConstant.LOGIN_USER1);
        return sysUser;
    }
}
