package com.llw.hospital.api.vo.token;

import com.llw.hospital.api.vo.RequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wendellpeng
 * @Title: TokenRequest
 * @ProjectName gov-parent
 * @date 2018/9/4 10:02
 */
@ApiModel(value = "获取token需要传递的参数",description = "获取token需要传递的参数")
public class TokenRequest extends RequestParam {
    @ApiModelProperty(value = "appKey 接入账号请联系管理员获取" ,required = true)
    private String appKey;
    @ApiModelProperty(value = "appSecret 接入账号请联系管理员获取" ,required = true)
    private String appSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
