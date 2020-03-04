package com.llw.hospital.ds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

/**
 * 住院信息表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-04 11:08:15
 */
@Table(name = "T_MEDICAL")
public class Medical {

	/**
	 * 就诊ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
	private Long medicalId;
	/**
	 * 姓名
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 住院号/就诊号
	 */
	@Column(name = "MEDICAL_NO")
	private String medicalNo;
	/**
	 * 统筹区编码
	 */
	@Column(name = "ZONE_CODE")
	private String zoneCode;
	/**
	 * 就诊类型（1住院、2门诊、3门特、4购药）
	 */
	@Column(name = "MEDICAL_TYPE")
	private Long medicalType;
	/**
	 * 数据同步标示
	 */
	@Column(name = "SYNCH_STATUS")
	private Long synchStatus;
	/**
	 * 出院时间
	 */
	@Column(name = "OUT_DATE")
	private Date outDate;
	/**
	 * 机构ID
	 */
	@Column(name = "ORG_ID")
	private Long orgId;

	/**
	 * 床号
	 */
	@Column(name = "BED_NO")
	private String bedNo;
	/**
	 * 诊断/疾病名称
	 */
	@Column(name = "DIAGNOSIS")
	private String diagnosis;
	/**
	 * 科室ID
	 */
	@Column(name = "DEPART_ID")
	private Long departId;
	/**
	 * 年龄
	 */
	@Column(name = "AGE")
	private Integer age;
	/**
	 * 入院时间
	 */
	@Column(name = "IN_DATE")
	private Date inDate;
	/**
	 * 人员类别（1医保人员、2非医保人员）
	 */
	@Column(name = "PERSON_TYPE")
	private String personType;
	/**
	 * 身份证号码
	 */
	@Column(name = "IDCARD")
	private String idcard;

	@Column(name = "SICARD")
	private String sicard;

	/**
	 * 人员ID
	 */
	@Column(name = "PERSON_ID")
	private Long personId;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	/**
	 * 系统编号
	 */
	@Column(name = "SYS_CODE")
	private Long sysCode;
	/**
	 * 性别
	 */
	@Column(name = "SEX")
	private String sex;
	/**
	 * 当前状态（1在院、2出院）
	 */
	@Column(name = "STATUS")
	private Long status;
	/**
	 * 险种/业务（310医保、330居民医保、410工伤、510生育）
	 */
	@Column(name = "BUSI_TYPE")
	private String busiType;
	/**
	 * 医院ID
	 */
	@Column(name = "HOSPITAL_ID")
	private Long hospitalId;

	/**
	 * 医院名称
	 */
	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;

	/**
	 * 医院名称
	 */
	@Column(name = "DEPART_NAME")
	private String departName;

	/**
	 * 转办时间
	 */
	@Column(name = "INSUR_DATE")
	private Date insurDate;

	/**
	 * 医院编码
	 */
	@Column(name = "HOSPITAL_CODE")
	private String hospitalCode;
	/**
	 * 部门编码
	 */
	@Column(name = "DEPART_CODE")
	private String departCode;

	
	/**
	 *撤销标志预留(1已撤销)，备用
	 */
	@Column(name = "REVOKE_FLAG")
	private Integer revokeFlag;
	
	
	
	/**
	 * 手动添加入院时间
	 */
	@Column(name = "IN_DATE0")
	private Date inDate0;
	
	/**
	 * 手动添加出院时间
	 */
	@Column(name = "OUT_DATE0")
	private Date outDate0;
	
	/**
	 * 数据来源 1 录入 2 同步
	 */
	@Column(name = "DATA_FROM")
	private Integer dataFrom;
	
	@Column(name = "PERSON_FROM")
	/**
	 * 参保统筹区（1本省、2本市、3外省、4省本级）
	 */
	private Integer personFrom;
	
	/**
	 * 预警状态(0未预警、1已预警)
	 */
	@Column(name = "WARNING_STATUS")
	private Integer warningStatus;
	
	/**
	 * 请假状态(0未请假、1请假待生效、2请假生效中)
	 */
	@Column(name = "LEAVE_STATUS")
	private Integer leaveStatus;
	
	/**
	 * 认证状态（0、未认证；1、认证成功；2、认证失败）
	 */
	@Column(name = "RECOG_STATUS")
	private Integer recogStatus;
	
	
	
	
	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
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
	public void setMedicalType(Long medicalType) {
		this.medicalType = medicalType;
	}

	/**
	 * 获取：就诊类型（1住院、2门诊、3门特、4购药）
	 */
	public Long getMedicalType() {
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

	/**
	 * 设置：系统编号
	 */
	public void setSysCode(Long sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * 获取：系统编号
	 */
	public Long getSysCode() {
		return sysCode;
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

	/**
	 * 设置：当前状态（1在院、2出院）
	 */
	public void setStatus(Long status) {
		this.status = status;
	}

	/**
	 * 获取：当前状态（1在院、2出院）
	 */
	public Long getStatus() {
		return status;
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

	public Date getInsurDate() {
		return insurDate;
	}

	public void setInsurDate(Date insurDate) {
		this.insurDate = insurDate;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSicard() {
		return sicard;
	}

	public void setSicard(String sicard) {
		this.sicard = sicard;
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

	public Integer getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(Integer dataFrom) {
		this.dataFrom = dataFrom;
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

	public Integer getRecogStatus() {
		return recogStatus;
	}

	public void setRecogStatus(Integer recogStatus) {
		this.recogStatus = recogStatus;
	}
	
	
	
	
	
	
	

}
