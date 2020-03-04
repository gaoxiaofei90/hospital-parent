package com.llw.hospital.api.vo.person;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "特殊人员建模信息", description = "特殊人员建模信息")
public class SpecialPersonCreateModelRequest {

	@ApiModelProperty(value = "人员ID")
	private Long personId;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "身份证号码")
	private String idcard;

	@ApiModelProperty(value = "社保卡号")
	private String sicard;


	@ApiModelProperty(value = "生物类型（11人脸、12指静脉、15指纹）")
	private String bioType;

	@ApiModelProperty(value = "特殊人员证明材料")
	MultipartFile[] files;
	
/*	@ApiModelProperty(value = "特殊人员证明材料")
	private List<String> materialList;
	*/
	@ApiModelProperty(value = "操作人照片,图片 base64编码")
	private String operatorPhoto;
	
	@ApiModelProperty(value = "模板照，图片 base64编码")
	private String modelPhoto;
;
	
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

	public String getBioType() {
		return bioType;
	}

	public void setBioType(String bioType) {
		this.bioType = bioType;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public String getOperatorPhoto() {
		return operatorPhoto;
	}

	public void setOperatorPhoto(String operatorPhoto) {
		this.operatorPhoto = operatorPhoto;
	}

	public String getModelPhoto() {
		return modelPhoto;
	}

	public void setModelPhoto(String modelPhoto) {
		this.modelPhoto = modelPhoto;
	}

	

}
