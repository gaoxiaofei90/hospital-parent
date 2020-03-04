package com.llw.hospital.api.vo.person;

import java.util.Date;

import com.llw.hospital.api.vo.BasePageRequest;

import io.swagger.annotations.ApiModelProperty;

public class PersonRequest extends BasePageRequest{
	@ApiModelProperty(value = "科室id,数据类型为Long" )
	private Long departId;

	@ApiModelProperty(value = "人员状态（1在院、2出院）" )
	private Integer personStatus;
	@ApiModelProperty(value = "抽查开始时间，时间戳形式 " )
	private Date beginTime ;
	@ApiModelProperty(value = "抽查结束时间，时间戳形式" )
	private Date endTime;
	@ApiModelProperty(value = "关键字,姓名/床号/就诊号" )
    private String keyWord;

	@ApiModelProperty(value = "医院id,数据类型为Long" )
	private Long HospitalId;
	
	@ApiModelProperty(value = "人脸模板，1:有模板  其他是无模板,数据类型为Long")
	private Long faceExist; 
	
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	public Integer getPersonStatus() {
		return personStatus;
	}
	public void setPersonStatus(Integer personStatus) {
		this.personStatus = personStatus;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Long getHospitalId() {
		return HospitalId;
	}
	public void setHospitalId(Long hospitalId) {
		HospitalId = hospitalId;
	}
	public Long getFaceExist() {
		return faceExist;
	}
	public void setFaceExist(Long faceExist) {
		this.faceExist = faceExist;
	}
	
	
}
