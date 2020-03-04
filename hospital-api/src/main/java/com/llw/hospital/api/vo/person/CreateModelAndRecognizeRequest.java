package com.llw.hospital.api.vo.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "建模和认证信息", description = "建模和认证信息")
public class CreateModelAndRecognizeRequest {

	@ApiModelProperty(value = "人员ID")
	private Long personId;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "身份证号码")
	private String idcard;

	@ApiModelProperty(value = "社保卡号")
	private String sicard;

	@ApiModelProperty(value = "身份证照，图片 base64编码")
	private String idcardPhoto;

	@ApiModelProperty(value = "模板照，图片 base64编码")
	private String modelPhoto;

	@ApiModelProperty(value = "认证照，图片 base64编码")
	private String recogPhoto;

	@ApiModelProperty(value = "生物类型（11人脸、12指静脉、15指纹）")
	private String bioType;

	@ApiModelProperty(value = "特殊人员(0:否，1:是)")
	private Integer specialPerson;

	@ApiModelProperty(value = "特殊人员原因")
	private String specialReason;

	@ApiModelProperty(value = "认证业务（10:抽查认证、11:普查认证、12:入科认证、13:出院认证、14:实人就诊、15:实人购药、16:门特线上、17:门特线下、18:大额检查、19:诊疗项目、20:稽核认证）")
	private Integer recogBusi;

	@ApiModelProperty(value = "认证业务ID")
	private String recogBusiId;

	@ApiModelProperty(value = "设备类型（JXB309:体温枪,M320:POS终端M320,M321:POS终端M321,M323:POS终端M323,M324:POS终端M324,F120:双目人证通,F330:双屏,P310:P310,CF320:摄像机,P101:安卓手机,P102:苹果手机）")
	private String devType;

	@ApiModelProperty(value = "终端编码")
	private String devCode;

	@ApiModelProperty(value = "orgId")
	private Long orgId;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getSicard() {
		return sicard;
	}

	public void setSicard(String sicard) {
		this.sicard = sicard;
	}

	public String getIdcardPhoto() {
		return idcardPhoto;
	}

	public void setIdcardPhoto(String idcardPhoto) {
		this.idcardPhoto = idcardPhoto;
	}

	public String getModelPhoto() {
		return modelPhoto;
	}

	public void setModelPhoto(String modelPhoto) {
		this.modelPhoto = modelPhoto;
	}

	public String getBioType() {
		return bioType;
	}

	public void setBioType(String bioType) {
		this.bioType = bioType;
	}

	public Integer getRecogBusi() {
		return recogBusi;
	}

	public void setRecogBusi(Integer recogBusi) {
		this.recogBusi = recogBusi;
	}

	public String getRecogBusiId() {
		return recogBusiId;
	}

	public void setRecogBusiId(String recogBusiId) {
		this.recogBusiId = recogBusiId;
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

	public Integer getSpecialPerson() {
		return specialPerson;
	}

	public void setSpecialPerson(Integer specialPerson) {
		this.specialPerson = specialPerson == null ? 0 : specialPerson;
	}

	public String getSpecialReason() {
		return specialReason;
	}

	public void setSpecialReason(String specialReason) {
		this.specialReason = specialReason;
	}

	public String getRecogPhoto() {
		return recogPhoto;
	}

	public void setRecogPhoto(String recogPhoto) {
		this.recogPhoto = recogPhoto;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
