package com.llw.hospital.ds.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

/**
 * 录像拍照认证
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-04 17:49:49
 */
@Table(name = "t_video_recog")
public class VideoRecog {

	/**
	 * 拍照认证ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
	private Long videoId;

	@Column(name = "scene_id")
	private Long sceneId;

	/**
	 * 认证时间
	 */
	@Column(name = "recog_time")
	private Date recogTime;

	@Column(name = "RECOG_BUSI")
	private Integer recogBusi;

	@Column(name = "MEDICAL_ID")
	private Long medicalId;

	/**
	 * 认证照存放路径，格式["img1","img2","img3"]
	 */
	@Column(name = "video_address")
	private String videoAddress;
	/**
	 * 设备类型
	 */
	@Column(name = "device_type")
	private String deviceType;
	/**
	 * 设备编码
	 */
	@Column(name = "device_code")
	private String deviceCode;

	@Column(name = "reason")
	private String reason;

	/**
	 * 录入人
	 */
	@Column(name = "input_staff")
	private Long inputStaff;
	/**
	 * 录入时间
	 */
	@Column(name = "input_time")
	private Date inputTime;
	/**
	 * 审核人
	 */
	@Column(name = "audit_staff")
	private Long auditStaff;
	/**
	 * 审核时间
	 */
	@Column(name = "audit_time")
	private Date auditTime;
	/**
	 * 审核结果（0未审核、1审核已通过、2审核未通过）
	 */
	@Column(name = "audit_status")
	private Integer auditStatus;
	/**
	 * 审核批注
	 */
	@Column(name = "audit_desc")
	private String auditDesc;
	/**
 * 
 */
	@Column(name = "org_id")
	private Long orgId;

	/**
	 * 特征库人员ID
	 */
	@Column(name = "person_id")
	private Long personId;

	/**
	 * 系统编号
	 */
	@Column(name = "sys_code")
	private Integer sysCode;
	/**
	 * 统筹区编码
	 */
	@Column(name = "zone_code")
	private String zoneCode;
	/**
	 * 险种/业务（310医保、330居民医保、410工伤、510生育）
	 */
	@Column(name = "busi_type")
	private String busiType;

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Date getRecogTime() {
		return recogTime;
	}

	public void setRecogTime(Date recogTime) {
		this.recogTime = recogTime;
	}

	public String getVideoAddress() {
		return videoAddress;
	}

	public void setVideoAddress(String videoAddress) {
		this.videoAddress = videoAddress;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public Long getInputStaff() {
		return inputStaff;
	}

	public void setInputStaff(Long inputStaff) {
		this.inputStaff = inputStaff;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Long getAuditStaff() {
		return auditStaff;
	}

	public void setAuditStaff(Long auditStaff) {
		this.auditStaff = auditStaff;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public Integer getRecogBusi() {
		return recogBusi;
	}

	public void setRecogBusi(Integer recogBusi) {
		this.recogBusi = recogBusi;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(Long medicalId) {
		this.medicalId = medicalId;
	}

}
