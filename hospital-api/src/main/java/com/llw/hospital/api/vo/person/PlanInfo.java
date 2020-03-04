package com.llw.hospital.api.vo.person;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.llw.hospital.util.UrlMapping;

@ApiModel(value="抽查记录信息",description="抽查记录信息")
public class PlanInfo {
	
	@ApiModelProperty(value = "抽样人员ID,数据类型为Long")
	private Long planId;
	 
	@ApiModelProperty(value = "抽查对象ID,数据类型为Long")
	private Long batchId;
	    
	@ApiModelProperty(value = "特征库人员ID,数据类型为Long")
	private Long personId;
	       
	@ApiModelProperty(value = "特认证业务类型（10抽查认证、11、日常认证、12入科认证、13出院认证）")
	private Integer recogBusi;
	
	@ApiModelProperty(value = "认证类型（1人脸识别、2视屏拍照、3离床登记）")
	private Integer recogType;
	      
	@ApiModelProperty(value = "制单时间")
	private Date createTime;
	       
	@ApiModelProperty(value = "抽查开始时间")
	private Date startTime;
	    
	@ApiModelProperty(value = "要求完成时间")
	private Date finishTime;
	      
	@ApiModelProperty(value = "源人员编码")
	private String personCode;
	    
	@ApiModelProperty(value = "姓名")
	private String name;
	    
	@ApiModelProperty(value = " 证件号码")
	private String idcard;
	   
	@ApiModelProperty(value = "就诊号/住院号")
	private String medicalNo;
	      
	@ApiModelProperty(value = "床号")
	private String bedNo;
	   
	@ApiModelProperty(value = "认证记录ID,数据类型为Long")
	private Long recogId;
	     
	@ApiModelProperty(value = "拍照认证ID,数据类型为Long")
	private Long videoId;
	       
	@ApiModelProperty(value = "离床登记ID,数据类型为Long")
	private Long outId;
	       
	@ApiModelProperty(value = "申诉记录ID,数据类型为Long")
	private Long appealId;
	   
	@ApiModelProperty(value = "认证结果（10未认证、11认证已通过、12认证未通过）")
	private Integer recogResult;

	@ApiModelProperty(value = "申诉结果（30申诉中、32申诉已通过、33申诉未通过）")
	private Integer appealResult;
	   
	@ApiModelProperty(value = "审批结果（20未审核、21审核已通过、22审核未通过）")
	private Integer auditResult;
	       
	@ApiModelProperty(value = "当前状态（10未认证、11认证已通过、12认证未通过、13逾期未完成；20未审核、21审核已通过、22审核未通过；30申诉中、31申诉已通过、32申诉未通过）")
	private Integer status;
	      
	@ApiModelProperty(value = "机构id,数据类型为Long")
	private Long orgId;
	      
	@ApiModelProperty(value = "系统编号")
	private Integer sysCode;
	  
	@ApiModelProperty(value = "统筹区编码")
	private String zoneCode;
	      
	@ApiModelProperty(value = "险种/业务（310医保、330居民医保、410工伤、510生育）")
	private String busiType;

	@ApiModelProperty(value = "科室名称")
	private String departName;
	
	
	@ApiModelProperty(value = "性别")
	private String sex;
    
	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "诊断/疾病名称")
	private String diagnosis;

	@ApiModelProperty(value = "入院时间")
	private Date inDate;

	@ApiModelProperty(value = "出院时间")
	private Date outDate;

	@ApiModelProperty(value = "照片地址")
	@UrlMapping
	private String photoAddress;
	
	@ApiModelProperty(value = "当前状态名（未认证、认证已通过、认证未通过、逾期未完成；未审核、审核已通过、审核未通过；申诉中、申诉已通过、申诉未通过")
	private String statusName;
	
	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Integer getRecogBusi() {
		return recogBusi;
	}

	public void setRecogBusi(Integer recogBusi) {
		this.recogBusi = recogBusi;
	}

	public Integer getRecogType() {
		return recogType;
	}

	public void setRecogType(Integer recogType) {
		this.recogType = recogType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
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

	public Long getRecogId() {
		return recogId;
	}

	public void setRecogId(Long recogId) {
		this.recogId = recogId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getOutId() {
		return outId;
	}

	public void setOutId(Long outId) {
		this.outId = outId;
	}

	public Long getAppealId() {
		return appealId;
	}

	public void setAppealId(Long appealId) {
		this.appealId = appealId;
	}

	public Integer getRecogResult() {
		return recogResult;
	}

	public void setRecogResult(Integer recogResult) {
		this.recogResult = recogResult;
	}

	public Integer getAppealResult() {
		return appealResult;
	}

	public void setAppealResult(Integer appealResult) {
		this.appealResult = appealResult;
	}

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
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

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
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

	public String getPhotoAddress() {
		return photoAddress;
	}

	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
		
}
