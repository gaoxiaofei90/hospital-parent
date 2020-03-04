package com.llw.hospital.api.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value = "MsgResponse", description = "消息列表")
public class MsgResponse {

	@ApiModelProperty(value = "消息ID")
	private Long msgId;

	@ApiModelProperty(value = "消息标题")
	private String msgTitle;

	@ApiModelProperty(value = "消息内容")
	private String content;

	@ApiModelProperty(value = "消息时间")
	private Date createTime;

	@ApiModelProperty(value = "是否已读（0未读，1已读）")
	private String status;

	@ApiModelProperty(value = "抽查险种（310医保、330居民医保、410工伤、510生育）")
	private String busiType;

	@ApiModelProperty(value = "对象ID")
	private Long busiId;

	@ApiModelProperty(value = " 机构ID")
	private Long orgId;

	@ApiModelProperty(value = "操作类型（1审核消息，2抽查消息）")
	private String operateType;
	
	public Long getMsgId() {
		return msgId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

}
