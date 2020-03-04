package com.llw.hospital.ds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

@Table(name = "sys_APP_URL")
public class AppUrl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
	private Long urlId;

	@Column(name = "VERSION_ID")
	private Long versionId;

	@Column(name = "VERSION_URL")
	private String versionUrl;

	@Column(name = "URL_TYPE")
	private Integer urlType;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "mark")
	private String mark;

	public Long getUrlId() {
		return urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public String getVersionUrl() {
		return versionUrl;
	}

	public void setVersionUrl(String versionUrl) {
		this.versionUrl = versionUrl;
	}

	public Integer getUrlType() {
		return urlType;
	}

	public void setUrlType(Integer urlType) {
		this.urlType = urlType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

}
