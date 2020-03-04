package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

import java.util.Date;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public class SysUserDto extends BaseDto{

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
    private String password;
        /**
         * 
         */
    private String salt;
        /**
         * 头像地址
         */
    private String photo;
        /**
         * 字典 user_type
         */
    private Integer userType;
        /**
         * 
         */
    private Date ctime;
        /**
         * 字典 enable_status
         */
    private Integer enabled;

    private String phone;

    private Long orgId;

    private String name;

    private Date updateTime;

    private Integer isAdmin;

    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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
        public void setPassword(String password) {
            this.password = password;
        }
        /**
         * 获取：
         */
        public String getPassword() {
            return password;
        }
	        /**
         * 设置：
         */
        public void setSalt(String salt) {
            this.salt = salt;
        }
        /**
         * 获取：
         */
        public String getSalt() {
            return salt;
        }
	        /**
         * 设置：头像地址
         */
        public void setPhoto(String photo) {
            this.photo = photo;
        }
        /**
         * 获取：头像地址
         */
        public String getPhoto() {
            return photo;
        }
	        /**
         * 设置：字典 user_type
         */
        public void setUserType(Integer userType) {
            this.userType = userType;
        }
        /**
         * 获取：字典 user_type
         */
        public Integer getUserType() {
            return userType;
        }
	        /**
         * 设置：
         */
        public void setCtime(Date ctime) {
            this.ctime = ctime;
        }
        /**
         * 获取：
         */
        public Date getCtime() {
            return ctime;
        }
	        /**
         * 设置：字典 enable_status
         */
        public void setEnabled(Integer enabled) {
            this.enabled = enabled;
        }
        /**
         * 获取：字典 enable_status
         */
        public Integer getEnabled() {
            return enabled;
        }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
