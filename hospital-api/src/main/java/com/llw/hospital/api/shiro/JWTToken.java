package com.llw.hospital.api.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author wendellpeng
 * @Title: JWTToken
 * @ProjectName gov-parent
 * @Description: JWTToken差不多就是Shiro用户名密码的载体,服务器无需保存用户状态，所以不需要RememberMe这类功能，我们简单的实现下AuthenticationToken接口即可
 * @date 2018/8/2719:53
 */
public class JWTToken implements AuthenticationToken {
    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
