package com.llw.hospital.api;

import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.PersonModelDto;

/**
 * 人员模板信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-03 10:18:37
 */
public interface PersonModelService extends BaseDtoExtendService<PersonModelDto> {
 
	public Long insertModel(PersonModelDto model);
	
}

