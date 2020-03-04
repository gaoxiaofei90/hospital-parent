package com.llw.hospital.util;

import com.jcl.common.spring.context.AppContextHolder;
import com.llw.hospital.api.SysRoleService;
import com.llw.hospital.dto.SysRoleDto;

public class RoleUtil {

    public static String getRoleName(String roleId){
        if(roleId==null) {
            return "";
        }
        SysRoleService sysRoleService=AppContextHolder.getBean(SysRoleService.class);
        SysRoleDto sysRoleDto=sysRoleService.selectByPrimaryKey(Long.parseLong(roleId));
        if(sysRoleDto==null){
            return "";
        }else{
            return sysRoleDto.getName();
        }
    }
}
