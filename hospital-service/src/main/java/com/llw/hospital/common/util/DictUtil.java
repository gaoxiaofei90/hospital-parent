package com.llw.hospital.common.util;

import com.llw.cache.CacheClient;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.dto.SysDictDto;

import java.util.HashMap;
import java.util.List;

/**
 * 字典工具类
 */
public class DictUtil {

    public static final String DICT_KEY_SEX = "sex";
    public static final String DICT_KEY_ENABLED = "enabled";
    public static final String DICT_KEY_ORG_CATEGORY = "orgCategory";
    public static final String DICT_KEY_ORG_LEVEL = "orgLevel";

    public static SysDictDto getDict(String dictKey, String value){
        if(StringUtils.isEmpty(dictKey) || StringUtils.isEmpty(value)){
            return null;
        }
        HashMap<String, List<SysDictDto>> data = (HashMap<String, List<SysDictDto>>) CacheClient.getCachedObject(CacheConstants.UPMS_DICT_CACHE);
        if(null == data || data.size() < 1){
            return null;
        }
        List<SysDictDto> list = data.get(dictKey);
        if(null == list || list.size() < 1){
            return null;
        }
        for(SysDictDto dto:list){
            if(dto.getFieldValue().equals(value)){
                return dto;
            }
        }
        return null;
    }

    public static String getDictLabel(String dictKey,String value){
        if(StringUtils.isEmpty(dictKey) || StringUtils.isEmpty(value)){
            return "";
        }
        HashMap<String, List<SysDictDto>> data = (HashMap<String, List<SysDictDto>>) CacheClient.getCachedObject(CacheConstants.UPMS_DICT_CACHE);
        if(null == data || data.size() < 1){
            return "";
        }
        List<SysDictDto> list = data.get(dictKey);
        if(null == list || list.size() < 1){
            return "";
        }
        for(SysDictDto dto:list){
            if(dto.getFieldValue().equals(value)){
                return dto.getValueComment();
            }
        }
        return "";
    }

    public static List<SysDictDto> getDict(String dictKey){
        if(StringUtils.isEmpty(dictKey)){
            return null;
        }
        HashMap<String, List<SysDictDto>> data = (HashMap<String, List<SysDictDto>>) CacheClient.getCachedObject(CacheConstants.UPMS_DICT_CACHE);
        if(null == data || data.size() < 1){
            return null;
        }
        return data.get(dictKey);
    }
}
