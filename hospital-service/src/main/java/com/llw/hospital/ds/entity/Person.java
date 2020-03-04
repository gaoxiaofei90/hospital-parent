package com.llw.hospital.ds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jcl.orm.tkmapper.keygenerator.LLwSelectKeyGenerator;

/**
 * 人员表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-02 16:53:38
 */
@Table(name = "T_PERSON")
public class Person {

	/**
	 * 人员ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = LLwSelectKeyGenerator.SNOWFLAKE)
	private Long personId;

	/**
	 * 姓名
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 身份证号码
	 */
	@Column(name = "IDCARD")
	private String idcard;

	/**
	 * 出生日期
	 */
	@Column(name = "BIRTHDAY")
	private Date birthday;

	/**
	 * 性别
	 */
	@Column(name = "SEX")
	private String sex;

	/**
	 * 添加时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;

	/**
	 * 人员类别（1医保人员、2非医保人员）
	 */
	@Column(name = "PERSON_TYPE")
	private String personType;

	@Column(name = "SICARD")
	private String sicard;

	@Column(name="SICARD_PHOTO")
	private String sicardPhoto;

	/**
	 * 特殊人员（指纹）
	 */
	@Column(name = "FINGERPRINT_SPECIAL")
	private Long fingerprintSpecial;
	/**
	 * 证件照URL
	 */
	@Column(name = "IDCARD_PHOTO")
	private String idcardPhoto;
	/**
	 * 特殊人员（指静脉）
	 */
	@Column(name = "FINGER_SPECIAL")
	private Long fingerSpecial;

	/**
	 * 指静脉有模板
	 */
	@Column(name = "FINGER_EXIST")
	private Long fingerExist;
	/**
	 * 个人头像
	 */
	@Column(name = "HEAD_PHOTO")
	private String headPhoto;
	/**
	 * 人脸有模板
	 */
	@Column(name = "FACE_EXIST")
	private Long faceExist;
	/**
	 * 特殊人员（人脸）
	 */
	@Column(name = "FACE_SPECIAL")
	private Long faceSpecial;
	/**
	 * 指纹有模板
	 */
	@Column(name = "FINGERPRINT_EXIST")
	private Long fingerprintExist;

	@Column(name = "PHONE")
	private String phone;

	private Integer sysCode;

	private String zoneCode;

	private String busiType;

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

	/**
	 * 设置：添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：人员类别（1医保人员、2非医保人员）
	 */
	public void setPersonType(String personType) {
		this.personType = personType;
	}

	/**
	 * 获取：人员类别（1医保人员、2非医保人员）
	 */
	public String getPersonType() {
		return personType;
	}

	/**
	 * 设置：特殊人员（指纹）
	 */
	public void setFingerprintSpecial(Long fingerprintSpecial) {
		this.fingerprintSpecial = fingerprintSpecial;
	}

	/**
	 * 获取：特殊人员（指纹）
	 */
	public Long getFingerprintSpecial() {
		return fingerprintSpecial;
	}

	/**
	 * 设置：证件照URL
	 */
	public void setIdcardPhoto(String idcardPhoto) {
		this.idcardPhoto = idcardPhoto;
	}

	/**
	 * 获取：证件照URL
	 */
	public String getIdcardPhoto() {
		return idcardPhoto;
	}

	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取：出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 设置：身份证号码
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	/**
	 * 获取：身份证号码
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * 设置：特殊人员（指静脉）
	 */
	public void setFingerSpecial(Long fingerSpecial) {
		this.fingerSpecial = fingerSpecial;
	}

	/**
	 * 获取：特殊人员（指静脉）
	 */
	public Long getFingerSpecial() {
		return fingerSpecial;
	}

	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置：指静脉有模板
	 */
	public void setFingerExist(Long fingerExist) {
		this.fingerExist = fingerExist;
	}

	/**
	 * 获取：指静脉有模板
	 */
	public Long getFingerExist() {
		return fingerExist;
	}

	/**
	 * 设置：个人头像
	 */
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

	/**
	 * 获取：个人头像
	 */
	public String getHeadPhoto() {
		return headPhoto;
	}

	/**
	 * 设置：人脸有模板
	 */
	public void setFaceExist(Long faceExist) {
		this.faceExist = faceExist;
	}

	/**
	 * 获取：人脸有模板
	 */
	public Long getFaceExist() {
		return faceExist;
	}

	/**
	 * 设置：特殊人员（人脸）
	 */
	public void setFaceSpecial(Long faceSpecial) {
		this.faceSpecial = faceSpecial;
	}

	/**
	 * 获取：特殊人员（人脸）
	 */
	public Long getFaceSpecial() {
		return faceSpecial;
	}

	/**
	 * 设置：指纹有模板
	 */
	public void setFingerprintExist(Long fingerprintExist) {
		this.fingerprintExist = fingerprintExist;
	}

	/**
	 * 获取：指纹有模板
	 */
	public Long getFingerprintExist() {
		return fingerprintExist;
	}

	public String getSicard() {
		return sicard;
	}

	public void setSicard(String sicard) {
		this.sicard = sicard;
	}

	public String getSicardPhoto() {
		return sicardPhoto;
	}

	public void setSicardPhoto(String sicardPhoto) {
		this.sicardPhoto = sicardPhoto;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSysCode() {
		return sysCode;
	}

	public void setSysCode(Integer sysCode) {
		this.sysCode = sysCode;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
}