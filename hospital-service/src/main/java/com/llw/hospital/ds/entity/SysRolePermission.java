package com.llw.hospital.ds.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:23
 */
@Table(name = "sys_role_permission")
public class SysRolePermission {

/**
 * 
 */
@Column(name = "permission_id")
private Long permissionId;
/**
 * 
 */
@Column(name = "role_id")
private Long roleId;

/**
 * 设置：
 */
public void setPermissionId(Long permissionId) {
    this.permissionId = permissionId;
}
/**
 * 获取：
 */
public Long getPermissionId() {
    return permissionId;
}
/**
 * 设置：
 */
public void setRoleId(Long roleId) {
    this.roleId = roleId;
}
/**
 * 获取：
 */
public Long getRoleId() {
    return roleId;
}
}
