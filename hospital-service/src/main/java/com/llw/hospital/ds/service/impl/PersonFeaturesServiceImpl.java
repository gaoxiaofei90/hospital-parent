package com.llw.hospital.ds.service.impl;

import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.PersonFeaturesService;
import com.llw.hospital.ds.entity.PersonFeatures;
import com.llw.hospital.dto.PersonFeaturesDto;


@Component
@Service(timeout = 3000)
public class PersonFeaturesServiceImpl extends BaseDtoExtendServiceImpl<PersonFeaturesDto, PersonFeatures> implements PersonFeaturesService {

}
