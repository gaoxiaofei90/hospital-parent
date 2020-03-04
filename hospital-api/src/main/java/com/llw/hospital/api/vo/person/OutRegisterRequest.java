package com.llw.hospital.api.vo.person;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class OutRegisterRequest {

	@ApiModelProperty(value = " 抽样人员ID,数据类型为Long")
	private Long planId;

	@ApiModelProperty(value = "离床原因")
	private String reason;

	@ApiModelProperty(value = "事由说明")
	private String causeDesc;

	@ApiModelProperty(value = "录入人,数据类型为Long")
	private Long inputStaff;

	@ApiModelProperty(value = "机构ID,数据类型为Long")
	private Long orgId;

	@ApiModelProperty(value = "设备类型（JXB309:体温枪,M320:POS终端M320,M321:POS终端M321,M323:POS终端M323,M324:POS终端M324,F120:双目人证通,F330:双屏,P310:P310,CF320:摄像机,P101:安卓手机,P102:苹果手机）")
	private String devType;

	@ApiModelProperty(value = "终端编码")
	private String devCode;

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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

}
