package com.llw.hospital.ds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

/**
 * 人员特性信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-04 17:49:49
 */
@Table(name = "t_person_features")
public class PersonFeatures {

	/**
	 * 特征ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
	private Long featuresId;
	/**
	 * 模板照ID
	 */
	@Column(name = "model_id")
	private Long modelId;
	/**
	 * 特征库人员ID
	 */
	@Column(name = "person_id")
	private Long personId;
	/**
	 * 生物类型（11人脸、12指静脉、15指纹）
	 */
	@Column(name = "bio_type")
	private Integer bioType;
	/**
	 * 算法主版本号
	 */
	@Column(name = "major_version")
	private String majorVersion;
	/**
	 * 算法次版本号
	 */
	@Column(name = "minor_version")
	private String minorVersion;
	/**
	 * 特征值
	 */
	@Column(name = "features")
	private byte[] features;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 创建人ID
	 */
	@Column(name = "create_staff")
	private Long createStaff;
	/**
	 * 特征状态：10有效, 11无效
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 设置：特征ID
	 */
	public void setFeaturesId(Long featuresId) {
		this.featuresId = featuresId;
	}

	/**
	 * 获取：特征ID
	 */
	public Long getFeaturesId() {
		return featuresId;
	}

	/**
	 * 设置：模板照ID
	 */
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	/**
	 * 获取：模板照ID
	 */
	public Long getModelId() {
		return modelId;
	}

	/**
	 * 设置：特征库人员ID
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * 获取：特征库人员ID
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * 设置：生物类型（11人脸、12指静脉、15指纹）
	 */
	public void setBioType(Integer bioType) {
		this.bioType = bioType;
	}

	/**
	 * 获取：生物类型（11人脸、12指静脉、15指纹）
	 */
	public Integer getBioType() {
		return bioType;
	}

	/**
	 * 设置：算法主版本号
	 */
	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

	/**
	 * 获取：算法主版本号
	 */
	public String getMajorVersion() {
		return majorVersion;
	}

	/**
	 * 设置：算法次版本号
	 */
	public void setMinorVersion(String minorVersion) {
		this.minorVersion = minorVersion;
	}

	/**
	 * 获取：算法次版本号
	 */
	public String getMinorVersion() {
		return minorVersion;
	}

	/**
	 * 设置：特征值
	 */
	public void setFeatures(byte[] features) {
		this.features = features;
	}

	/**
	 * 获取：特征值
	 */
	public byte[] getFeatures() {
		return features;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：创建人ID
	 */
	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	/**
	 * 获取：创建人ID
	 */
	public Long getCreateStaff() {
		return createStaff;
	}

	/**
	 * 设置：特征状态：10有效, 11无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：特征状态：10有效, 11无效
	 */
	public Integer getStatus() {
		return status;
	}
}
