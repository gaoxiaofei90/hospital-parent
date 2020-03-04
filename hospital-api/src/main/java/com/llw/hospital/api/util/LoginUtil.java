package com.llw.hospital.api.util;

import com.llw.hospital.api.shiro.JWTFilter;
import com.llw.hospital.dto.SysUserDto;

public class LoginUtil {
    /**
     * 获取当前登录的用户
     * @return
     */
    public static SysUserDto getCurrentUser(){
        return JWTFilter.currentUser.get();
    }
}
