package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public class SysParamDto extends BaseDto{

	        /**
         * 
         */
    private String paramName;
	        /**
         * 
         */
    private String paramValue;
	        /**
         * 
         */
    private String paramDesc;
	
	        /**
         * 设置：
         */
        public void setParamName(String paramName) {
            this.paramName = paramName;
        }
        /**
         * 获取：
         */
        public String getParamName() {
            return paramName;
        }
	        /**
         * 设置：
         */
        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }
        /**
         * 获取：
         */
        public String getParamValue() {
            return paramValue;
        }
	        /**
         * 设置：
         */
        public void setParamDesc(String paramDesc) {
            this.paramDesc = paramDesc;
        }
        /**
         * 获取：
         */
        public String getParamDesc() {
            return paramDesc;
        }
	}
