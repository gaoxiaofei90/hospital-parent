package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.stereotype.Component;

import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.SysDeviceTypeService;
import com.llw.hospital.ds.entity.SysDeviceType;
import com.llw.hospital.dto.SysDeviceTypeDto;


@Component
@Service(timeout = 3000)
public class SysDeviceTypeServiceImpl extends BaseDtoExtendServiceImpl<SysDeviceTypeDto, SysDeviceType> implements SysDeviceTypeService {

}
