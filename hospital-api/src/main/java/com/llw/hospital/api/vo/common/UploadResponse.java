package com.llw.hospital.api.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "上传文件返回参数",description = "上传文件返回参数")
public class UploadResponse {
    @ApiModelProperty(value = "文件存放位置")
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
