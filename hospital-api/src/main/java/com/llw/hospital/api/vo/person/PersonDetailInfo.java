package com.llw.hospital.api.vo.person;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="人员详细信息",description="人员详细信息")
public class PersonDetailInfo extends PersonInfo {
	@ApiModelProperty(value = "已住院天数")
	private Integer inHospitalDays;
	
	@ApiModelProperty(value = "应认证次数")
	private Integer needRecogCount;
	
	@ApiModelProperty(value = "已认证次数")
	private Integer hasRecogCount;
	
	@ApiModelProperty(value = "抽查记录")
	private List<PlanInfo> checkRecordList ;

	public List<PlanInfo> getCheckRecordList() {
		return checkRecordList;
	}

	public void setCheckRecordList(List<PlanInfo> checkRecordList) {
		this.checkRecordList = checkRecordList;
	}

	public Integer getInHospitalDays() {
		return inHospitalDays;
	}

	public void setInHospitalDays(Integer inHospitalDays) {
		this.inHospitalDays = inHospitalDays;
	}

	public Integer getNeedRecogCount() {
		return needRecogCount;
	}

	public void setNeedRecogCount(Integer needRecogCount) {
		this.needRecogCount = needRecogCount;
	}

	public Integer getHasRecogCount() {
		return hasRecogCount;
	}

	public void setHasRecogCount(Integer hasRecogCount) {
		this.hasRecogCount = hasRecogCount;
	}
	
	
}
