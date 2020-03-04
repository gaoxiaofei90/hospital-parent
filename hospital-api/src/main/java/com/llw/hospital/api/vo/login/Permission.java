package com.llw.hospital.api.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "Permission",description = "权限值")
public class Permission implements Serializable {
    @ApiModelProperty(value = "功能名称" ,required = true)
    private String name;
    @ApiModelProperty(value = "事件类型 native 或者 h5" ,required = true)
    private String actionType;
    @ApiModelProperty(value = "事件链接 如果actionType=h5则代表url地址，如果为native则为本地路径" ,required = true)
    private String uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
