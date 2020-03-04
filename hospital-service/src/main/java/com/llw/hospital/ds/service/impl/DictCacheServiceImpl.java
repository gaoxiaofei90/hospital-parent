package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.cache.CacheService;
import com.llw.cache.TableAssociatedCacheService;
import com.llw.hospital.api.SysDictService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.dto.SysDictDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service(group = "dict_"+ BaseConstants.MODULE_NAME,interfaceClass = CacheService.class,timeout = 3000)
public class DictCacheServiceImpl implements TableAssociatedCacheService {
    @Autowired
    private SysDictService dictService;


    @Override
    public String getCacheName() {
        return CacheConstants.UPMS_DICT_CACHE;
    }

    @Override
    public Object getCacheObject() {
        HashMap<String,List<SysDictDto>> maps = new HashMap<String,List<SysDictDto>>();
        //需要根据orders字段排序
        LLwExample lLwExample = new LLwExample();
        lLwExample.orderBy("orders").asc();
        List<SysDictDto> list = dictService.selectByExample(lLwExample);
        for(SysDictDto dict:list){
            String fieldName = dict.getAppFieldName();
            if (fieldName == null || fieldName.trim().length() == 0) {
                continue;
            }
            if (maps.containsKey(fieldName)) {
                List<SysDictDto> items = maps.get(fieldName);
                String fieldValue = dict.getFieldValue();
                if (fieldValue != null && fieldValue.trim().length() > 0) {
                    items.add(dict);
                }
            } else {
                List<SysDictDto> items = new ArrayList<SysDictDto>();
                String fieldValue = dict.getFieldValue();
                if (fieldValue != null && fieldValue.trim().length() > 0) {

                    items.add(dict);
                }
                maps.put(fieldName, items);
            }
        }
        return maps;
    }

    @Override
    public boolean associatedWith(String s) {
        return "sys_dict".equals(s);
    }

    @Override
    public Object refresh(Object o, Map<String, String> map) {
        //TODO 改成按机构刷新
        return getCacheObject();
    }
}
