package com.llw.hospital.ds.entity;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * APP版本信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-24 10:50:47
 */
@Table(name = "SYS_APP_VERSION")
public class AppVersion {

	/**
	 * 版本ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
	private Long versionId;
	/**
	 * 版本号
	 */
	@Column(name = "VERSION")
	private String version;
	/**
	 * 发布时间
	 */
	@Column(name = "CREATE_DATE")
	private Date createDate;
	/**
	 * 备注
	 */
	@Column(name = "MARK")
	private String mark;
	/**
	 * 最新版本（0否，1是）
	 */
	@Column(name = "LASTEST")
	private Integer lastest;
	/**
	 * 设备类型（101安卓手机，102苹果手机）
	 */
	@Column(name = "DEVICE_TYPE")
	private String deviceType;

	/**
	 * 设置：版本ID
	 */
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	/**
	 * 获取：版本ID
	 */
	public Long getVersionId() {
		return versionId;
	}

	/**
	 * 设置：版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获取：版本号
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 设置：发布时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取：发布时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置：备注
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * 获取：备注
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * 设置：最新版本（0否，1是）
	 */
	public void setLastest(Integer lastest) {
		this.lastest = lastest;
	}

	/**
	 * 获取：最新版本（0否，1是）
	 */
	public Integer getLastest() {
		return lastest;
	}

	/**
	 * 设置：设备类型（101安卓手机，102苹果手机）
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * 获取：设备类型（101安卓手机，102苹果手机）
	 */
	public String getDeviceType() {
		return deviceType;
	}
}
