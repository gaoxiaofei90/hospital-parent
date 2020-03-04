package com.llw.hospital.api.vo.person;

import com.llw.hospital.dto.N1RecognizeRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "认证信息", description = "认证信息")
public class RecogInfoRequest  extends N1RecognizeRequest{
	@ApiModelProperty(value = "门特患者ID")
	private Long menterId;
	
	@ApiModelProperty(value = "亲属关系ID")
    private Long relativeId;
	  
	@ApiModelProperty(value = "人员ID")
	private Long personId;	
	
	@ApiModelProperty(value = "认证计划ID")
	private Long planId;

	@ApiModelProperty(value = "特殊人员(0:否，1:是)")
	private Integer specialPerson;

	@ApiModelProperty(value = "认证照，图片 base64编码")
	private String recogPhoto;

	@ApiModelProperty(notes = "认证方式：1在线认证、2离线采集上传")
	private Integer recogMode;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Integer getSpecialPerson() {
		return specialPerson;
	}

	public void setSpecialPerson(Integer specialPerson) {
		this.specialPerson = specialPerson;
	}

	public String getRecogPhoto() {
		return recogPhoto;
	}

	public void setRecogPhoto(String recogPhoto) {
		this.recogPhoto = recogPhoto;
	}


	public Long getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(Long relativeId) {
		this.relativeId = relativeId;
	}

	public Integer getRecogMode() {
		return recogMode;
	}

	public void setRecogMode(Integer recogMode) {
		this.recogMode = recogMode;
	}

	public Long getMenterId() {
		return menterId;
	}

	public void setMenterId(Long menterId) {
		this.menterId = menterId;
	}

	

	
}
