package com.llw.hospital.dto;

import java.util.Date;

import com.jcl.dto.BaseDto;
import com.llw.hospital.util.UrlMapping;

/**
 * 人员模板信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-04 17:49:49
 */
public class PersonModelDto extends BaseDto {

	/**
	 * 模板照ID
	 */
	private Long modelId;
	/**
	 * 特征库人员ID
	 */
	private Long personId;
	/**
	 * 建模方式（11专业设备建模、12手机拍照建模、13证件照、14照片建模，21生物特性库、22社保卡库、23公安库、24其它）
	 */
	private Integer createType;
	/**
	 * 照片存放路径
	 */
	@UrlMapping
	private String modelAddress;

	@UrlMapping
	private String idcardAddress;
	/**
	 * 生物类型（11人脸、12指静脉、15指纹）
	 */
	private Integer bioType;
	/**
	 * 专业设备类型
	 */
	private String deviceType;
	/**
	 * 专业设备编码
	 */
	private String deviceCode;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createStaff;
	/**
	 * 模板状态10有效, 11无效
	 */
	private Integer status;

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
	 * 设置：建模方式（11专业设备建模、12手机拍照建模、13证件照、14照片建模，21生物特性库、22社保卡库、23公安库、24其它）
	 */
	public void setCreateType(Integer createType) {
		this.createType = createType;
	}

	/**
	 * 获取：建模方式（11专业设备建模、12手机拍照建模、13证件照、14照片建模，21生物特性库、22社保卡库、23公安库、24其它）
	 */
	public Integer getCreateType() {
		return createType;
	}

	/**
	 * 设置：照片存放路径
	 */
	public void setModelAddress(String modelAddress) {
		this.modelAddress = modelAddress;
	}

	/**
	 * 获取：照片存放路径
	 */
	public String getModelAddress() {
		return modelAddress;
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
	 * 设置：专业设备类型
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * 获取：专业设备类型
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 设置：专业设备编码
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	/**
	 * 获取：专业设备编码
	 */
	public String getDeviceCode() {
		return deviceCode;
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
	 * 设置：创建人
	 */
	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	/**
	 * 获取：创建人
	 */
	public Long getCreateStaff() {
		return createStaff;
	}

	/**
	 * 设置：模板状态10有效, 11无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：模板状态10有效, 11无效
	 */
	public Integer getStatus() {
		return status;
	}

	public String getIdcardAddress() {
		return idcardAddress;
	}

	public void setIdcardAddress(String idcardAddress) {
		this.idcardAddress = idcardAddress;
	}

}
