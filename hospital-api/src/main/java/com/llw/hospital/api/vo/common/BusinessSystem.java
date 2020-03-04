package com.llw.hospital.api.vo.common;

import io.swagger.annotations.ApiModelProperty;

public class BusinessSystem{

	@ApiModelProperty(value = "系统编号")
    private Integer sysCode;
    
	@ApiModelProperty(value = "系统名称")
	private String sysName;
	  
	@ApiModelProperty(value = "业务类型（310医疗、410工伤等）")
    private String busiType;

	@ApiModelProperty(value = "统筹区）")
    private String zoneCode;

	public Integer getSysCode() {
		return sysCode;
	}

	public void setSysCode(Integer sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	     
	}
