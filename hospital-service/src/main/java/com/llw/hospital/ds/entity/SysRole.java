package com.llw.hospital.ds.entity;

import com.jcl.orm.tkmapper.annotation.OrgId;
import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@Table(name = "sys_role")
public class SysRole {

/**
 * 
 */
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY,generator = LLwSelectKeyGenerator.SNOWFLAKE)
private Long roleId;
/**
 * 
 */
@Column(name = "org_id")
@OrgId
private Long orgId;
/**
 * 
 */
@Column(name = "name")
private String name;
/**
 * 
 */
@Column(name = "title")
private String title;
/**
 * 
 */
@Column(name = "description")
private String description;
/**
 * 
 */
@Column(name = "ctime")
private Date ctime;
/**
 * 
 */
@Column(name = "create_user")
private String createUser;
/**
 * 字典 enable_status
 */
@Column(name = "enabled")
private Integer enabled;

@Column(name="role_type")
private Integer roleType;

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
/**
 * 设置：
 */
public void setOrgId(Long orgId) {
    this.orgId = orgId;
}
/**
 * 获取：
 */
public Long getOrgId() {
    return orgId;
}
/**
 * 设置：
 */
public void setName(String name) {
    this.name = name;
}
/**
 * 获取：
 */
public String getName() {
    return name;
}
/**
 * 设置：
 */
public void setTitle(String title) {
    this.title = title;
}
/**
 * 获取：
 */
public String getTitle() {
    return title;
}
/**
 * 设置：
 */
public void setDescription(String description) {
    this.description = description;
}
/**
 * 获取：
 */
public String getDescription() {
    return description;
}
/**
 * 设置：
 */
public void setCtime(Date ctime) {
    this.ctime = ctime;
}
/**
 * 获取：
 */
public Date getCtime() {
    return ctime;
}
/**
 * 设置：
 */
public void setCreateUser(String createUser) {
    this.createUser = createUser;
}
/**
 * 获取：
 */
public String getCreateUser() {
    return createUser;
}
/**
 * 设置：字典 enable_status
 */
public void setEnabled(Integer enabled) {
    this.enabled = enabled;
}
/**
 * 获取：字典 enable_status
 */
public Integer getEnabled() {
    return enabled;
}

public Integer getRoleType() {
    return roleType;
}

public void setRoleType(Integer roleType) {
    this.roleType = roleType;
}
}
