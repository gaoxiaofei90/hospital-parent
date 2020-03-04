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
public class SysRoleDto extends BaseDto{

    /**
         * 
         */
    private Long roleId;
	        /**
         * 
         */
    private Long orgId;
	        /**
         * 
         */
    private String name;
	        /**
         * 
         */
    private String title;
	        /**
         * 
         */
    private String description;
	        /**
         * 
         */
    private Date ctime;
	        /**
         * 
         */
    private String createUser;
	        /**
         * 字典 enable_status
         */
    private Integer enabled;

    private Integer roleType;
	
	        /**
         * 设置：
         */
        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }
        /**
         * 获取：
         */
        public Long getRoleId() {
            return roleId;
        }
	        /**
         * 设置：
         */
        public void setOrgId(Long orgId) {
            this.orgId = orgId;
        }
        /**
         * 获取：
         */
        public Long getOrgId() {
            return orgId;
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
         * 设置：
         */
        public void setTitle(String title) {
            this.title = title;
        }
        /**
         * 获取：
         */
        public String getTitle() {
            return title;
        }
	        /**
         * 设置：
         */
        public void setDescription(String description) {
            this.description = description;
        }
        /**
         * 获取：
         */
        public String getDescription() {
            return description;
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
         * 设置：
         */
        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }
        /**
         * 获取：
         */
        public String getCreateUser() {
            return createUser;
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

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}
