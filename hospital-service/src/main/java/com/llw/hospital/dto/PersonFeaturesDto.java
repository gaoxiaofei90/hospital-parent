package com.llw.hospital.dto;

import java.util.Date;

import com.jcl.dto.BaseDto;

/**
 * 人员特性信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-04 17:49:49
 */
public class PersonFeaturesDto extends BaseDto {

	/**
	 * 特征ID
	 */
	private Long featuresId;
	/**
	 * 模板照ID
	 */
	private Long modelId;
	/**
	 * 特征库人员ID
	 */
	private Long personId;
	/**
	 * 生物类型（11人脸、12指静脉、15指纹）
	 */
	private Integer bioType;
	/**
	 * 算法主版本号
	 */
	private String majorVersion;
	/**
	 * 算法次版本号
	 */
	private String minorVersion;
	/**
	 * 特征值
	 */
	private byte[] features;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人ID
	 */
	private Long createStaff;
	/**
	 * 特征状态：10有效, 11无效
	 */
	private Integer status;

	public Long getFeaturesId() {
		return featuresId;
	}

	public void setFeaturesId(Long featuresId) {
		this.featuresId = featuresId;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Integer getBioType() {
		return bioType;
	}

	public void setBioType(Integer bioType) {
		this.bioType = bioType;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

	public String getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(String minorVersion) {
		this.minorVersion = minorVersion;
	}

	public byte[] getFeatures() {
		return features;
	}

	public void setFeatures(byte[] features) {
		this.features = features;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
