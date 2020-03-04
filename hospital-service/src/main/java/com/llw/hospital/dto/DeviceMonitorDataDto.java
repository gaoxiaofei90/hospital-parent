package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

public class DeviceMonitorDataDto extends BaseDto{

	 private Long orgId;
	 private String hospitalName;
	 private Integer onLineAmount;//在院人数
	 private Integer offLineAmount;//今日抽查人数
	 private Integer warnAmount;//今日抽查完成人数
	 private Integer subtotal;//在线+离线
	 private String deviceType;
	 
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Integer getOnLineAmount() {
		return onLineAmount;
	}
	public void setOnLineAmount(Integer onLineAmount) {
		this.onLineAmount = onLineAmount;
	}
	public Integer getOffLineAmount() {
		return offLineAmount;
	}
	public void setOffLineAmount(Integer offLineAmount) {
		this.offLineAmount = offLineAmount;
	}
	public Integer getWarnAmount() {
		return warnAmount;
	}
	public void setWarnAmount(Integer warnAmount) {
		this.warnAmount = warnAmount;
	}
	public Integer getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
