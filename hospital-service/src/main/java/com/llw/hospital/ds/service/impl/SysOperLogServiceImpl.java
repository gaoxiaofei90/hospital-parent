package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseServiceImpl;
import com.llw.hospital.ds.entity.SysOperLog;
import com.llw.hospital.api.SysOperLogService;
import com.llw.hospital.dto.SysOperLogDto;
import org.springframework.stereotype.Component;

@Component
@Service(timeout = 3000)
public class SysOperLogServiceImpl extends BaseServiceImpl<SysOperLogDto, SysOperLog> implements SysOperLogService {

}
