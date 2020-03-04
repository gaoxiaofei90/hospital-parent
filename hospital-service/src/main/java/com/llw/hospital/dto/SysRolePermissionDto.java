package com.llw.hospital.dto;

import com.jcl.dto.BaseDto;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:23
 */
public class SysRolePermissionDto extends BaseDto{

	        /**
         * 
         */
    private Long permissionId;
	        /**
         * 
         */
    private Long roleId;
	
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
        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }
        /**
         * 获取：
         */
        public Long getRoleId() {
            return roleId;
        }
	}
