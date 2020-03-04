package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.jcl.orm.tkmapper.BaseServiceImpl;
import com.llw.hospital.ds.entity.SysPermission;
import com.llw.hospital.api.SysPermissionService;
import com.llw.hospital.dto.SysPermissionDto;
import org.springframework.stereotype.Component;


@Component
@Service(timeout = 3000)
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionDto, SysPermission> implements SysPermissionService {

    @Override
    public int permission(JSONArray datas, Long id) {
        return 0;
    }

    @Override
    public JSONArray getTreeByRoleId(String currentUser, Integer roleId) {
        return null;
    }

    @Override
    public JSONArray getTreeByUserId(String currentUser, Integer usereId, Byte type) {
        return null;
    }
}
