package com.llw.hospital.api;

import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.SysRolePermissionDto;
import com.llw.hospital.dto.SysRoleDto;

import java.util.List;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public interface SysRoleService extends BaseDtoExtendService<SysRoleDto> {

    /**
     * 获取角色已有的权限
     * @param roleId
     * @return
     */
    List<SysRolePermissionDto> getPermissions(Long roleId);

    /**
     * 重新分配权限
     * @param roleId
     * @param permissions
     */
    void reAssignPermissions(Long roleId,List<Long> permissions);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRole(Long roleId);


}

