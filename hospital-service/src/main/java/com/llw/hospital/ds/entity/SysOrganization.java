package com.llw.hospital.ds.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构
 *
 * @author weixin
 * @email weixin@a-eye.com
 * @date 2019-01-08 09:15:23
 */
@Table(name = "sys_organization")
public class SysOrganization {

/**
 * 
 */
@Id
private Long orgId;
/**
 * 
 */
@Column(name = "user_id")
private Long userId;
/**
 * 
 */
@Column(name = "pid")
private Long pid;
/**
 * 
 */
@Column(name = "name")
private String name;
/**
 * 联系电话
 */
@Column(name = "phone")
private String phone;
/**
 * 负责人
 */
@Column(name = "contact")
private String contact;
/**
 * 
 */
@Column(name = "description")
private String description;
/**
 * 
 */
@Column(name = "enabled")
private Integer enabled;


/**
 *详细地址
 */
@Column(name = "address")
private String address;

@Column(name = "is_del")
private Integer isDel;


@Column(name="ctime")
private Date ctime;

@Column(name="update_time")
private Date updateTime;

@Column(name = "org_category")
private Integer orgCategory;

    
    
    
    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public Integer getOrgCategory() {
		return orgCategory;
	}

	public void setOrgCategory(Integer orgCategory) {
		this.orgCategory = orgCategory;
	}
    
    
    
}
