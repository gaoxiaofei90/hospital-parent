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
 * 人员证明材料
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-11-27 17:01:45
 */
@Table(name = "T_PERSON_DATA")
public class PersonData {

/**
 * 证明材料ID
 */
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
private Long dataId;
/**
 * 创建时间
 */
@Column(name = "CREATE_DATE")
private Date createDate;
/**
 * 操作员认证记录ID
 */
@Column(name = "RECOG_ID")
private Long recogId;
/**
 * 操作员留底照URL
 */
@Column(name = "RECOG_PIC")
private String recogPic;
/**
 * 模板照ID
 */
@Column(name = "MODEL_ID")
private Long modelId;
/**
 * 操作员姓名
 */
@Column(name = "USER_NAME")
private String userName;
/**
 * 材料URL
 */
@Column(name = "DATA_URL")
private String dataUrl;
/**
 * 操作员ID
 */
@Column(name = "USER_ID")
private Long userId;
/**
 * 人员ID
 */
@Column(name = "PERSON_ID")
private Long personId;

/**
 * 设置：证明材料ID
 */
public void setDataId(Long dataId) {
    this.dataId = dataId;
}
/**
 * 获取：证明材料ID
 */
public Long getDataId() {
    return dataId;
}
/**
 * 设置：创建时间
 */
public void setCreateDate(Date createDate) {
    this.createDate = createDate;
}
/**
 * 获取：创建时间
 */
public Date getCreateDate() {
    return createDate;
}
/**
 * 设置：操作员认证记录ID
 */
public void setRecogId(Long recogId) {
    this.recogId = recogId;
}
/**
 * 获取：操作员认证记录ID
 */
public Long getRecogId() {
    return recogId;
}
/**
 * 设置：操作员留底照URL
 */
public void setRecogPic(String recogPic) {
    this.recogPic = recogPic;
}
/**
 * 获取：操作员留底照URL
 */
public String getRecogPic() {
    return recogPic;
}
/**
 * 设置：模板照ID
 */
public void setModelId(Long modelId) {
    this.modelId = modelId;
}
/**
 * 获取：模板照ID
 */
public Long getModelId() {
    return modelId;
}
/**
 * 设置：操作员姓名
 */
public void setUserName(String userName) {
    this.userName = userName;
}
/**
 * 获取：操作员姓名
 */
public String getUserName() {
    return userName;
}
/**
 * 设置：材料URL
 */
public void setDataUrl(String dataUrl) {
    this.dataUrl = dataUrl;
}
/**
 * 获取：材料URL
 */
public String getDataUrl() {
    return dataUrl;
}
/**
 * 设置：操作员ID
 */
public void setUserId(Long userId) {
    this.userId = userId;
}
/**
 * 获取：操作员ID
 */
public Long getUserId() {
    return userId;
}
/**
 * 设置：人员ID
 */
public void setPersonId(Long personId) {
    this.personId = personId;
}
/**
 * 获取：人员ID
 */
public Long getPersonId() {
    return personId;
}
}
