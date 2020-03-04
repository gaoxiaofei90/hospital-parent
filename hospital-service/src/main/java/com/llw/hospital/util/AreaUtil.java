package com.llw.hospital.util;

import com.llw.cache.CacheClient;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.common.util.MultiKeyMap;
import com.llw.hospital.dto.SysAreaDto;

public class AreaUtil {

    public static String getAreaName(String areaCode){
        MultiKeyMap<String, SysAreaDto> areaMap = (MultiKeyMap<String, SysAreaDto>) CacheClient.getCachedObject(CacheConstants.UPMS_AREA_CACHE);
        SysAreaDto dto = areaMap.get(areaCode);
       if(dto==null){
           return "";
       }
       return dto.getName();
    }


    public static String getDetailAddress(String provinceCode, String cityCode,
                String regionCode, String streetCode, String address){
        MultiKeyMap<String, SysAreaDto> areaMap = (MultiKeyMap<String, SysAreaDto>) CacheClient.getCachedObject(CacheConstants.UPMS_AREA_CACHE);
        String[] addrCodeArr = new String[]{provinceCode, cityCode, regionCode, streetCode};
        StringBuffer addrBuffer = new StringBuffer();
        for (String addrCode : addrCodeArr){
            SysAreaDto dto = areaMap.get(addrCode);
            if(dto!=null){
                addrBuffer.append(dto.getName());
            }
        }
        if (address != null){
            addrBuffer.append(address);
        }
        return addrBuffer.toString();
    }

}
