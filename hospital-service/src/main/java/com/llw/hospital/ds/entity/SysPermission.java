package com.llw.hospital.ds.entity;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

import javax.persistence.*;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@Table(name = "sys_permission")
public class SysPermission {

/**
 * 
 */
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY,generator = LLwSelectKeyGenerator.SNOWFLAKE)
private Long permissionId;
/**
 * 
 */
@Column(name = "pid")
private Long pid;
/**
 * 
 */
@Column(name = "name")
private String name;
/**
 * 类型 放入字典permission_type
 */
@Column(name = "type")
private Integer type;
/**
 * 
 */
@Column(name = "permission_value")
private String permissionValue;
/**
 * 
 */
@Column(name = "uri")
private String uri;
/**
 * 
 */
@Column(name = "icon")
private String icon;
/**
 * 
 */
@Column(name = "orders")
private Integer orders;
/**
 * 字典 enable_status
 */
@Column(name = "enabled")
private Integer enabled;

/**
 * 所属模块 0 后台 1 app
 */
@Column(name="modules")
private Integer modules;

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
public void setPid(Long pid) {
    this.pid = pid;
}
/**
 * 获取：
 */
public Long getPid() {
    return pid;
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

public Integer getType() {
    return type;
}

public void setType(Integer type) {
    this.type = type;
}

    /**
 * 设置：
 */
public void setPermissionValue(String permissionValue) {
    this.permissionValue = permissionValue;
}
/**
 * 获取：
 */
public String getPermissionValue() {
    return permissionValue;
}
/**
 * 设置：
 */
public void setUri(String uri) {
    this.uri = uri;
}
/**
 * 获取：
 */
public String getUri() {
    return uri;
}
/**
 * 设置：
 */
public void setIcon(String icon) {
    this.icon = icon;
}
/**
 * 获取：
 */
public String getIcon() {
    return icon;
}
/**
 * 设置：
 */
public void setOrders(Integer orders) {
    this.orders = orders;
}
/**
 * 获取：
 */
public Integer getOrders() {
    return orders;
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

public Integer getModules() {
    return modules;
}

public void setModules(Integer modules) {
    this.modules = modules;
}
}
