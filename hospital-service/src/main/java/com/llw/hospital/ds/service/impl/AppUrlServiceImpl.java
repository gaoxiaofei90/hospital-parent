package com.llw.hospital.ds.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.AppUrlService;
import com.llw.hospital.ds.entity.AppUrl;
import com.llw.hospital.dto.AppUrlDto;

@Component
@Service(timeout = 3000)
public class AppUrlServiceImpl extends BaseDtoExtendServiceImpl<AppUrlDto, AppUrl> implements AppUrlService {

}
