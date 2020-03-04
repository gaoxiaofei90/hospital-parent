package com.llw.hospital.ds.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.MsgService;
import com.llw.hospital.ds.entity.Msg;
import com.llw.hospital.dto.MsgDto;

@Component
@Service(timeout = 3000)
public class MsgServiceImpl extends BaseDtoExtendServiceImpl<MsgDto, Msg> implements MsgService {

}
