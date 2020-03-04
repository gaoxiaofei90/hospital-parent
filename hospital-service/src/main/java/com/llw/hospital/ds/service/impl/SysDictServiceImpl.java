package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseServiceImpl;
import com.llw.hospital.api.SysDictService;
import com.llw.hospital.dto.SysDictDto;
import com.llw.hospital.ds.entity.SysDict;
import org.springframework.stereotype.Component;


@Component
@Service(timeout = 3000)
public class SysDictServiceImpl extends BaseServiceImpl<SysDictDto, SysDict> implements SysDictService {
}
