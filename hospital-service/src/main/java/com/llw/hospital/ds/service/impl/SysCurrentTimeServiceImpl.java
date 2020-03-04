package com.llw.hospital.ds.service.impl;

import com.llw.hospital.api.SysCurrentTimeService;
import com.alibaba.dubbo.config.annotation.Service;
import com.llw.hospital.ds.mapper.SysCommonQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wendellpeng
 * @Title: CurrentTimeServiceImpl
 * @ProjectName gov-parent
 * @date 2018/8/29 14:55
 */
@Component(value="currentTimeService")
@Service(timeout = 2000)
public class SysCurrentTimeServiceImpl implements SysCurrentTimeService {

    @Autowired
    SysCommonQueryMapper sysCommonQueryMapper;

    @Override
    public Date getCurrentTime() {
        return sysCommonQueryMapper.currentTime();
    }
}
