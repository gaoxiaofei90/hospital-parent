package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

import java.util.Date;

/**
 * 登录日志
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:23
 */
public class SysLoginLogDto extends BaseDto{

	        /**
         * 
         */
    private Long loginId;
	        /**
         * 
         */
    private Long userId;
	        /**
         * 
         */
    private String username;
	        /**
         * 
         */
    private String ip;
	        /**
         * 
         */
    private String action;
	        /**
         * 时间
         */
    private Date createTime;
	
	        /**
         * 设置：
         */
        public void setLoginId(Long loginId) {
            this.loginId = loginId;
        }
        /**
         * 获取：
         */
        public Long getLoginId() {
            return loginId;
        }
	        /**
         * 设置：
         */
        public void setUserId(Long userId) {
            this.userId = userId;
        }
        /**
         * 获取：
         */
        public Long getUserId() {
            return userId;
        }
	        /**
         * 设置：
         */
        public void setUsername(String username) {
            this.username = username;
        }
        /**
         * 获取：
         */
        public String getUsername() {
            return username;
        }
	        /**
         * 设置：
         */
        public void setIp(String ip) {
            this.ip = ip;
        }
        /**
         * 获取：
         */
        public String getIp() {
            return ip;
        }
	        /**
         * 设置：
         */
        public void setAction(String action) {
            this.action = action;
        }
        /**
         * 获取：
         */
        public String getAction() {
            return action;
        }
	        /**
         * 设置：时间
         */
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
        /**
         * 获取：时间
         */
        public Date getCreateTime() {
            return createTime;
        }
	}
