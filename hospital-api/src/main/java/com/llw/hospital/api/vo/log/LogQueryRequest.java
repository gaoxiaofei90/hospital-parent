package com.llw.hospital.api.vo.log;

import com.llw.hospital.api.vo.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class LogQueryRequest extends BasePageRequest {

    @ApiModelProperty(value = "请求接口编号")
    private String fn;

    @ApiModelProperty(value = "调用开始时间 时间戳形式")
    private Date startTime;

    @ApiModelProperty(value = "调用结束时间 时间戳形式")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }
}
