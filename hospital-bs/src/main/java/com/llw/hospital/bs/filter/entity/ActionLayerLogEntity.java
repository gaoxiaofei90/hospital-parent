package com.llw.hospital.bs.filter.entity;

public class ActionLayerLogEntity {
    public String sessionId;
    public String sessionObj;
    public Long userId;
    public String username;
    public String url;
    public String queryString;
    public String reqParam;
    public String respParam;
    public Long castTime;
    public String remoteAddress;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long orgId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionObj() {
        return sessionObj;
    }

    public void setSessionObj(String sessionObj) {
        this.sessionObj = sessionObj;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    public String getRespParam() {
        return respParam;
    }

    public void setRespParam(String respParam) {
        this.respParam = respParam;
    }

    public Long getCastTime() {
        return castTime;
    }

    public void setCastTime(Long castTime) {
        this.castTime = castTime;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
