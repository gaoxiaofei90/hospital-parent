package com.llw.hospital.dto;

import java.util.Date;

public class MedicalExtendDto extends MedicalDto{

	private Integer admissionAcount;//入院人数
	
	private Integer firstRecogAcount;//入科认证人数
	
	private Integer proportion;//占比
	
	private Integer inHospitalAcount;//在院人数
	
	private Integer shouldCensusACount;//应普查人数 
	
	private Integer censusACount;//实际普查人数 
	
	private Integer proportion2;//占比
	
	private Integer warningAcount;//预警人数
	
	private Integer firstStatus;//入科认证状态
	
	private String keyword;//关键字
    
    private String startTime;
    
    private String endTime;
    
    private Integer visitingDoctorAcount;//就诊人数
    
    private Integer recogAcount;//已认证人数
    
    private Integer notRecogAcount;//未认证人数
    
    private String recogResult;//认证结果
    
    private Integer recogBusi;//10:抽查认证、11:普查认证、12:入科认证、13:出院认证、14:实人就诊、15:实人购药、16:门特线上、17:门特线下、18:大额检查、19:诊疗项目、20:稽核认证
    
    private Integer medicalCount;
    
    private Date recogTime;
    
    private Integer shouldInspectionACount;//稽查人数
    
    private Integer inspectionStatus;//稽查状态
    
    private String deviceCode;//设备编码
    
    private String deviceName;//设备名称
    
    private Integer medicalPersonTotal;//在院医保人员总
    
    private Integer noMedicalPersonTotal;//在院参保待定人员
    
    private Integer inHospitalRecogAcount;//入科认证人数
    
    private String orderType;//排序
    private String orderField;
    
    private String modelAddress;
    
    private Long userOrgId;
    
    
	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getModelAddress() {
		return modelAddress;
	}

	public void setModelAddress(String modelAddress) {
		this.modelAddress = modelAddress;
	}


	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public Integer getInHospitalRecogAcount() {
		return inHospitalRecogAcount;
	}

	public void setInHospitalRecogAcount(Integer inHospitalRecogAcount) {
		this.inHospitalRecogAcount = inHospitalRecogAcount;
	}

	public Integer getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(Integer inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public Integer getShouldInspectionACount() {
		return shouldInspectionACount;
	}

	public void setShouldInspectionACount(Integer shouldInspectionACount) {
		this.shouldInspectionACount = shouldInspectionACount;
	}

	public Date getRecogTime() {
		return recogTime;
	}

	public void setRecogTime(Date recogTime) {
		this.recogTime = recogTime;
	}

	public Integer getMedicalCount() {
		return medicalCount;
	}

	public void setMedicalCount(Integer medicalCount) {
		this.medicalCount = medicalCount;
	}

	public Integer getRecogBusi() {
		return recogBusi;
	}

	public void setRecogBusi(Integer recogBusi) {
		this.recogBusi = recogBusi;
	}

	public Integer getVisitingDoctorAcount() {
		return visitingDoctorAcount;
	}

	public void setVisitingDoctorAcount(Integer visitingDoctorAcount) {
		this.visitingDoctorAcount = visitingDoctorAcount;
	}

	public Integer getRecogAcount() {
		return recogAcount;
	}

	public void setRecogAcount(Integer recogAcount) {
		this.recogAcount = recogAcount;
	}

	public Integer getNotRecogAcount() {
		return notRecogAcount;
	}

	public void setNotRecogAcount(Integer notRecogAcount) {
		this.notRecogAcount = notRecogAcount;
	}

	public String getRecogResult() {
		return recogResult;
	}

	public void setRecogResult(String recogResult) {
		this.recogResult = recogResult;
	}

	public Integer getAdmissionAcount() {
		return admissionAcount;
	}

	public void setAdmissionAcount(Integer admissionAcount) {
		this.admissionAcount = admissionAcount;
	}

	public Integer getFirstRecogAcount() {
		return firstRecogAcount;
	}

	public void setFirstRecogAcount(Integer firstRecogAcount) {
		this.firstRecogAcount = firstRecogAcount;
	}

	public Integer getProportion() {
		return proportion;
	}

	public void setProportion(Integer proportion) {
		this.proportion = proportion;
	}

	public Integer getInHospitalAcount() {
		return inHospitalAcount;
	}

	public void setInHospitalAcount(Integer inHospitalAcount) {
		this.inHospitalAcount = inHospitalAcount;
	}

	public Integer getShouldCensusACount() {
		return shouldCensusACount;
	}

	public void setShouldCensusACount(Integer shouldCensusACount) {
		this.shouldCensusACount = shouldCensusACount;
	}

	public Integer getCensusACount() {
		return censusACount;
	}

	public void setCensusACount(Integer censusACount) {
		this.censusACount = censusACount;
	}

	public Integer getProportion2() {
		return proportion2;
	}

	public void setProportion2(Integer proportion2) {
		this.proportion2 = proportion2;
	}

	public Integer getWarningAcount() {
		return warningAcount;
	}

	public void setWarningAcount(Integer warningAcount) {
		this.warningAcount = warningAcount;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public Integer getFirstStatus() {
		return firstStatus;
	}

	public void setFirstStatus(Integer firstStatus) {
		this.firstStatus = firstStatus;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getMedicalPersonTotal() {
		return medicalPersonTotal;
	}

	public void setMedicalPersonTotal(Integer medicalPersonTotal) {
		this.medicalPersonTotal = medicalPersonTotal;
	}

	public Integer getNoMedicalPersonTotal() {
		return noMedicalPersonTotal;
	}

	public void setNoMedicalPersonTotal(Integer noMedicalPersonTotal) {
		this.noMedicalPersonTotal = noMedicalPersonTotal;
	}

	
	
	
	
}
