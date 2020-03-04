package com.llw.hospital.bs;

import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpmsCookie extends SimpleCookie {

    public UpmsCookie() {
    }

    public UpmsCookie(String name) {
        super(name);
    }

    public UpmsCookie(Cookie cookie) {
        super(cookie);
    }

    @Override
    public void saveTo(HttpServletRequest request, HttpServletResponse response) {
        super.saveTo(request,response);
    }
}
