package com.llw.hospital.api.vo.login;

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
public class LoginRequest extends RequestParam {
    @ApiModelProperty(value = "用户名" ,required = true)
    private String username;
    @ApiModelProperty(value = "密码" ,required = true)
    private String password;
    @ApiModelProperty(value="mac地址",required = true)
    private String mac;
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
    
    
}
