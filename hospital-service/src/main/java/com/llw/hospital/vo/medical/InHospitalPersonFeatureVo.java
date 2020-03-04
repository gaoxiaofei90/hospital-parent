package com.llw.hospital.vo.medical;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class InHospitalPersonFeatureVo extends InHospitalPersonVo {
    @ApiModelProperty(value = "特征数据，base64编码返回")
    private byte[] feature;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFeature() {
        return feature;
    }

    public void setFeature(byte[] feature) {
        this.feature = feature;
    }
}
