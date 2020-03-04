package com.llw.hospital.ds.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 设备管理表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-04-30 14:09:28
 */
@Table(name = "T_DEVICE")
public class Device {

	/**
	 * 设备编码
	 */
	@Id
	private String deviceCode;
	/**
	 * 设备状态(1正常、2维修中)
	 */
	@Column(name = "DEVICE_STATUS")
	private String deviceStatus;
	/**
	 * 设备类型(体温枪:500、M320:M320、P310:P310、一体机:D320)
	 */
	@Column(name = "DEVICE_TYPE")
	private String deviceType;

	@Column(name = "ONLINE_TIME")
	private Date onlineTime;

	@Column(name = "net_type")
	private Integer netType;

	/**
	 * 硬件版本
	 */
	@Column(name = "HARD_VERSION")
	private String hardVersion;
	/**
	 * 机构ID
	 */
	@Column(name = "ORG_ID")
	private Long orgId;
	/**
	 * 软件版本
	 */
	@Column(name = "SOFT_VERSION")
	private String softVersion;
	/**
	 * 网络状态(1在线、2离线)
	 */
	@Column(name = "NET_STATUS")
	private String netStatus;
	/**
	 * 所属医院
	 */
	@Column(name = "HOSPITAL_ID")
	private Long hospitalId;
	/**
	 * 操作系统
	 */
	@Column(name = "OS_VERSION")
	private String osVersion;
	/**
	 * 产品编码
	 */
	@Column(name = "PRODUCT_CODE")
	private String productCode;
	/**
	 * 所属科室
	 */
	@Column(name = "DEPART_ID")
	private Long departId;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;

	/**
	 * 设备名称
	 */
	@Column(name = "DEVICE_NAME")
	private String deviceName;

	/**
	 * 物理地址
	 */
	@Column(name = "MAC_ADDRESS")
	private String macAddress;

	/**
	 * IP地址
	 */
	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	/**
	 * 接入账号
	 */
	@Column(name = "USER_NAME")
	private String userName;

	/**
	 * 接入账号
	 */
	@Column(name = "USER_PWD")
	private String userPwd;

	/**
	 * 主管机构ID
	 */
	@Column(name = "MANAGE_ORG_ID")
	private Long manageOrgId;

	/**
	 * 安装位置
	 */
	@Column(name = "LOCATION")
	private String location;

	/**
	 * 镜头方式
	 */
	@Column(name = "CAMERA_DIRECTION")
	private String cameraDirection;

	/**
	 * 地区
	 */
	@Column(name = "ZONE_CODE")
	private String zoneCode;

	@Column(name = "SERVER_ID")
	private Long serverId;

	@Column(name = "RECOG_BUSI")
	private String recogBusi;

	@Column(name = "CAMERA_INDEX_CODE")
	private String cameraIndexCode;

	private String streamUrl;

	private Integer cameraPicServerType;
	
	@Column(name = "DEVICE_LARGE_TYPE")
	private Integer deviceLargeType;
	
	
	@Column(name = "CURE_ITEM")
	private String cureItem;

	private Integer archiveType;

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

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
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

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
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

	public Integer getDeviceLargeType() {
		return deviceLargeType;
	}

	public void setDeviceLargeType(Integer deviceLargeType) {
		this.deviceLargeType = deviceLargeType;
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
