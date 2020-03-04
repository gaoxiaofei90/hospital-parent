package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseServiceImpl;
import com.llw.hospital.api.SysParamService;
import com.llw.hospital.dto.SysParamDto;
import com.llw.hospital.ds.entity.SysParam;
import org.springframework.stereotype.Component;


@Component
@Service(timeout = 3000)
public class SysParamServiceImpl extends BaseServiceImpl<SysParamDto, SysParam> implements SysParamService {

}
