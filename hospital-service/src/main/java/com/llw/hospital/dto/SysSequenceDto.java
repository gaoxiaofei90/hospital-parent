package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public class SysSequenceDto extends BaseDto{

	        /**
         * 序列关键字
         */
    private String key;
	        /**
         * 
         */
    private String name;
	        /**
         * 序列值
         */
    private Long value;
	        /**
         * 版本号
         */
    private Long version;
	
	        /**
         * 设置：序列关键字
         */
        public void setKey(String key) {
            this.key = key;
        }
        /**
         * 获取：序列关键字
         */
        public String getKey() {
            return key;
        }
	        /**
         * 设置：
         */
        public void setName(String name) {
            this.name = name;
        }
        /**
         * 获取：
         */
        public String getName() {
            return name;
        }
	        /**
         * 设置：序列值
         */
        public void setValue(Long value) {
            this.value = value;
        }
        /**
         * 获取：序列值
         */
        public Long getValue() {
            return value;
        }
	        /**
         * 设置：版本号
         */
        public void setVersion(Long version) {
            this.version = version;
        }
        /**
         * 获取：版本号
         */
        public Long getVersion() {
            return version;
        }
	}
