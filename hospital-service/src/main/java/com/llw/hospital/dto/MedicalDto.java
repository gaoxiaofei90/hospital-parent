package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

/**
 * 住院信息表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-04 11:08:15
 */
public class MedicalDto extends BaseDto {

	/**
	 * 就诊ID
	 */
	private Long medicalId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 住院号/就诊号
	 */
	private String medicalNo;
	/**
	 * 统筹区编码
	 */
	private String zoneCode;
	/**
	 * 就诊类型（1住院、2门诊、3门特、4购药）
	 */
	private Integer medicalType;
	/**
	 * 数据同步标示
	 */
	private Long synchStatus;
	/**
	 * 出院时间
	 */
	private Date outDate;
	/**
	 * 机构ID
	 */
	private Long orgId;

	/**
	 * 床号
	 */
	private String bedNo;
	/**
	 * 诊断/疾病名称
	 */
	private String diagnosis;
	/**
	 * 科室ID
	 */
	private Long departId;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 入院时间
	 */
	private Date inDate;
	/**
	 * 人员类别（1医保人员、2非医保人员）
	 */
	private String personType;
	/**
	 * 身份证号码
	 */
	private String idcard;
	/**
	 * 人员ID
	 */
	private Long personId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 系统编号
	 */
	private Integer sysCode;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 当前状态（1在院、2出院）
	 */
	private Integer status;
	/**
	 * 险种/业务（310医保、330居民医保、410工伤、510生育）
	 */
	private String busiType;
	/**
	 * 医院ID
	 */
	private Long hospitalId;

	private String hospitalName;

	private String departName;
	/**
	 * 数据库无此，入院时间
	 */
	private String inDateStr;
	/**
	 * 数据库无此，出院时间
	 */
	private String outDateStr;

	/**
	 * 数据库无此，源人员编码
	 */
	private String personCode;

	private String photoAddress;
	
	private String idcardPhoto;

	/**
	 * 出生日期
	 */
	private Date birthday;

	
	private String warnLabel;
	/**
	 * 转办时间
	 */
	@Column(name = "INSUR_DATE")
	private Date insurDate;
	
	/**
	 * 医院编码
	 */
	private String hospitalCode;
	/**
	 * 部门编码
	 */
	private String departCode;
	
	
	private String sicard;
	

	/**
	 *撤销标志预留(1已撤销)，备用
	 */
	private Integer revokeFlag;
	
	
	/**
	 * 手动添加入院时间
	 */
	private Date inDate0;
	
	/**
	 * 手动添加出院时间
	 */
	private Date outDate0;
	


	/**
	 *数据来源（1录入、2同步）
	 */
	private Integer dataFrom;
	
	/**
	 * 参保统筹区（1本省、2本市、3外省、4省本级）
	 */
	private Integer personFrom;
	
	
	/**
	 * 预警状态(0未预警、1已预警)
	 */
	private Integer warningStatus;
	
	/**
	 * 请假状态(0未请假、1请假待生效、2请假生效中)
	 */
	private Integer leaveStatus;
	
	
	private Long faceExist; 
	
	/**
	 * 查询关键字
	 */
	private String keyword;
	
	/**
	 * 业务名称
	 */
	private String busiName;
	
	
	/**
	 * 认证状态（0、未认证；1、认证成功；2、认证失败）
	 */
	private Integer recogStatus;
	
	
	
	public Integer getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(Integer dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	/**
	 * 设置：就诊ID
	 */
	public void setMedicalId(Long medicalId) {
		this.medicalId = medicalId;
	}

	/**
	 * 获取：就诊ID
	 */
	public Long getMedicalId() {
		return medicalId;
	}

	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：住院号/就诊号
	 */
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}

	/**
	 * 获取：住院号/就诊号
	 */
	public String getMedicalNo() {
		return medicalNo;
	}

	/**
	 * 设置：统筹区编码
	 */
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	/**
	 * 获取：统筹区编码
	 */
	public String getZoneCode() {
		return zoneCode;
	}

	/**
	 * 设置：就诊类型（1住院、2门诊、3门特、4购药）
	 */
	public void setMedicalType(Integer medicalType) {
		this.medicalType = medicalType;
	}

	/**
	 * 获取：就诊类型（1住院、2门诊、3门特、4购药）
	 */
	public Integer getMedicalType() {
		return medicalType;
	}

	/**
	 * 设置：数据同步标示
	 */
	public void setSynchStatus(Long synchStatus) {
		this.synchStatus = synchStatus;
	}

	/**
	 * 获取：数据同步标示
	 */
	public Long getSynchStatus() {
		return synchStatus;
	}

	/**
	 * 设置：出院时间
	 */
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	/**
	 * 获取：出院时间
	 */
	public Date getOutDate() {
		return outDate;
	}

	/**
	 * 设置：机构ID
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取：机构ID
	 */
	public Long getOrgId() {
		return orgId;
	}

	/**
	 * 设置：床号
	 */
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	/**
	 * 获取：床号
	 */
	public String getBedNo() {
		return bedNo;
	}

	/**
	 * 设置：诊断/疾病名称
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * 获取：诊断/疾病名称
	 */
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * 设置：科室ID
	 */
	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	/**
	 * 获取：科室ID
	 */
	public Long getDepartId() {
		return departId;
	}

	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置：入院时间
	 */
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	/**
	 * 获取：入院时间
	 */
	public Date getInDate() {
		return inDate;
	}

	/**
	 * 设置：人员类别（1医保人员、2非医保人员）
	 */
	public void setPersonType(String personType) {
		this.personType = personType;
	}

	/**
	 * 获取：人员类别（1医保人员、2非医保人员）
	 */
	public String getPersonType() {
		return personType;
	}

	/**
	 * 设置：身份证号码
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	/**
	 * 获取：身份证号码
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * 设置：人员ID
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * 获取：人员ID
	 */
	public Long getPersonId() {
		return personId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSysCode() {
		return sysCode;
	}

	public void setSysCode(Integer sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 设置：险种/业务（310医保、330居民医保、410工伤、510生育）
	 */
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	/**
	 * 获取：险种/业务（310医保、330居民医保、410工伤、510生育）
	 */
	public String getBusiType() {
		return busiType;
	}

	/**
	 * 设置：医院ID
	 */
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
	 * 获取：医院ID
	 */
	public Long getHospitalId() {
		return hospitalId;
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

	public String getInDateStr() {
		return inDateStr;
	}

	public void setInDateStr(String inDateStr) {
		this.inDateStr = inDateStr;
	}

	public String getOutDateStr() {
		return outDateStr;
	}

	public void setOutDateStr(String outDateStr) {
		this.outDateStr = outDateStr;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPhotoAddress() {
		return photoAddress;
	}

	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getInsurDate() {
		return insurDate;
	}

	public void setInsurDate(Date insurDate) {
		this.insurDate = insurDate;
	}

	public String getWarnLabel() {
		return warnLabel;
	}

	public void setWarnLabel(String warnLabel) {
		this.warnLabel = warnLabel;
	}

	public String getIdcardPhoto() {
		return idcardPhoto;
	}

	public void setIdcardPhoto(String idcardPhoto) {
		this.idcardPhoto = idcardPhoto;
	}

	public String getSicard() {
		return sicard;
	}

	public void setSicard(String sicard) {
		this.sicard = sicard;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRevokeFlag() {
		return revokeFlag;
	}

	public void setRevokeFlag(Integer revokeFlag) {
		this.revokeFlag = revokeFlag;
	}

	public Date getInDate0() {
		return inDate0;
	}

	public void setInDate0(Date inDate0) {
		this.inDate0 = inDate0;
	}

	public Date getOutDate0() {
		return outDate0;
	}

	public void setOutDate0(Date outDate0) {
		this.outDate0 = outDate0;
	}

	public Integer getPersonFrom() {
		return personFrom;
	}

	public void setPersonFrom(Integer personFrom) {
		this.personFrom = personFrom;
	}

	public Integer getWarningStatus() {
		return warningStatus;
	}

	public void setWarningStatus(Integer warningStatus) {
		this.warningStatus = warningStatus;
	}

	public Integer getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(Integer leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Long getFaceExist() {
		return faceExist;
	}

	public void setFaceExist(Long faceExist) {
		this.faceExist = faceExist;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public Integer getRecogStatus() {
		return recogStatus;
	}

	public void setRecogStatus(Integer recogStatus) {
		this.recogStatus = recogStatus;
	}
	

	
	
	
}
