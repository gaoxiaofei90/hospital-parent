package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.llw.cache.CacheService;
import com.llw.cache.TableAssociatedCacheService;
import com.llw.hospital.api.SysAreaService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.common.util.MultiKeyMap;
import com.llw.hospital.common.util.StringUtils;
import com.llw.hospital.dto.SysAreaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Service(group = "area_"+ BaseConstants.MODULE_NAME,interfaceClass = CacheService.class,timeout = 30000)
public class AreaCacheServiceImpl implements TableAssociatedCacheService {

    @Autowired
    SysAreaService sysAreaService;

    @Override
    public String getCacheName() {
        return CacheConstants.UPMS_AREA_CACHE;
    }

    @Override
    public Object getCacheObject() {
        MultiKeyMap<String, SysAreaDto> multiKeyMap = new MultiKeyMap();
        //全部查出来 TODO 需考虑dubbo序列化的最大值 --shengpeng

        List<SysAreaDto> list = sysAreaService.selectList(new SysAreaDto());
        for(SysAreaDto area:list){
            String pid = area.getPCode();
            if(StringUtils.isEmpty(pid)){
                pid = "0";
            }
            multiKeyMap.put(area.getAreaCode(),pid,area);
        }
        return multiKeyMap;
    }

    @Override
    public boolean associatedWith(String s) {
        return "sys_area".equals(s);
    }

    @Override
    public Object refresh(Object o, Map<String, String> map) {
        //TODO 改成按机构刷新
        return getCacheObject();
    }
}
