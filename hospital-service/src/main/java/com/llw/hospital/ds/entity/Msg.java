package com.llw.hospital.ds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

@Table(name = "t_msg")
public class Msg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
	private Long MsgId;

	@Column(name = "msg_title")
	private String MsgTitle;

	@Column(name = "content")
	private String content;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "status")
	private Integer status;

	@Column(name = "busi_type")
	private String busiType;

	@Column(name = "busi_id")
	private Long busiId;

	@Column(name = "org_id")
	private Long orgId;

	@Column(name = "sys_code")
	private Integer sysCode;

	@Column(name = "zone_code")
	private String zoneCode;

	@Column(name = "operate_type")
	private String operateType;
	
	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}

	public String getMsgTitle() {
		return MsgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		MsgTitle = msgTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public Long getBusiId() {
		return busiId;
	}

	public void setBusiId(Long busiId) {
		this.busiId = busiId;
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

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

}
