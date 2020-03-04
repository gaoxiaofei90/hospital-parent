package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public class SysPermissionDto extends BaseDto{

	        /**
         * 
         */
    private Long permissionId;
	        /**
         * 
         */
    private Long pid;
	        /**
         * 
         */
    private String name;
	        /**
         * 类型 放入字典
         */
    private Integer type;
	        /**
         * 
         */
    private String permissionValue;
	        /**
         * 
         */
    private String uri;
	        /**
         * 
         */
    private String icon;
	        /**
         * 
         */
    private Integer orders;
	        /**
         * 字典 enable_status
         */
    private Integer enabled;

    private Integer modules;

    public Integer getModules() {
        return modules;
    }

    public void setModules(Integer modules) {
        this.modules = modules;
    }

    /**
         * 设置：
         */
        public void setPermissionId(Long permissionId) {
            this.permissionId = permissionId;
        }
        /**
         * 获取：
         */
        public Long getPermissionId() {
            return permissionId;
        }
	        /**
         * 设置：
         */
        public void setPid(Long pid) {
            this.pid = pid;
        }
        /**
         * 获取：
         */
        public Long getPid() {
            return pid;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
         * 设置：
         */
        public void setPermissionValue(String permissionValue) {
            this.permissionValue = permissionValue;
        }
        /**
         * 获取：
         */
        public String getPermissionValue() {
            return permissionValue;
        }
	        /**
         * 设置：
         */
        public void setUri(String uri) {
            this.uri = uri;
        }
        /**
         * 获取：
         */
        public String getUri() {
            return uri;
        }
	        /**
         * 设置：
         */
        public void setIcon(String icon) {
            this.icon = icon;
        }
        /**
         * 获取：
         */
        public String getIcon() {
            return icon;
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
	}
