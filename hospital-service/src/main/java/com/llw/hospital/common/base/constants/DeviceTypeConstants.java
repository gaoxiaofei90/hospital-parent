package com.llw.hospital.common.base.constants;

public enum DeviceTypeConstants {

	JXB309("JXB309", "体温枪"),
	
	M320("M320", "M320"),
	M321("M321", "M321"),
	M323("M323", "M323"),
	M324("M324", "M324"),
	M330("M330", "M330"),

	F120("F120", "F120"),
	F330("F330", "F330"),
	
	P310("P310", "P310"), 
	
    CF320("CF320", "AI摄像机"),
    CF380("CF380", "FPGA摄像机"),
	CF330("CF330", "宝瑞明摄像机"),


	P101("P101", "安卓手机"), 
	P102("P102", "苹果手机"),
    
	MT001("MT001", "门特购药"),
	ZL001("ZL001", "检查治疗"),
	
	LLW("LLW", "老来网"); 
	
	private String code;
	private String deviceName;

	private DeviceTypeConstants(String code, String deviceName) {
		this.code = code;
		this.deviceName = deviceName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getCode() {
		return code;
	}

	public static String getDeviceText(String code) {
		for (DeviceTypeConstants type : DeviceTypeConstants.values()) {
			if (code.equals(type.getCode())) {
				return type.getDeviceName();
			}
		}
		return "";
	}

	public static String getDeviceTextByOrdinal(String ordinal) {
		for (DeviceTypeConstants type : DeviceTypeConstants.values()) {
			if (ordinal.equals(type.ordinal() + "")) {
				return type.getDeviceName();
			}
		}
		return "";
	}

	public static DeviceTypeConstants getDeviceByOrdinal(String ordinal) {
		for (DeviceTypeConstants type : DeviceTypeConstants.values()) {
			if (ordinal.equals(type.ordinal() + "")) {
				return type;
			}
		}
		return null;
	}

	public static boolean isCommonDevice(Integer ordinal) {
		return ordinal <= 1;
	}

}
