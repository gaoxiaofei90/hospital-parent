package com.llw.hospital.api.vo.person;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class OuregisterInfo {

	@ApiModelProperty(value = "离床登记ID,数据类型为Long")
	private Long outId;

	@ApiModelProperty(value = "抽样人员ID,数据类型为Long")
	private Long planId;

	@ApiModelProperty(value = "离床原因")
	private String reason;

	@ApiModelProperty(value = "事由说明")
	private String causeDesc;

	@ApiModelProperty(value = "录入人,数据类型为Long")
	private Long inputStaff;

	@ApiModelProperty(value = "录入时间")
	private Date inputTime;

	@ApiModelProperty(value = "审核人,数据类型为Long")
	private Long auditStaff;

	@ApiModelProperty(value = "审核时间")
	private Date auditTime;

	@ApiModelProperty(value = "审核结果（0未审核、1审核已通过、2审核未通过）")
	private Integer auditStatus;

	@ApiModelProperty(value = "审核批注")
	private String auditDesc;

	@ApiModelProperty(value = "机构ID,数据类型为Long")
	private Long orgId;

	@ApiModelProperty(value = "特征库人员ID,数据类型为Long")
	private Long personId;

	@ApiModelProperty(value = "系统编号")
	private Integer sysCode;

	@ApiModelProperty(value = "统筹区编码")
	private String zoneCode;

	@ApiModelProperty(value = "险种/业务（310医保、330居民医保、410工伤、510生育）")
	private String busiType;

	public Long getOutId() {
		return outId;
	}

	public void setOutId(Long outId) {
		this.outId = outId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCauseDesc() {
		return causeDesc;
	}

	public void setCauseDesc(String causeDesc) {
		this.causeDesc = causeDesc;
	}

	public Long getInputStaff() {
		return inputStaff;
	}

	public void setInputStaff(Long inputStaff) {
		this.inputStaff = inputStaff;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Long getAuditStaff() {
		return auditStaff;
	}

	public void setAuditStaff(Long auditStaff) {
		this.auditStaff = auditStaff;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
