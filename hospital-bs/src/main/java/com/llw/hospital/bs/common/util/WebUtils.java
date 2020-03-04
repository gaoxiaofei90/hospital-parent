package com.llw.hospital.bs.common.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils extends org.springframework.web.util.WebUtils{
    /**
     * 获取远程访问IP
     *
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        if (request != null) {
            String remoteIp = request.getHeader(FilterConstants.X_FORWARDED_FOR_HEADER);
            if (StringUtils.isEmpty(remoteIp) || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader(FilterConstants.X_REAL_IP);
            }
            if (StringUtils.isEmpty(remoteIp) || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader(FilterConstants.PROXY_CLIENT_IP);
            }
            if (StringUtils.isEmpty(remoteIp) || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader(FilterConstants.WL_PROXY_CLIENT_IP);
            }
            if (StringUtils.isEmpty(remoteIp) || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader(FilterConstants.HTTP_CLIENT_IP);
            }
            if (StringUtils.isEmpty(remoteIp) || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getHeader(FilterConstants.HTTP_X_FORWARDED_FOR);
            }
            if (StringUtils.isEmpty(remoteIp) || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getRemoteAddr();
            }
            if (StringUtils.isEmpty(remoteIp) || "unknown".equalsIgnoreCase(remoteIp)) {
                remoteIp = request.getRemoteHost();
            }
            try {
                if (remoteIp != null && remoteIp.contains(",")) {
                    return remoteIp.split(",")[0].trim();
                }
            } catch (Exception e) {

            }
            return remoteIp;
        }
        return null;
    }

    /**
     * 获得服务器网关父domain
     */
    public static String getGatewayParentDomain(HttpServletRequest request) {
        String domain = getGatewayDomain(request);
        if (StringUtils.isNotBlank(domain) && domain.contains(".")) {
            domain = domain.substring(domain.indexOf(".") + 1);
        }
        return domain;
    }

    /**
     * 获得服务器网关domain
     * @param request
     * @return
     * 返回格式 http://xxx.xxx.xxx:8888
     */
    public static String getGatewayDomain(HttpServletRequest request) {
        String host = request.getServerName();
        String protocol = request.getScheme().toLowerCase();
        String xProtocol = request.getHeader(FilterConstants.X_FORWARDED_PROTO_HEADER);
        if(!StringUtils.isAnyBlank(xProtocol)){
            protocol = xProtocol;
        }
        String xHost = request.getHeader(FilterConstants.X_FORWARDED_HOST_HEADER);
        if(StringUtils.isNotBlank(xHost)){
            host = xHost;
        }
        StringBuilder builder = new StringBuilder(protocol);
        builder.append("://");
        builder.append(host);
        if(!host.contains(":")){
            int port = request.getServerPort();
            String xPort = request.getHeader(FilterConstants.X_FORWARDED_PORT_HEADER);
            if(StringUtils.isNotBlank(xPort)){
                try {
                    port = Integer.valueOf(xPort);
                }catch (Exception e){

                }
            }
            if (port != 80) {
                builder.append(":");
                builder.append(port);
            }
        }
        return builder.toString();
    }

    /**
     * NAME=VALUE；Expires=DATE；Path=PATH；Domain=DOMAIN_NAME；SECURE
     * @param response
     * @param cookie
     */
    public static final void addCookie(HttpServletResponse response, Cookie cookie){
        Assert.notNull(response, "HttpServletResponse must not be null");
        Assert.notNull(cookie, "Cookie must not be null");
        StringBuilder builder = new StringBuilder();
        builder.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        if(StringUtils.isNotBlank(cookie.getPath())){
            builder.append("path=").append(cookie.getPath()).append(";");
        }
        String domain = cookie.getDomain();
        if(StringUtils.isNotBlank(domain)){
            builder.append("domain=");
            if(!domain.startsWith(".")){
                builder.append(".");
            }
            builder.append(cookie.getDomain()).append(";");
        }
        //if(cookie.getMaxAge()>0){
        builder.append("Max-Age=").append(cookie.getMaxAge()).append(";");
        //}

        if(cookie.getSecure()){
            builder.append("Secure;");
        }
        /*if(cookie.isHttpOnly()){
            builder.append("HttpOnly;");
        }*/
        builder.deleteCharAt(builder.length()-1);
        response.addHeader("Set-Cookie",builder.toString());
    }

    /**
     * 获取当前的请求url
     * @param request
     * @return
     * 格式为 /xxxx?key1=value1&key2=value2
     */
    public static String getRequestURI(HttpServletRequest request){
        StringBuilder builder = new StringBuilder(request.getRequestURI().toString());
        String queryString = request.getQueryString();
        if (!StringUtils.isEmpty(queryString)) {
            builder.append("?");
            builder.append(queryString);
        }
        return builder.toString();
    }
    /**
     * 获取当前的请求url
     * @param request
     * @return
     * 格式为 http://www.xxxxx.com/xxxx?key1=value1&key2=value2
     */
    public static String getRequestURL(HttpServletRequest request){
        StringBuilder builder = new StringBuilder(getGatewayDomain(request));
        builder.append(getRequestURI(request));
        return builder.toString();
    }


    /**
     * 获取Cookie的值
     * @param request
     * @param name
     * @return
     */
    public static final String getCookieValue(HttpServletRequest request,String name){
        Cookie cookie = getCookie(request,name);
        if(cookie != null){
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 判断是否Ajax请求
     * @param httpServletRequest
     * @return
     */
    public static boolean isAjax(HttpServletRequest httpServletRequest) {
        return FilterConstants.XML_HTTP_REQUEST.equals(httpServletRequest.getHeader(FilterConstants.X_REQUESTED_WITH));
    }


    /**
     * 判断是不是json类型请求
     * @param savedRequest
     * @return
     */
    public static boolean isContentTypeJson(HttpServletRequest savedRequest) {
        return savedRequest.getHeader(FilterConstants.CONTENT_TYPE).contains(FilterConstants.CONTENT_TYPE_JSON);
    }

}
