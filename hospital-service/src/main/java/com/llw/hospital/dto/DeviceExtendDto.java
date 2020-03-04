package com.llw.hospital.dto;

import java.math.BigDecimal;

public class DeviceExtendDto extends DeviceDto{

	private String departName;
	 private String hospitalName;
	 private String keyword;
	 private String fromType;
	 
	 private String orgName;
	 private Integer orgCategory;
	 private Long pid;
	 private BigDecimal latitude;
	 private BigDecimal longitude;
	 
	 
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getOrgCategory() {
		return orgCategory;
	}
	public void setOrgCategory(Integer orgCategory) {
		this.orgCategory = orgCategory;
	}
	public String getFromType() {
		return fromType;
	}
	public void setFromType(String fromType) {
		this.fromType = fromType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	 
	 
}
