package com.llw.hospital.common.base.constants;

public enum CmdTypeConstants {

	heartbeat("heartbeat", "心跳"),
	
	authUpdate("authUpdate", "授权码更新"),

	restart("restart", "重启"),

	shutdown("shutdown", "关机"),

	upgrade("upgrade", "升级"),

	download("download", "升级包下载中"),

	downfail("downfail", "升级包下载失败"),

	downbreak("downbreak", "升级包下载失败"),

	downfinish("downfinish", "升级包下载完成"),

	upgradefinish("upgradefinish", "升级完成"),

	warning("warning", "预警信息上传"),

	logup("logup", "日志文件上传"),

	logupno("logupno", "暂无日志"),

	logupfail("logupfail", "日志文件上传失败"),

	logupfinish("logupfinish", "日志文件上传成功");

	private String code;
	private String name;

	private CmdTypeConstants(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
