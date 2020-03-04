package com.llw.hospital.api.vo.login;

import java.util.List;

import com.llw.hospital.ds.entity.Business;

import io.swagger.annotations.ApiModelProperty;

public class UserMessage {
	@ApiModelProperty(value = "菜单信息")
	private List<SysMenu> sysMenuList;
	

//	@ApiModelProperty(value = "业务类型" )
//	private List<Zone> zones;

	 
	@ApiModelProperty(value = "业务类型" )
    private List<Business> busiTypes;

	
	

	public List<SysMenu> getSysMenuList() {
		return sysMenuList;
	}

	public void setSysMenuList(List<SysMenu> sysMenuList) {
		this.sysMenuList = sysMenuList;
	}

	

	public List<Business> getBusiTypes() {
		return busiTypes;
	}

	public void setBusiTypes(List<Business> busiTypes) {
		this.busiTypes = busiTypes;
	}

//	public List<Zone> getZones() {
//		return zones;
//	}
//
//	public void setZones(List<Zone> zones) {
//		this.zones = zones;
//	}

	

	
}
