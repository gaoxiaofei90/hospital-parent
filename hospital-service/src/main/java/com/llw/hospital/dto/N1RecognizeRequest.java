package com.llw.hospital.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class N1RecognizeRequest implements Serializable {
    @ApiModelProperty(notes = "设备类型")
    private String deviceType;

    @ApiModelProperty(notes = "设备编码")
    private String deviceCode;

    @ApiModelProperty(notes = "监控点编码")
    private String cameraIndexCode;

    @ApiModelProperty(notes= "mac 地址")
    private String mac;

    @ApiModelProperty(notes = "扩展参数，json形式保存")
    private JSONObject extraParam;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public JSONObject getExtraParam() {
        return extraParam;
    }

    public void setExtraParam(JSONObject extraParam) {
        this.extraParam = extraParam;
    }

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }
}
