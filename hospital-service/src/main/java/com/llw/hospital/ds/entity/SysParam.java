package com.llw.hospital.ds.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@Table(name = "sys_param")
public class SysParam {

/**
 * 
 */
@Id
private String paramName;
/**
 * 
 */
@Column(name = "param_value")
private String paramValue;
/**
 * 
 */
@Column(name = "param_desc")
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
