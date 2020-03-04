package com.llw.hospital.dto;

import java.util.Date;

import com.jcl.dto.BaseDto;

/**
 * 录像拍照认证
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-04 17:49:49
 */
public class VideoRecogDto extends BaseDto {

	/**
	 * 拍照认证ID
	 */
	private Long videoId;

	private Long sceneId;

	private Long planId;

	private String reason;

	private Integer recogBusi;

	private Long medicalId;

	/**
	 * 认证时间
	 */
	private Date recogTime;
	/**
	 * 认证照存放路径，格式["img1","img2","img3"]
	 */
	private String videoAddress;
	/**
	 * 设备类型
	 */
	private String deviceType;
	/**
	 * 设备编码
	 */
	private String deviceCode;
	/**
	 * 录入人
	 */
	private Long inputStaff;
	/**
	 * 录入时间
	 */
	private Date inputTime;
	/**
	 * 审核人
	 */
	private Long auditStaff;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 审核结果（20未审核、21审核已通过、22审核未通过）
	 */
	private Integer auditStatus;
	/**
	 * 审核批注
	 */
	private String auditDesc;
	/**
         * 
         */
	private Long orgId;

	/**
	 * 系统编号
	 */
	private Integer sysCode;
	/**
	 * 统筹区编码
	 */
	private String zoneCode;
	/**
	 * 险种/业务（310医保、330居民医保、410工伤、510生育）
	 */
	private String busiType;

	/**
	 * 人员ID
	 */
	private Long personId;

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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
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
