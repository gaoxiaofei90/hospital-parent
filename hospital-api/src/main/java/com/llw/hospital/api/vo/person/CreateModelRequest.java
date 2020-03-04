package com.llw.hospital.api.vo.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "建模信息", description = "建模信息")
public class CreateModelRequest {

	@ApiModelProperty(value = "人员ID")
	private Long personId;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "身份证号码")
	private String idcard;

	@ApiModelProperty(value = "社保卡号")
	private String sicard;

	@ApiModelProperty(value = "模板照，图片 base64编码")
	private String modelPhoto;

	@ApiModelProperty(value = "生物类型（11人脸、12指静脉、15指纹）")
	private String bioType;

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

}
