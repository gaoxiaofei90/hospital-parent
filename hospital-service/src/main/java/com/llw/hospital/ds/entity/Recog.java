package com.llw.hospital.ds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

/**
 * 认证记录
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-04 17:49:50
 */
@Table(name = "t_recog")
public class Recog {

	/**
	 * 认证记录ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
	private Long recogId;

	/**
	 * 认证时间
	 */
	@Column(name = "recog_time")
	private Date recogTime;
	/**
	 * 生物类型（11人脸、12指静脉、15指纹）
	 */
	@Column(name = "bio_type")
	private String bioType;

	/**
	 * 认证照存放路径，格式["img1","img2","img3"]
	 */
	@Column(name = "recog_address")
	private String recogAddress;
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
	/**
	 * 创建人
	 */
	@Column(name = "create_staff")
	private String createStaff;
	/**
	 * 认证结果（1通过、2未通过）
	 */
	@Column(name = "recog_result")
	private Long recogResult;
	
	
	@Column(name = "recog_type")
	private Long recogType;
	/**
	 * 认证分值
	 */
	@Column(name = "recog_score")
	private Float recogScore;
	/**
 *
 */
	@Column(name = "org_id")
	private Long orgId;
	/**
	 * 体温
	 */
	@Column(name = "extra_param")
	private String extraParam;

	@Column(name = "recog_busi")
	private Long recogBusi;

	/**
	 * 特征库人员ID
	 */
	@Column(name = "person_id")
	private Long personId;

	/**
	 * 系统编号
	 */
	@Column(name = "sys_code")
	private Long sysCode;
	/**
	 * 统筹区编码
	 */
	@Column(name = "zone_code")
	private String zoneCode;
	/**
	 * 险种（310职工医保、390居民医保）
	 */
	@Column(name = "busi_type")
	private String busiType;

	/**
	 * 业务场景ID
	 */
	@Column(name = "scene_id")
	private Long sceneId;

	/**
	 * 足迹ID
	 */
	@Column(name ="trail_id")
	private Long trailId;

	@Column(name = "background_url")
	private String backgroundUrl;

	/**
	 * 是否为离线认证，0否，1是
	 */
	@Column(name = "offline_recog")
	private Long offlineRecog;
	
	/**
	 * 就诊ID
	 */
	@Column(name ="medical_id")
	private Long medicalId;

	
	/**
	 * 是否为离线认证，0否，1是
	 */
	@Column(name = "picture_id")
	private Long pictureId;
	
	@Column(name ="create_time")
	private Date createTime;
	
	/**
	 * 治疗项目编码
	 */
	@Column(name ="cure_item")
	private String cureItem;
	
	/**
	 * 就诊类型（1住院、2普通门诊、31门特治疗、32门特购药、33特药治疗、34特药购药、4普通购药）
	 */
	@Column(name ="medical_type")
	private Long medicalType;
	
	
	/**
	 * 认证方式（10、人脸核验；11、特殊建模，12、特殊登记）
	 */
	@Column(name = "recog_mode")
	private Long recogMode;
	
	

	public Long getRecogId() {
		return recogId;
	}

	public void setRecogId(Long recogId) {
		this.recogId = recogId;
	}

	public Date getRecogTime() {
		return recogTime;
	}

	public void setRecogTime(Date recogTime) {
		this.recogTime = recogTime;
	}

	public String getBioType() {
		return bioType;
	}

	public void setBioType(String bioType) {
		this.bioType = bioType;
	}

	public String getRecogAddress() {
		return recogAddress;
	}

	public void setRecogAddress(String recogAddress) {
		this.recogAddress = recogAddress;
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

	public String getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}

	public Long getRecogResult() {
		return recogResult;
	}

	public void setRecogResult(Long recogResult) {
		this.recogResult = recogResult;
	}

	public Long getRecogType() {
		return recogType;
	}

	public void setRecogType(Long recogType) {
		this.recogType = recogType;
	}

	public Float getRecogScore() {
		return recogScore;
	}

	public void setRecogScore(Float recogScore) {
		this.recogScore = recogScore;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getExtraParam() {
		return extraParam;
	}

	public void setExtraParam(String extraParam) {
		this.extraParam = extraParam;
	}

	public Long getRecogBusi() {
		return recogBusi;
	}

	public void setRecogBusi(Long recogBusi) {
		this.recogBusi = recogBusi;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getSysCode() {
		return sysCode;
	}

	public void setSysCode(Long sysCode) {
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

	public Long getTrailId() {
		return trailId;
	}

	public void setTrailId(Long trailId) {
		this.trailId = trailId;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public Long getOfflineRecog() {
		return offlineRecog;
	}

	public void setOfflineRecog(Long offlineRecog) {
		this.offlineRecog = offlineRecog;
	}

	public Long getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(Long medicalId) {
		this.medicalId = medicalId;
	}

	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCureItem() {
		return cureItem;
	}

	public void setCureItem(String cureItem) {
		this.cureItem = cureItem;
	}

	public Long getMedicalType() {
		return medicalType;
	}

	public void setMedicalType(Long medicalType) {
		this.medicalType = medicalType;
	}

	public Long getRecogMode() {
		return recogMode;
	}

	public void setRecogMode(Long recogMode) {
		this.recogMode = recogMode;
	}
	
	
	
	
}
