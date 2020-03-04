package com.llw.hospital.api.vo.person;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class RecogInfo {

	@ApiModelProperty(value = "认证记录ID,数据类型为Long")
	private Long recogId;

	@ApiModelProperty(value = "抽样人员ID,数据类型为Long")
	private Long planId;

	@ApiModelProperty(value = "认证时间")
	private Date recogTime;

	@ApiModelProperty(value = "生物类型（11人脸、12指静脉、15指纹）")
	private String bioType;

	@ApiModelProperty(value = "模板类型（111可见光）")
	private String modelType;

	@ApiModelProperty(value = "认证照存放路径")
	private String recogAddress;

	@ApiModelProperty(value = "设备类型")
	private String deviceType;

	@ApiModelProperty(value = "设备编码")
	private String deviceCode;

	@ApiModelProperty(value = "创建人,数据类型为Long")
	private Long createStaff;

	@ApiModelProperty(value = "认证结果（1通过、2未通过）")
	private Integer recogResult;

	@ApiModelProperty(value = "认证分值")
	private Float recogScore;

	@ApiModelProperty(value = "组织ID,数据类型为Long")
	private Long orgId;

	@ApiModelProperty(value = "体温")
	private Float temperature;
	@ApiModelProperty(value = "特征库人员ID,数据类型为Long")
	private Long personId;

	@ApiModelProperty(value = "系统编号")
	private Integer sysCode;

	@ApiModelProperty(value = "统筹区编码")
	private String zoneCode;

	@ApiModelProperty(value = "险种/业务（310医保、330居民医保、410工伤、510生育）")
	private String busiType;

	public Long getRecogId() {
		return recogId;
	}

	public void setRecogId(Long recogId) {
		this.recogId = recogId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Date getRecogTime() {
		return recogTime;
	}

	public void setRecogTime(Date recogTime) {
		this.recogTime = recogTime;
	}

	public String getBioType() {
		return bioType;
	}

	public void setBioType(String bioType) {
		this.bioType = bioType;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getRecogAddress() {
		return recogAddress;
	}

	public void setRecogAddress(String recogAddress) {
		this.recogAddress = recogAddress;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Integer getRecogResult() {
		return recogResult;
	}

	public void setRecogResult(Integer recogResult) {
		this.recogResult = recogResult;
	}

	public Float getRecogScore() {
		return recogScore;
	}

	public void setRecogScore(Float recogScore) {
		this.recogScore = recogScore;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Integer getSysCode() {
		return sysCode;
	}

	public void setSysCode(Integer sysCode) {
		this.sysCode = sysCode;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

}
