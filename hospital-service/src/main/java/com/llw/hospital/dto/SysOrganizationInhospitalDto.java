package com.llw.hospital.dto;

import java.util.List;

/**
 * @serial 医院端组织机构实体类
 * @author gjp 
 * @date   20191031
 *
 */
public class SysOrganizationInhospitalDto extends SysOrganizationDto{


	private static final long serialVersionUID = 1L;

	private String pidName ; //组织机构父组织名称

	private List<SysOrganizationDto> list;//子组织机构/科室 集合
	
	
	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

	public List<SysOrganizationDto> getList() {
		return list;
	}

	public void setList(List<SysOrganizationDto> list) {
		this.list = list;
	}
	
	
	
	
}
