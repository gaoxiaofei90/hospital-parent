package com.llw.hospital.bs.shiro.realm;

import com.llw.common.shiro.realm.LLwRealm;
import com.llw.hospital.api.SysUpmsApiService;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.util.MD5Util;
import com.llw.hospital.dto.SysPermissionDto;
import com.llw.hospital.dto.SysUserDto;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户认证和授权
 * Created by shuzheng on 2017/1/20.
 */
@Component
public class UpmsRealm extends LLwRealm {

    private static Logger _log = LoggerFactory.getLogger(UpmsRealm.class);

    @Autowired
    SysUpmsApiService upmsApiService;

    @Autowired
    SysUserService sysUserService;
    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
       // UpmsApiService upmsApiService = AppContextHolder.getBean(UpmsApiService.class);
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setUsername(username);
        SysUserDto upmsUser = sysUserService.selectOne(sysUserDto);


        // 当前用户所有权限
        List<SysPermissionDto> upmsPermissions = upmsApiService.selectPermissionByUserId(upmsUser.getUserId(),BaseConstants.MODULES_BS);
        Set<String> permissions = new HashSet<>();
        for (SysPermissionDto upmsPermission : upmsPermissions) {
            if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
                String[] permissionArray = upmsPermission.getPermissionValue().split(";");
                for(String p:permissionArray){
                    if(!StringUtils.isBlank(p)){
                        permissions.add(p);
                    }
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setUsername(username);
        SysUserDto upmsUser = sysUserService.selectOne(sysUserDto);

        if (null == upmsUser) {
            throw new UnknownAccountException();
        }
        if (!upmsUser.getPassword().equals(MD5Util.MD5(password + upmsUser.getSalt()))) {
            throw new IncorrectCredentialsException();
        }
        if(upmsUser.getEnabled()== BaseConstants.STATUS_FORBIDDEN){
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
