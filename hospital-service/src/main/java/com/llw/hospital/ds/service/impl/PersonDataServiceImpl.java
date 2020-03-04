package com.llw.hospital.ds.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.PersonDataService;
import com.llw.hospital.ds.entity.PersonData;
import com.llw.hospital.dto.PersonDataDto;


@Component
@Service(timeout = 3000)
public class PersonDataServiceImpl extends BaseDtoExtendServiceImpl<PersonDataDto, PersonData> implements PersonDataService {

}
