package com.llw.hospital.dto;


import com.jcl.dto.BaseDto;
import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-09-10 15:58:44
 */
public class SysDeviceTypeDto extends BaseDto{

	        /**
         * 设备类型
         */
    private String deviceType;
	        /**
         * 设备名称
         */
    private String deviceName;
	        /**
         * 设备大类1：终端设备、2网络摄像机
         */
    private Long deviceLargeType;
	
	        /**
         * 设置：设备类型
         */
        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }
        /**
         * 获取：设备类型
         */
        public String getDeviceType() {
            return deviceType;
        }
	        /**
         * 设置：设备名称
         */
        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }
        /**
         * 获取：设备名称
         */
        public String getDeviceName() {
            return deviceName;
        }
	        /**
         * 设置：设备大类1：终端设备、2网络摄像机
         */
        public void setDeviceLargeType(Long deviceLargeType) {
            this.deviceLargeType = deviceLargeType;
        }
        /**
         * 获取：设备大类1：终端设备、2网络摄像机
         */
        public Long getDeviceLargeType() {
            return deviceLargeType;
        }
	}
