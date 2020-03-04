package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseServiceImpl;
import com.llw.hospital.api.SysAreaService;
import com.llw.hospital.dto.SysAreaDto;
import com.llw.hospital.ds.entity.SysArea;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Service(timeout = 3000)
public class SysAreaServiceImpl extends BaseServiceImpl<SysAreaDto, SysArea> implements SysAreaService {

    @Override
    public List<SysAreaDto> getAreaListByParentId(String pid) {
        return null;
    }
}
