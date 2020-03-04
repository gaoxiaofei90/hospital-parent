package com.llw.hospital.api;

import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.SysUserRoleDto;
import com.llw.hospital.vo.role.UserRoleDto;

import java.util.List;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public interface SysUserRoleService extends BaseDtoExtendService<SysUserRoleDto> {

    String selectRoleIds(Long userId);

    List<UserRoleDto> selectRoles(Long userId, Long operUserId);

    List<UserRoleDto> selectRoles(Long operUserId);
}

