package com.llw.hospital.api.vo.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.llw.hospital.util.UrlMapping;

@ApiModel(value="人员信息",description="人员信息")
public class PersonInfo {
	@ApiModelProperty(value = "就诊ID,数据类型为Long")
	private Long medicalId;
	
	@ApiModelProperty(value = "人员ID,数据类型为Long")
	private Long personId;
	
	@ApiModelProperty(value = "姓名")
	private String name;
	
	@ApiModelProperty(value = "身份证号码")
	private String idcard;
	
	@ApiModelProperty(value = "性别")
	private String sex;
	
	@ApiModelProperty(value = "年龄")
	private Integer age;
	
	@ApiModelProperty(value = "人员类型（1医保人员、2非医保人员）")
	private String personType;
	
/*	@ApiModelProperty(value = "病区")
	private Long hospitalAreaId; */
	
	@ApiModelProperty(value = "就诊号/住院号")
	private String medicalNo;
	
	@ApiModelProperty(value = "就诊类型（1住院、2门诊、3门特门慢）")
	private Integer medicalType;
	
	@ApiModelProperty(value = "床号")
	private String bedNo;
	
	@ApiModelProperty(value = "诊断/疾病,数据类型为Long")
	private Long diagnosisId;

	@ApiModelProperty(value = "诊断/疾病名称")
	private String diagnosis;
	
	@ApiModelProperty(value = "入院时间")
	private Date  inDate;
	
	@ApiModelProperty(value = "出院时间")
	private Date outDate;
	
	@ApiModelProperty(value = "当前状态（在院、出院）")
	private Integer status;
	
	@ApiModelProperty(value = "机构id,数据类型为Long")
	private Long orgId;

	@ApiModelProperty(value = "科室名称")
	private String departName;
	
	@ApiModelProperty(value = "照片地址")
	@UrlMapping
	private String photoAddress;
	
	@ApiModelProperty(value = "源医院名称")
	private String hospitalName;
	
	@ApiModelProperty(value = "照片base64串")
	private String photoBase64String;
	
	@ApiModelProperty(value = "区域名")
	private String zoneName;
	
	@ApiModelProperty(value = "是否已建模 true:有模板  false：无模板")
	private boolean hasModel;
	
	@ApiModelProperty(value = "社保卡号")
	private String sicard;
	
	@ApiModelProperty(value = "数据来源 1 录入 2 同步")
	private Integer dataFrom;
	
	@ApiModelProperty(value = "科室ID")
	private Long departId;
	
	@ApiModelProperty(value = "险种/业务（310城镇职工医保、390城乡居民医保）")
	private String busiType;
	
	@ApiModelProperty(value = "参保统筹区（1本省、2本市、3外省、4省本级）")
	private Integer personFrom;
	
	public Long getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(Long medicalId) {
		this.medicalId = medicalId;
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getMedicalNo() {
		return medicalNo;
	}

	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}

	public Integer getMedicalType() {
		return medicalType;
	}

	public void setMedicalType(Integer medicalType) {
		this.medicalType = medicalType;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public Long getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getPhotoAddress() {
		return photoAddress;
	}

	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getPhotoBase64String() {
		return photoBase64String;
	}

	public void setPhotoBase64String(String photoBase64String) {
		this.photoBase64String = photoBase64String;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public boolean isHasModel() {
		return hasModel;
	}

	public void setHasModel(boolean hasModel) {
		this.hasModel = hasModel;
	}

	public String getSicard() {
		return sicard;
	}


	public void setSicard(String sicard) {
		this.sicard = sicard;
	}

	public Integer getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(Integer dataFrom) {
		this.dataFrom = dataFrom;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public Integer getPersonFrom() {
		return personFrom;
	}

	public void setPersonFrom(Integer personFrom) {
		this.personFrom = personFrom;
	}

	
}
