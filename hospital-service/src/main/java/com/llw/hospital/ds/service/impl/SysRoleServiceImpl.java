package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.constant.ErrorCodeConstants;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.SysUserRoleService;
import com.llw.hospital.ds.entity.SysRole;
import com.llw.hospital.api.SysRolePermissionService;
import com.llw.hospital.api.SysRoleService;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.common.exception.ServiceException;
import com.llw.hospital.dto.SysRolePermissionDto;
import com.llw.hospital.dto.SysRoleDto;
import com.llw.hospital.dto.SysUserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(timeout = 3000)
public class SysRoleServiceImpl extends BaseDtoExtendServiceImpl<SysRoleDto, SysRole> implements SysRoleService {

    @Autowired
    SysRolePermissionService rolePermissionService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Override
    public List<SysRolePermissionDto> getPermissions(Long roleId) {
        SysRolePermissionDto rolePermissionDto = new SysRolePermissionDto();
        rolePermissionDto.setRoleId(roleId);
        return  rolePermissionService.selectList(rolePermissionDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reAssignPermissions(Long roleId, List<Long> permissions) {
        SysRolePermissionDto rolePermissionDto = new SysRolePermissionDto();
        rolePermissionDto.setRoleId(roleId);
        rolePermissionService.delete(rolePermissionDto);

        if(!CollectionUtils.isEmpty(permissions))
        {
            List<SysRolePermissionDto> permissionDtos = new ArrayList<>(permissions.size());
            for(Long permissionId:permissions)
            {
                SysRolePermissionDto data = new SysRolePermissionDto();
                data.setRoleId(roleId);
                data.setPermissionId(permissionId);
                permissionDtos.add(data);
            }
            rolePermissionService.insertListSelective(permissionDtos);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long roleId) {

        SysUserRoleDto sysUserDto = new SysUserRoleDto();
        sysUserDto.setRoleId(roleId);
        if(sysUserRoleService.selectCount(sysUserDto) > 0)
        {
            throw new ServiceException(ErrorCodeConstants.FAILD,"角色已经被分配，无法删除");
        }

        SysRolePermissionDto rolePermissionDto = new SysRolePermissionDto();
        rolePermissionDto.setRoleId(roleId);
        rolePermissionService.delete(rolePermissionDto);
        deleteByPrimaryKey(roleId);
    }
}
