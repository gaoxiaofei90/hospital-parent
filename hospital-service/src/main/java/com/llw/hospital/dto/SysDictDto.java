package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:23
 */
public class SysDictDto extends BaseDto{

	        /**
         * 
         */
    private String fieldName;
	        /**
         * 
         */
    private String fieldComment;
	        /**
         * 
         */
    private String fieldValue;
	        /**
         * 
         */
    private String valueComment;
	        /**
         * 
         */
    private String appFieldName;
	        /**
         * 
         */
    private Integer status;
	        /**
         * 
         */
    private Integer orders;
	
	        /**
         * 设置：
         */
        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }
        /**
         * 获取：
         */
        public String getFieldName() {
            return fieldName;
        }
	        /**
         * 设置：
         */
        public void setFieldComment(String fieldComment) {
            this.fieldComment = fieldComment;
        }
        /**
         * 获取：
         */
        public String getFieldComment() {
            return fieldComment;
        }
	        /**
         * 设置：
         */
        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }
        /**
         * 获取：
         */
        public String getFieldValue() {
            return fieldValue;
        }
	        /**
         * 设置：
         */
        public void setValueComment(String valueComment) {
            this.valueComment = valueComment;
        }
        /**
         * 获取：
         */
        public String getValueComment() {
            return valueComment;
        }
	        /**
         * 设置：
         */
        public void setAppFieldName(String appFieldName) {
            this.appFieldName = appFieldName;
        }
        /**
         * 获取：
         */
        public String getAppFieldName() {
            return appFieldName;
        }
	        /**
         * 设置：
         */
        public void setStatus(Integer status) {
            this.status = status;
        }
        /**
         * 获取：
         */
        public Integer getStatus() {
            return status;
        }
	        /**
         * 设置：
         */
        public void setOrders(Integer orders) {
            this.orders = orders;
        }
        /**
         * 获取：
         */
        public Integer getOrders() {
            return orders;
        }
	}
