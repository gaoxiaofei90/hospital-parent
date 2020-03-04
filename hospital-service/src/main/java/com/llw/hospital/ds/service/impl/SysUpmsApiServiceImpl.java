package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.llw.hospital.api.SysUpmsApiService;
import com.llw.hospital.dto.SysPermissionDto;
import com.llw.hospital.ds.mapper.SysUserDao;
import com.llw.hospital.ds.mapper.SysUpmsApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UpmsApiService实现
 * Created by shuaeye.mbr on 2016/01/19.
 */
@Component
@Service(timeout = 3000)
public class SysUpmsApiServiceImpl implements SysUpmsApiService {

    private static Logger _log = LoggerFactory.getLogger(SysUpmsApiServiceImpl.class);

    @Autowired
    SysUserDao upmsUserMapper;

    @Autowired
    SysUpmsApiMapper upmsApiMapper;

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysPermissionDto> selectPermissionByUserId(Long userId,int modules) {
        Map<String,Object> params = new HashMap<>(1);
        params.put("user_id",userId);
        params.put("modules",modules);
        List<SysPermissionDto> upmsPermissions = upmsApiMapper.selectPermissionByUserIdInModules(params);
        return upmsPermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysPermissionDto> selectPermissionByUserId(Long userId) {
        Map<String,Object> params = new HashMap<>(1);
        params.put("user_id",userId);
        List<SysPermissionDto> upmsPermissions = upmsApiMapper.selectPermissionByUserId(params);
        return upmsPermissions;
    }

}