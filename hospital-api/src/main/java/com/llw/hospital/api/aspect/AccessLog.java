package com.llw.hospital.api.aspect;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel
public class AccessLog implements Serializable {

    @ApiModelProperty(value= "请求名称")
    private String fn;

    @ApiModelProperty(value = "请求数据")
    private String request;

    @ApiModelProperty(value = "响应数据")
    private String response;

    @ApiModelProperty(value="请求时间")
    private Date requestTime;

    @ApiModelProperty(value = "响应时间")
    private Date responseTime;

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }
}
