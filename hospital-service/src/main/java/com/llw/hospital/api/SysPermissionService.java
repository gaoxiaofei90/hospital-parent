package com.llw.hospital.api;

import com.alibaba.fastjson.JSONArray;
import com.jcl.orm.tkmapper.BaseService;
import com.llw.hospital.dto.SysPermissionDto;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public interface SysPermissionService extends BaseService<SysPermissionDto> {
    /**
     * 用户权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, Long id);

    JSONArray getTreeByRoleId(String currentUser,Integer roleId);

    JSONArray getTreeByUserId(String currentUser,Integer usereId, Byte type);

}

