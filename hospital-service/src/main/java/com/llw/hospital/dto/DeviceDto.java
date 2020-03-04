package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

import java.util.Date;

/**
 * 设备管理表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-04-30 14:09:28
 */
public class DeviceDto extends BaseDto {

	/**
	 * 设备编码
	 */
	private String deviceCode;
	/**
	 * 设备状态(1正常、2维修中)
	 */
	private String deviceStatus;
	/**
	 * 设备类型(体温枪:500、M320:M320、P310:P310、一体机:D320)
	 */
	private String deviceType;
	/**
	 * 硬件版本
	 */
	private String hardVersion;
	/**
	 * 机构ID
	 */
	private Long orgId;
	/**
	 * 软件版本
	 */
	private String softVersion;
	/**
	 * 网络状态(1在线、2离线)
	 */
	private String netStatus;
	/**
	 * 所属医院
	 */
	private Long hospitalId;
	/**
	 * 操作系统
	 */
	private String osVersion;
	/**
	 * 产品编码
	 */
	private String productCode;
	/**
	 * 所属科室
	 */
	private Long departId;

	private Date createTime;

	private Date onlineTime;

	/**
	 * 归档方式 0 异步 1 实时
	 */
	private Integer archiveType;

	/**
	 * 设备名称
	 */
	private String deviceName;

	/**
	 * 物理地址
	 */
	private String macAddress;

	/**
	 * IP地址
	 */
	private String ipAddress;

	/**
	 * 接入账号
	 */
	private String userName;

	/**
	 * 接入账号
	 */
	private String userPwd;

	/**
	 * 主管机构ID
	 */
	private Long manageOrgId;

	/**
	 * 安装位置
	 */
	private String location;

	/**
	 * 镜头方式
	 */
	private String cameraDirection;

	/**
	 * 地区
	 */
	private String zoneCode;

	private Long serverId;

	private String recogBusi;

	private Integer netType;

	private String cameraIndexCode;

	private String streamUrl;

	private Integer cameraPicServerType;//1 VBR 0 Tomcat

	private Integer deviceLargeType;
	
	private String cureItem;
	
	public Integer getDeviceLargeType() {
		return deviceLargeType;
	}

	public void setDeviceLargeType(Integer deviceLargeType) {
		this.deviceLargeType = deviceLargeType;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	/**
	 * 设置：设备编码
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	/**
	 * 获取：设备编码
	 */
	public String getDeviceCode() {
		return deviceCode;
	}

	/**
	 * 设置：设备状态(1正常、2维修中)
	 */
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	/**
	 * 获取：设备状态(1正常、2维修中)
	 */
	public String getDeviceStatus() {
		return deviceStatus;
	}

	/**
	 * 设置：设备类型(体温枪:500、M320:M320、P310:P310、一体机:D320)
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * 获取：设备类型(体温枪:500、M320:M320、P310:P310、一体机:D320)
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 设置：硬件版本
	 */
	public void setHardVersion(String hardVersion) {
		this.hardVersion = hardVersion;
	}

	/**
	 * 获取：硬件版本
	 */
	public String getHardVersion() {
		return hardVersion;
	}

	/**
	 * 设置：机构ID
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取：机构ID
	 */
	public Long getOrgId() {
		return orgId;
	}

	/**
	 * 设置：软件版本
	 */
	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	/**
	 * 获取：软件版本
	 */
	public String getSoftVersion() {
		return softVersion;
	}

	/**
	 * 设置：网络状态(1在线、2离线)
	 */
	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}

	/**
	 * 获取：网络状态(1在线、2离线)
	 */
	public String getNetStatus() {
		return netStatus;
	}

	/**
	 * 设置：所属医院
	 */
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
	 * 获取：所属医院
	 */
	public Long getHospitalId() {
		return hospitalId;
	}

	/**
	 * 设置：操作系统
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * 获取：操作系统
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * 设置：产品编码
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * 获取：产品编码
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * 设置：所属科室
	 */
	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	/**
	 * 获取：所属科室
	 */
	public Long getDepartId() {
		return departId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Long getManageOrgId() {
		return manageOrgId;
	}

	public void setManageOrgId(Long manageOrgId) {
		this.manageOrgId = manageOrgId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCameraDirection() {
		return cameraDirection;
	}

	public void setCameraDirection(String cameraDirection) {
		this.cameraDirection = cameraDirection;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getRecogBusi() {
		return recogBusi;
	}

	public void setRecogBusi(String recogBusi) {
		this.recogBusi = recogBusi;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getNetType() {
		return netType;
	}

	public void setNetType(Integer netType) {
		this.netType = netType;
	}

	public String getCameraIndexCode() {
		return cameraIndexCode;
	}

	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}

	public String getStreamUrl() {
		return streamUrl;
	}

	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
	}

	public Integer getCameraPicServerType() {
		return cameraPicServerType;
	}

	public void setCameraPicServerType(Integer cameraPicServerType) {
		this.cameraPicServerType = cameraPicServerType;
	}

	public String getCureItem() {
		return cureItem;
	}

	public void setCureItem(String cureItem) {
		this.cureItem = cureItem;
	}

	public Integer getArchiveType() {
		return archiveType;
	}

	public void setArchiveType(Integer archiveType) {
		this.archiveType = archiveType;
	}
}
