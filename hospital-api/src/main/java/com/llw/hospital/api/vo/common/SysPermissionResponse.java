package com.llw.hospital.api.vo.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "当前权限列表响应")
public class SysPermissionResponse {
    @ApiModelProperty(value = "权限id")
    private Long permissionId;
    @ApiModelProperty(value = "权限名称")
    private String name;
    @ApiModelProperty(value = "权限标识")
    private String permissionValue;
    @ApiModelProperty(value = "权限图标")
    private String icon;
    @ApiModelProperty(value = "可跳转地址")
    @JSONField(serialzeFeatures= {SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty})
    private String uri;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
