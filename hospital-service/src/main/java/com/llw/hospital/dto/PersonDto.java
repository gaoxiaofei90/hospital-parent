package com.llw.hospital.dto;
import java.util.Date;
import java.util.List;
import com.jcl.dto.BaseDto;
import com.llw.hospital.util.UrlMapping;

/**
 * 人员表
 * 
 */
public class PersonDto extends BaseDto {

	/**
	 * 人员ID
	 */
	private Long personId;
	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 身份证号码
	 */
	private String idcard;
	/**
	 * 出生日期
	 */
	private Date birthday;
	/**
	 * 性别
	 */
	private String sex;

	private String sicard;

	@UrlMapping
	private String sicardPhoto;

	/**
	 * 人员类别（1医保人员、2非医保人员）
	 */
	private String personType;
	/**
	 * 添加时间
	 */
	private Date createTime;

	/**
	 * 证件照URL
	 */
	@UrlMapping
	private String idcardPhoto;
	/**
	 * 个人头像
	 */
	@UrlMapping
	private String headPhoto;

	/**
	 * 特殊人员（人脸）
	 */
	private Long faceSpecial;

	/**
	 * 特殊人员（指纹）
	 */
	private Long fingerprintSpecial;

	/**
	 * 特殊人员（指静脉）
	 */
	private Long fingerSpecial;

	/**
	 * 指静脉有模板
	 */
	private Long fingerExist;

	/**
	 * 指纹有模板
	 */
	private Long fingerprintExist;

	/**
	 * 人脸有模板
	 */
	private Long faceExist;

	
	
	/**
	 * 人员类型
	 */
	private String personTypename;
	
	/**
	 * 预警次数
	 */
	private String warningCount;
	
	/**
	 * 住院ID
	 */
	private String medicalId;
	
	
	/**
	 * 关键字
	 */
	private String keyword;

	private String phone;

	private Integer sysCode;

	private String zoneCode;

	private String busiType;

	private List<byte[]> features;

	//人员足迹 在哪个机构有业务数据 用于1比n时缩小范围
	private List<Long> footPrints;

	//人员所属的分类 如果是门特人员 groups=[3] 如果同时还是黑名单 groups=[3,99]
	private Short[] groups;
	
	private String orderType;
	private String orderField;
	
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIdcardPhoto() {
		return idcardPhoto;
	}

	public void setIdcardPhoto(String idcardPhoto) {
		this.idcardPhoto = idcardPhoto;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

	public Long getFaceSpecial() {
		return faceSpecial;
	}

	public void setFaceSpecial(Long faceSpecial) {
		this.faceSpecial = faceSpecial;
	}

	public Long getFingerprintSpecial() {
		return fingerprintSpecial;
	}

	public void setFingerprintSpecial(Long fingerprintSpecial) {
		this.fingerprintSpecial = fingerprintSpecial;
	}

	public Long getFingerSpecial() {
		return fingerSpecial;
	}

	public void setFingerSpecial(Long fingerSpecial) {
		this.fingerSpecial = fingerSpecial;
	}

	public Long getFingerExist() {
		return fingerExist;
	}

	public void setFingerExist(Long fingerExist) {
		this.fingerExist = fingerExist;
	}

	public Long getFingerprintExist() {
		return fingerprintExist;
	}

	public void setFingerprintExist(Long fingerprintExist) {
		this.fingerprintExist = fingerprintExist;
	}

	public Long getFaceExist() {
		return faceExist;
	}

	public void setFaceExist(Long faceExist) {
		this.faceExist = faceExist;
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

	public String getPersonTypename() {
		return personTypename;
	}

	public void setPersonTypename(String personTypename) {
		this.personTypename = personTypename;
	}

	public String getWarningCount() {
		return warningCount;
	}

	public void setWarningCount(String warningCount) {
		this.warningCount = warningCount;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
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

	public List<byte[]> getFeatures() {
		return features;
	}

	public void setFeatures(List<byte[]> features) {
		this.features = features;
	}

	public List<Long> getFootPrints() {
		return footPrints;
	}

	public void setFootPrints(List<Long> footPrints) {
		this.footPrints = footPrints;
	}

	public Short[] getGroups() {
		return groups;
	}

	public void setGroups(Short[] groups) {
		this.groups = groups;
	}
}
