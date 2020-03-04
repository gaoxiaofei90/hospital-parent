package com.llw.hospital.ds.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-11 10:30:27
 */
@Table(name = "T_MONITOR")
public class Monitor {

/**
 * 
 */
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
private Long monitorId;
/**
 * 
 */
@Column(name = "CREATE_TIME")
private Date createTime;
/**
 * 备注
 */
@Column(name = "MARK")
private String mark;
/**
 * 
 */
@Column(name = "DEVICE_CODE")
private String deviceCode;
/**
 * 
 */
@Column(name = "DEVICE_TYPE")
private String deviceType;

/**
 * 设置：
 */
public void setMonitorId(Long monitorId) {
    this.monitorId = monitorId;
}
/**
 * 获取：
 */
public Long getMonitorId() {
    return monitorId;
}
/**
 * 设置：
 */
public void setCreateTime(Date createTime) {
    this.createTime = createTime;
}
/**
 * 获取：
 */
public Date getCreateTime() {
    return createTime;
}
/**
 * 设置：
 */
public void setMark(String mark) {
    this.mark = mark;
}
/**
 * 获取：
 */
public String getMark() {
    return mark;
}
/**
 * 设置：
 */
public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
}
/**
 * 获取：
 */
public String getDeviceCode() {
    return deviceCode;
}
/**
 * 设置：
 */
public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
}
/**
 * 获取：
 */
public String getDeviceType() {
    return deviceType;
}
}
