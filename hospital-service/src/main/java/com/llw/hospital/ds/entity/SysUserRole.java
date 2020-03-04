package com.llw.hospital.ds.entity;

import javax.persistence.*;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@Table(name = "sys_user_role")
public class SysUserRole {

/**
 * 
 */
@Column(name = "user_id")
private Long userId;
/**
 * 
 */
@Column(name = "role_id")
private Long roleId;

public Long getUserId() {
    return userId;
}

public void setUserId(Long userId) {
    this.userId = userId;
}

public Long getRoleId() {
    return roleId;
}

public void setRoleId(Long roleId) {
    this.roleId = roleId;
}
}
