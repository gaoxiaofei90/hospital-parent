package com.llw.hospital.ds.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;
/**
 * ${comments}
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-09-10 15:58:44
 */
@Table(name = "SYS_DEVICE_TYPE")
public class SysDeviceType {

/**
 * 设备类型
 */
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
private String deviceType;
/**
 * 设备名称
 */
@Column(name = "DEVICE_NAME")
private String deviceName;
/**
 * 设备大类1：终端设备、2网络摄像机
 */
@Column(name = "DEVICE_LARGE_TYPE")
private Long deviceLargeType;

/**
 * 设置：设备类型
 */
public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
}
/**
 * 获取：设备类型
 */
public String getDeviceType() {
    return deviceType;
}
/**
 * 设置：设备名称
 */
public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
}
/**
 * 获取：设备名称
 */
public String getDeviceName() {
    return deviceName;
}
/**
 * 设置：设备大类1：终端设备、2网络摄像机
 */
public void setDeviceLargeType(Long deviceLargeType) {
    this.deviceLargeType = deviceLargeType;
}
/**
 * 获取：设备大类1：终端设备、2网络摄像机
 */
public Long getDeviceLargeType() {
    return deviceLargeType;
}
}
