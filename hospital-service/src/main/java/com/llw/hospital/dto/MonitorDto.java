package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;
import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-11 10:30:27
 */
public class MonitorDto extends BaseDto{

	        /**
         * 
         */
    private Long monitorId;
	        /**
         * 
         */
    private Date createTime;
	        /**
         * 
         */
    private String mark;
	        /**
         * 
         */
    private String deviceCode;
	        /**
         * 
         */
    private String deviceType;
	
	        /**
         * 设置：
         */
        public void setMonitorId(Long monitorId) {
            this.monitorId = monitorId;
        }
        /**
         * 获取：
         */
        public Long getMonitorId() {
            return monitorId;
        }
	        /**
         * 设置：
         */
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
        /**
         * 获取：
         */
        public Date getCreateTime() {
            return createTime;
        }
	        /**
         * 设置：
         */
        public void setMark(String mark) {
            this.mark = mark;
        }
        /**
         * 获取：
         */
        public String getMark() {
            return mark;
        }
	        /**
         * 设置：
         */
        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }
        /**
         * 获取：
         */
        public String getDeviceCode() {
            return deviceCode;
        }
	        /**
         * 设置：
         */
        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }
        /**
         * 获取：
         */
        public String getDeviceType() {
            return deviceType;
        }
	}
