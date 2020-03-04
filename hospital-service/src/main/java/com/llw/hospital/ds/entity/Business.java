package com.llw.hospital.ds.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;
/**
 * 险种/业务
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-08-26 17:30:44
 */
@Table(name = "SYS_BUSINESS")
public class Business {

/**
 * 业务类型
 */
@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
private String busiType;
/**
 * 业务名称
 */
@Column(name = "BUSI_NAME")
private String busiName;

/**
 * 设置：业务类型
 */
public void setBusiType(String busiType) {
    this.busiType = busiType;
}
/**
 * 获取：业务类型
 */
public String getBusiType() {
    return busiType;
}
/**
 * 设置：业务名称
 */
public void setBusiName(String busiName) {
    this.busiName = busiName;
}
/**
 * 获取：业务名称
 */
public String getBusiName() {
    return busiName;
}
}
