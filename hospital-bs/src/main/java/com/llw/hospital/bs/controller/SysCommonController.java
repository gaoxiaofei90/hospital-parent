package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jcl.dto.BaseResp;
import com.llw.cache.CacheClient;
import com.llw.hospital.api.SysUpmsApiService;
import com.llw.hospital.bs.util.LoginUtil;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.common.util.MultiKeyMap;
import com.llw.hospital.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by shengpeng on 2018/3/26.
 * 各组件通用的Controller
 * 主要为前端公共组件提供数据
 * 数据来自于通用缓存组件
 */
@Controller
@RequestMapping(value="/common")
public class SysCommonController {
    static final Logger log = LoggerFactory.getLogger(SysCommonController.class);

    @Autowired
    SysUpmsApiService upmsApiService;
    
    @ResponseBody
    @RequestMapping(value = "/childrenArea.jspx")
    public BaseResp childrenArea(@RequestParam(value="areaCode",required=false,defaultValue="000000000000") String areaCode) {
        MultiKeyMap<String, SysAreaDto> areaMap = (MultiKeyMap<String, SysAreaDto>) CacheClient.getCachedObject(CacheConstants.UPMS_AREA_CACHE);
        Collection<SysAreaDto> children = areaMap.getByFKey(areaCode);
        List<SysAreaDto> result = new ArrayList<>();
        if(null != children){
            for (SysAreaDto child : children) {
                result.add(child);
            }
        }
        return BaseResp.success(result);
    }


    @RequestMapping(value = "/dictSelect.jspx", method = RequestMethod.GET)
    @ResponseBody
    public Object dictSelect(String appFieldName) {
        Map<String,List<SysDictDto>> dicts = (Map<String, List<SysDictDto>>) CacheClient.getCachedObject(CacheConstants.UPMS_DICT_CACHE);
        List<SysDictDto> list = dicts.get(appFieldName.toUpperCase());
        if(!CollectionUtils.isEmpty(list)){
            return list;
        }else{
            return new ArrayList<>(0);
        }
    }

    @RequestMapping(value = "/allDict.jspx")
    @ResponseBody
    public Object getDict() {
        Map<String,List<SysDictDto>> dicts = (Map<String, List<SysDictDto>>) CacheClient.getCachedObject(CacheConstants.UPMS_DICT_CACHE);
        return BaseResp.success(dicts);
    }

    @RequestMapping(value = "/allPermissions.jspx")
    @ResponseBody
    public Object getPermissions() {
        // 当前登录用户权限
        SysUserDto sysUser = LoginUtil.getCurrentUser();
        List<SysPermissionDto> upmsPermissions = upmsApiService.selectPermissionByUserId(sysUser.getUserId(),BaseConstants.MODULES_BS);
        return BaseResp.success(upmsPermissions);
    }

}
