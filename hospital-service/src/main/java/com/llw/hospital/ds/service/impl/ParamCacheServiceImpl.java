package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.llw.cache.CacheService;
import com.llw.cache.TableAssociatedCacheService;
import com.llw.hospital.api.SysParamService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.dto.SysParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/14.
 */
@Component
@Service(group = "param_"+ BaseConstants.MODULE_NAME,interfaceClass = CacheService.class,timeout = 3000)
public class ParamCacheServiceImpl implements TableAssociatedCacheService {

    @Autowired
    private SysParamService sysParamService;

    @Override
    public String getCacheName() {
        return CacheConstants.UPMS_PARAM_CACHE;
    }

    @Override
    public boolean associatedWith(String tableName){
        return "sys_param".equals(tableName);
    }

    @Override
    public Object getCacheObject() {
        List<SysParamDto> list = sysParamService.selectList(new SysParamDto());
        Map<String,String> map = new HashMap<String,String>(list.size());
        for(SysParamDto p:list){
            map.put(p.getParamName(),p.getParamValue());
        }
        return map;
    }

    @Override
    public Object refresh(Object o, Map<String, String> map) {
        return getCacheObject();
    }
}
