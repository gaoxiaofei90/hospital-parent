package com.llw.hospital.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class RecogExtendDto extends RecogDto {
	
	private Long hospitalId;
	private String hospitalName;
	private Long departId;
	private String departName;
	private String location;
	private String keyword;//关键字查询
	private String startTime;//测试体温起始时间
	private String endTime;// 测试体温截至时间
	private String name;//姓名
	private String idcard;//身份证号码
	private String medicalNo;//住院号
	private String bedNo;//床号
	private Long userOrgId;//用户所在机构Id，作用用于权限过滤
	private Integer personFrom;//参保统筹区（1本省、2本市、3省本级、4外省）
	private String temperatureStr;//体温数据
	private Date inDate;//入院时间
	private String monthKey;//月份查询关键字
//	private Integer recogMode;//认证方式（11人脸/人脸核验、12指静脉、15指纹、21拍照认证/特殊建模、22离床登记、23稽查登记/特殊登记）
	private Integer personTotal; //总认证人次
	private Integer recogSusTotal;//认证成功人次
	private Integer recogFaiTotal;//认证失败人次
	private Integer noRecogTotal;//未认证人次
	private Float susRate;//完成率
	private Integer recogStatus;//认证状态（0、未认证；1、认证成功；2、认证失败）
	private Integer recogEvent; //认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
	private String proveData;//证明材料URL
	private String idcardPhoto;//身份证照、模板照
	private String recogStr; //认证字符串
	
	
	
	
	public Long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getMedicalNo() {
		return medicalNo;
	}
	public void setMedicalNo(String medicalNo) {
		this.medicalNo = medicalNo;
	}
	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}
	public String getTemperatureStr() {
		return temperatureStr;
	}
	public void setTemperatureStr(String temperatureStr) {
		
		if(!StringUtils.isBlank(temperatureStr)){
			
			String [] arrays = temperatureStr.split(",");
			
			if(arrays != null){
				for(String str: arrays){
					if(!StringUtils.isBlank(str)){
						if(str.contains("temperature")){
							int len = str.indexOf("temperature");
							str = str.substring(len +11);
							str = str.replace(":", "").replace("}", "").replace("{", "").replace(" ", "").replace("null", "").replace("\"", "");
							temperatureStr = str;
							break;
						}else{
							temperatureStr = "";
						}
					}
				}
			}else{
				temperatureStr = "";
			}
				
		}
		this.temperatureStr = temperatureStr;
	}
	public Long getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	public Integer getPersonFrom() {
		return personFrom;
	}
	public void setPersonFrom(Integer personFrom) {
		this.personFrom = personFrom;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public String getMonthKey() {
		return monthKey;
	}
	public void setMonthKey(String monthKey) {
		this.monthKey = monthKey;
	}
//	public Integer getRecogMode() {
//		return recogMode;
//	}
//	public void setRecogMode(Integer recogMode) {
//		this.recogMode = recogMode;
//	}
	public Integer getPersonTotal() {
		return personTotal;
	}
	public void setPersonTotal(Integer personTotal) {
		this.personTotal = personTotal;
	}
	public Integer getRecogSusTotal() {
		return recogSusTotal;
	}
	public void setRecogSusTotal(Integer recogSusTotal) {
		this.recogSusTotal = recogSusTotal;
	}
	public Integer getRecogFaiTotal() {
		return recogFaiTotal;
	}
	public void setRecogFaiTotal(Integer recogFaiTotal) {
		this.recogFaiTotal = recogFaiTotal;
	}
	public Integer getNoRecogTotal() {
		return noRecogTotal;
	}
	public void setNoRecogTotal(Integer noRecogTotal) {
		this.noRecogTotal = noRecogTotal;
	}
	public Float getSusRate() {
		return susRate;
	}
	public void setSusRate(Float susRate) {
		this.susRate = susRate;
	}
	public Integer getRecogStatus() {
		return recogStatus;
	}
	public void setRecogStatus(Integer recogStatus) {
		this.recogStatus = recogStatus;
	}
	public Integer getRecogEvent() {
		return recogEvent;
	}
	public void setRecogEvent(Integer recogEvent) {
		this.recogEvent = recogEvent;
	}
	public String getIdcardPhoto() {
		return idcardPhoto;
	}
	public void setIdcardPhoto(String idcardPhoto) {
		this.idcardPhoto = idcardPhoto;
	}
	public String getProveData() {
		return proveData;
	}
	public void setProveData(String proveData) {
		this.proveData = proveData;
	}
	public String getRecogStr() {
		return recogStr;
	}
	public void setRecogStr(String recogStr) {
		this.recogStr = recogStr;
	}
	
	
}
