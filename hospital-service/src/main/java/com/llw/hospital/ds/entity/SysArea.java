package com.llw.hospital.ds.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:23
 */
@Table(name = "sys_area")
public class SysArea {

/**
 * 
 */
@Id
private String areaCode;
/**
 * 
 */
@Column(name = "p_code")
private String pCode;
/**
 * 
 */
@Column(name = "name")
private String name;
/**
 * 
 */
@Column(name = "org_level")
private Integer orgLevel;
/**
 * 
 */
@Column(name = "description")
private String description;
/**
 * 
 */
@Column(name = "path")
private String path;

    @Column(name = "pinyin")
    private String pinyin;


/**
 * 设置：
 */
public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
}
/**
 * 获取：
 */
public String getAreaCode() {
    return areaCode;
}
/**
 * 设置：
 */
public void setPCode(String pCode) {
    this.pCode = pCode;
}
/**
 * 获取：
 */
public String getPCode() {
    return pCode;
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

public Integer getOrgLevel() {
    return orgLevel;
}

public void setOrgLevel(Integer orgLevel) {
    this.orgLevel = orgLevel;
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
public void setPath(String path) {
    this.path = path;
}
/**
 * 获取：
 */
public String getPath() {
    return path;
}

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
