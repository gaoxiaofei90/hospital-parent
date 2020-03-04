package com.llw.hospital.api.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.alibaba.fastjson.JSONObject;
import com.llw.hospital.api.vo.RequestParam;

@ApiModel(value = "心跳需要传递的参数", description = "心跳需要传递的参数")
public class HeartbeatRequest extends RequestParam {

	@ApiModelProperty(value = "指令(heartbeat:心跳、restart:重启、shutdown:关机、upgrade:升级、authUpdate:授权码更新")
	private String cmdType;

	@ApiModelProperty(value = "设备类型（JXB309:体温枪,M320:POS终端M320,M321:POS终端M321,M323:POS终端M323,M324:POS终端M324,F120:双目人证通,F330:双屏,P310:P310,CF320:摄像机,P101:安卓手机,P102:苹果手机）")
	private String devType;

	@ApiModelProperty(value = "终端编码")
	private String devCode;

	@ApiModelProperty(value = "版本号")
	private String version;

	@ApiModelProperty(notes = "扩展参数，json形式保存")
	private JSONObject extraParam;

	@ApiModelProperty(value = "工单ID")
	private Long workId;

	@ApiModelProperty(value = "执行状态,0等执行、1执行成功、2执行失败")
	private Integer runStatus;

	public String getCmdType() {
		return cmdType;
	}

	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public JSONObject getExtraParam() {
		return extraParam;
	}

	public void setExtraParam(JSONObject extraParam) {
		this.extraParam = extraParam;
	}

	public Integer getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

}