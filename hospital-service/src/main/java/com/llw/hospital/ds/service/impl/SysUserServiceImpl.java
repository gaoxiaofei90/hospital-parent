package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.constant.ErrorCodeConstants;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.SysUserRoleService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.ds.entity.SysUser;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.common.exception.ServiceException;
import com.llw.hospital.common.util.MD5Util;
import com.llw.hospital.dto.SysUserDto;
import com.llw.hospital.dto.SysUserRoleDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
@Service(timeout = 3000)
public class SysUserServiceImpl extends BaseDtoExtendServiceImpl<SysUserDto, SysUser> implements SysUserService {

    @Autowired
    private SysOrganizationService sysOrganizationService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public String newPassword() {
        return BaseConstants.DEFAULT_PASSWORD;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserDto createUser(SysUserDto upmsUser,List<Long> roleIds) {
        //若已存在则报 用户已存在
        assertExists(upmsUser);
        //密码加密
        UUID uuid=UUID.randomUUID();
        String password=upmsUser.getPassword();
        password=MD5Util.MD5(password+uuid.toString());
        upmsUser.setPassword(password);
        upmsUser.setSalt(uuid.toString());


        //每个机构的第一个用户为该机构的管理员
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setOrgId(upmsUser.getOrgId());
        if(selectCount(sysUserDto) < 1){
            upmsUser.setIsAdmin(1);
        }
        upmsUser=insertSelectiveAndGet(upmsUser);

        //分配角色
        if(!CollectionUtils.isEmpty(roleIds)){
            List<SysUserRoleDto> roles = new ArrayList<>(roleIds.size());
            for(Long roleId:roleIds){
                SysUserRoleDto sysUserRoleDto = new SysUserRoleDto();
                sysUserRoleDto.setRoleId(roleId);
                sysUserRoleDto.setUserId(upmsUser.getUserId());
                roles.add(sysUserRoleDto);
            }
            sysUserRoleService.insertListSelective(roles);
        }

        return upmsUser;
    }

    private void assertExists(SysUserDto upmsUser) {
        SysUserDto sysUserDto=new SysUserDto();
        sysUserDto.setUsername(upmsUser.getUsername());

        Example example = new Example(SysUser.class);
        Example.Criteria c = example.createCriteria().andEqualTo("username",upmsUser.getUsername());
        if(null != upmsUser.getUserId()){
            //upmsUser.getUserId != null 说明是更新
            c.andNotEqualTo("userId",upmsUser.getUserId());
        }
        List list =mapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            throw new ServiceException(ErrorCodeConstants.FAILD,"用户已存在");
        }
    }

    @Override
    @Transactional
    public void updateUser(SysUserDto sysUserDto,List<Long> roleIds) {
        //若已存在则报 用户已存在
        assertExists(sysUserDto);
        //密码加密
        if(!StringUtils.isEmpty(sysUserDto.getPassword())){
            UUID uuid=UUID.randomUUID();
            String password=sysUserDto.getPassword();
            password=MD5Util.MD5(password+uuid.toString());
            sysUserDto.setPassword(password);
            sysUserDto.setSalt(uuid.toString());
        }
        updateByPrimaryKeySelective(sysUserDto);

        //重新分配角色
        if(!CollectionUtils.isEmpty(roleIds)){
            SysUserRoleDto userRoleDto = new SysUserRoleDto();
            userRoleDto.setUserId(sysUserDto.getUserId());
            sysUserRoleService.delete(userRoleDto);
            List<SysUserRoleDto> roles = new ArrayList<>(roleIds.size());
            for(Long roleId:roleIds){
                SysUserRoleDto sysUserRoleDto = new SysUserRoleDto();
                sysUserRoleDto.setRoleId(roleId);
                sysUserRoleDto.setUserId(sysUserDto.getUserId());
                roles.add(sysUserRoleDto);
            }
            sysUserRoleService.insertListSelective(roles);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long pk) {
        SysUserDto exists = selectByPrimaryKey(pk);
        if(null != exists && exists.getIsAdmin() != null && exists.getIsAdmin() == 1){
            throw new ServiceException(ErrorCodeConstants.FAILD,"管理员无法删除");
        }
        deleteByPrimaryKey(pk);
        SysUserRoleDto userRoleDto = new SysUserRoleDto();
        userRoleDto.setUserId(pk);
        sysUserRoleService.delete(userRoleDto);
    }
}
