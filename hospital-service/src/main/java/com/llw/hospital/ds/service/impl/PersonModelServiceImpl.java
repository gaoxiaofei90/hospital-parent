package com.llw.hospital.ds.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.PersonModelService;
import com.llw.hospital.ds.entity.PersonModel;
import com.llw.hospital.dto.PersonModelDto;


@Component
@Service(timeout = 3000)
public class PersonModelServiceImpl extends BaseDtoExtendServiceImpl<PersonModelDto, PersonModel> implements PersonModelService {

	@Override
	public Long insertModel(PersonModelDto model) {
		this.insert(model);
		PersonModelDto  where = new PersonModelDto();
		where.setPersonId(model.getPersonId());
		where.setModelAddress(model.getModelAddress());
//		where.setCreateTime(model.getCreateTime());
		PersonModelDto p = this.selectOne(where);
		return p.getModelId();
	}

}
