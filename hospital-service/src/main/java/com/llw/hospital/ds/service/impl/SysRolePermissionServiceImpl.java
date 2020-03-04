package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseServiceImpl;
import com.llw.hospital.ds.entity.SysRolePermission;
import com.llw.hospital.api.SysRolePermissionService;
import com.llw.hospital.dto.SysRolePermissionDto;
import org.springframework.stereotype.Component;


@Component
@Service(timeout = 3000)
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionDto, SysRolePermission> implements SysRolePermissionService {

}
