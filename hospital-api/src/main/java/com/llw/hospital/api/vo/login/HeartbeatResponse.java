package com.llw.hospital.api.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

@ApiModel(value = "HeartbeatResponse", description = "心跳响应")
public class HeartbeatResponse {

	@ApiModelProperty(value = "指令(heartbeat:心跳、restart:重启、shutdown:关机、upgrade:升级、authUpdate:授权码更新")
	private String cmdType;

	@ApiModelProperty(value = "后台服务器时间")
	private Long sysTime;

	@ApiModelProperty(value = "描述信息")
	private String message;
	
//	@ApiModelProperty(value = "工单ID")
//	private Long workId;

	@ApiModelProperty(notes = "扩展参数，json形式保存")
	private JSONObject extraParam;

	public String getCmdType() {
		return cmdType;
	}

	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}

	public Long getSysTime() {
		return sysTime;
	}

	public void setSysTime(Long sysTime) {
		this.sysTime = sysTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getExtraParam() {
		return extraParam;
	}

	public void setExtraParam(JSONObject extraParam) {
		this.extraParam = extraParam;
	}

//	public Long getWorkId() {
//		return workId;
//	}
//
//	public void setWorkId(Long workId) {
//		this.workId = workId;
//	}
//	
	

}