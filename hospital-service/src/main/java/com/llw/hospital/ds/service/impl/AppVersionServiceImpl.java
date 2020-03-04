package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.stereotype.Component;

import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.AppVersionService;
import com.llw.hospital.ds.entity.AppVersion;
import com.llw.hospital.dto.AppVersionDto;

import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;


@Component
@Service(timeout = 3000)
public class AppVersionServiceImpl extends BaseDtoExtendServiceImpl<AppVersionDto, AppVersion> implements AppVersionService {

    @Override
    @Transactional
    public int save(AppVersionDto appVersionDto) {
        clearLastest(appVersionDto);
        return insertSelective(appVersionDto);
    }

    @Override
    @Transactional
    public int update(AppVersionDto appVersionDto) {
        clearLastest(appVersionDto);
        return updateByPrimaryKeySelective(appVersionDto);
    }

    private void clearLastest(AppVersionDto appVersionDto) {
        if(appVersionDto.getLastest() == 1){
            AppVersion version = new AppVersion();
            version.setLastest(0);
            Example example = new Example(AppVersion.class);
            example.createCriteria().andEqualTo("deviceType",appVersionDto.getDeviceType());
            this.mapper.updateByExampleSelective(version,example);
        }
    }
}
