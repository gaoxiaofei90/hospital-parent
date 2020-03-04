package com.llw.hospital.api.util;

import com.jcl.orm.tkmapper.LLwExample;
import com.llw.hospital.api.vo.SearchKeyParam;
import com.llw.hospital.common.util.StringUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;

public class RequestUtil {
    public static void checkPageParam(SearchKeyParam request) {
        Integer pageNo=request.getPageNo();
        Integer pageSize=request.getPageSize();
        if(pageNo==null){
            request.setPageNo(1);
        }
        if(pageSize==null){
            request.setPageSize(10);
        }
    }

    public static void packageSearchKey(@ApiParam @RequestBody SearchKeyParam request, LLwExample lLwExample) {
        if(StringUtils.isNotBlank(request.getKey())){
            LLwExample lLwExample2=lLwExample.and();
            lLwExample2.orLike("admissionNo",request.getKey()).orLike("elderName","%"+request.getKey().replaceAll("%","\\%").replaceAll("_","\\_")+"%");
        }
    }
}
