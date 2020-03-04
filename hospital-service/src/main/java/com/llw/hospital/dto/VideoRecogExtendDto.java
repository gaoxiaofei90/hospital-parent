package com.llw.hospital.dto;

import java.util.Date;

public class VideoRecogExtendDto extends VideoRecogDto {

	 private String name;

    private String idcard;
    
    private String sex;
    
    private Integer age;
    
    private Integer status;//当前状态（在院、出院）
    
    private String statusName;//当前状态（在院、出院）
    
    private String hospitalName;//源医院名称
    
    private String departName;//源科室名称
    
    private String diagnosis;//疾病诊断名称
    
    private String medicalNo;//住院号
    
    private String bedNo;//床号
    
    private String keyword;//关键字
    
    private String startTime;//用于查询 抽查开始时间大于等于此时间
    
    private String endTime;//用于查询 抽查开始时间小于等于此时间
    
    private String inputTimeStr;//录入时间
    
    private String recogTimeStr;//认证时间
    
    private String auditStatusName;//申诉状态名称
    
    private Date inDate;//入院时间
    
    private String inDateStr;//入院时间 页面展示时需要格式化）
    
    private Date outDate;//出院时间
    
    private String outDateStr;//出院时间 页面展示时需要格式化）
    
    private Long personId;//人员编号
    
    private String modelAddress;//模板照片地址
    
    private String recogAddress;//认证照片地址
    
    private String avaliableZoneCode;
    
    private String avaliableBusiTypes;
    
    private String avaliableSysCodes;
    
    private String reason;
    
    private Long departId;
    
    private Integer fromType;//1为中心端  2为医院端
    
    private Long userOrgId;
    
    
	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Integer getFromType() {
		return fromType;
	}

	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}
	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	public String getAvaliableZoneCode() {
		return avaliableZoneCode;
	}

	public void setAvaliableZoneCode(String avaliableZoneCode) {
		this.avaliableZoneCode = avaliableZoneCode;
	}

	public String getAvaliableBusiTypes() {
		return avaliableBusiTypes;
	}

	public void setAvaliableBusiTypes(String avaliableBusiTypes) {
		this.avaliableBusiTypes = avaliableBusiTypes;
	}

	public String getAvaliableSysCodes() {
		return avaliableSysCodes;
	}

	public void setAvaliableSysCodes(String avaliableSysCodes) {
		this.avaliableSysCodes = avaliableSysCodes;
	}

	public String getInputTimeStr() {
		return inputTimeStr;
	}

	public void setInputTimeStr(String inputTimeStr) {
		this.inputTimeStr = inputTimeStr;
	}

	public String getAuditStatusName() {
		return auditStatusName;
	}

	public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMedicalNo() {
		return medicalNo;
	}

	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getInDateStr() {
		return inDateStr;
	}

	public void setInDateStr(String inDateStr) {
		this.inDateStr = inDateStr;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutDateStr() {
		return outDateStr;
	}

	public void setOutDateStr(String outDateStr) {
		this.outDateStr = outDateStr;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getModelAddress() {
		return modelAddress;
	}

	public void setModelAddress(String modelAddress) {
		this.modelAddress = modelAddress;
	}

	public String getRecogAddress() {
		return recogAddress;
	}

	public void setRecogAddress(String recogAddress) {
		this.recogAddress = recogAddress;
	}

	public String getRecogTimeStr() {
		return recogTimeStr;
	}

	public void setRecogTimeStr(String recogTimeStr) {
		this.recogTimeStr = recogTimeStr;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
}
