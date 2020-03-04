package com.llw.hospital.ds.entity;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 操作日志
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@Table(name = "sys_oper_log")
public class SysOperLog {

/**
 * 
 */
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY,generator = LLwSelectKeyGenerator.SNOWFLAKE)
private Long operId;
/**
 * 
 */
@Column(name = "user_id")
private Long userId;
/**
 * 
 */
@Column(name = "username")
private String username;
/**
 * 
 */
@Column(name = "ip")
private String ip;
/**
 * 
 */
@Column(name = "modules")
private String modules;
/**
 * 
 */
@Column(name = "action")
private String action;
/**
 * 时间
 */
@Column(name = "create_time")
private Date createTime;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Column(name = "org_id")
private Long orgId;

/**
 * 设置：
 */
public void setOperId(Long operId) {
    this.operId = operId;
}
/**
 * 获取：
 */
public Long getOperId() {
    return operId;
}
/**
 * 设置：
 */
public void setUserId(Long userId) {
    this.userId = userId;
}
/**
 * 获取：
 */
public Long getUserId() {
    return userId;
}
/**
 * 设置：
 */
public void setUsername(String username) {
    this.username = username;
}
/**
 * 获取：
 */
public String getUsername() {
    return username;
}
/**
 * 设置：
 */
public void setIp(String ip) {
    this.ip = ip;
}
/**
 * 获取：
 */
public String getIp() {
    return ip;
}
/**
 * 设置：
 */
public void setModules(String modules) {
    this.modules = modules;
}
/**
 * 获取：
 */
public String getModules() {
    return modules;
}
/**
 * 设置：
 */
public void setAction(String action) {
    this.action = action;
}
/**
 * 获取：
 */
public String getAction() {
    return action;
}
/**
 * 设置：时间
 */
public void setCreateTime(Date createTime) {
    this.createTime = createTime;
}
/**
 * 获取：时间
 */
public Date getCreateTime() {
    return createTime;
}
}
