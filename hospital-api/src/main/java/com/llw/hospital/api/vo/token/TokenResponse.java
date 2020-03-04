package com.llw.hospital.api.vo.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wendellpeng
 * @Title: TokenResponse
 * @ProjectName gov-parent
 * @date 2018/9/4 11:16
 */
@ApiModel(value = "TokenResponse",description = "获取token响应")
public class TokenResponse{

    public TokenResponse(String token,Integer effectiveTime) {
        this.token = token;
        this.effectiveTime = effectiveTime;
    }

    @ApiModelProperty(value = "token值" ,required = true)
    private String token;

    @ApiModelProperty(required = true,value = "有效时长，单位为小时")
    private Integer effectiveTime;

    public Integer getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Integer effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
