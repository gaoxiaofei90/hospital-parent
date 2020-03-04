package com.llw.hospital.api.vo.person;
import io.swagger.annotations.ApiModelProperty;

public class VerificationRequest {

	@ApiModelProperty(value = " 就诊ID,数据类型为Long" )
	private Long medicalId;
	   
	@ApiModelProperty(value = "审核结论" )
	private Integer auditResult;
	   
	@ApiModelProperty(value = "原因" )
	private String reason;

	public Long getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(Long medicalId) {
		this.medicalId = medicalId;
	}

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	

}
