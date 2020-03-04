package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.SysRoleService;
import com.llw.hospital.api.SysUserRoleService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.ds.entity.SysRole;
import com.llw.hospital.ds.entity.SysUserRole;
import com.llw.hospital.ds.mapper.SysRoleDao;
import com.llw.hospital.ds.util.LoginUtil;
import com.llw.hospital.dto.SysRoleDto;
import com.llw.hospital.dto.SysUserRoleDto;
import com.llw.hospital.vo.role.UserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@Service(timeout = 3000)
public class SysUserRoleServiceImpl extends BaseDtoExtendServiceImpl<SysUserRoleDto, SysUserRole> implements SysUserRoleService {

    @Autowired
    SysRoleDao sysRoleDao;

    @Autowired
    SysRoleService sysRoleService;

    @Override
    public String selectRoleIds(Long userId) {
        SysUserRoleDto sysUserRoleDto = new SysUserRoleDto();
        sysUserRoleDto.setUserId(userId);
        List<SysUserRoleDto> list = selectList(sysUserRoleDto);
        String result = "";
        if(!CollectionUtils.isEmpty(list)){
            List<String> ids = new ArrayList<>(list.size());
            list.forEach((item)->{
                ids.add(String.valueOf(item.getRoleId()));
            });
            result = String.join(",",ids);
        }
        return result;
    }


    @Override
    public List<UserRoleDto> selectRoles(Long operUserId) {
        //获取当前登录用户能操作的角色
        SysRoleDto sysRoleDto = new SysRoleDto();
        sysRoleDto.setOrgId(LoginUtil.getCurrentUserOrgId());
        sysRoleDto.setEnabled(BaseConstants.STATUS_NORMAL);
        List<SysRoleDto> allList = sysRoleService.selectList(sysRoleDto);

        //当前操作人自己拥有的角色Id
        Set<Long> allRoleIds = new HashSet<>(allList.size());
        for(SysRoleDto tmp:allList){
            allRoleIds.add(tmp.getRoleId());
        }
        Example example = new Example(SysRole.class);
        Set<Long> targetRoleIds = new HashSet<>(allRoleIds.size());
        targetRoleIds.addAll(allRoleIds);
        if(!CollectionUtils.isEmpty(targetRoleIds)){
            example.createCriteria().andIn("roleId",targetRoleIds);
            List<SysRole> roles = sysRoleDao.selectByExample(example);
            List<UserRoleDto> result = mapperFactory.getMapperFacade().mapAsList(roles, UserRoleDto.class);
            return result;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<UserRoleDto> selectRoles(Long userId, Long operUserId) {
        SysUserRoleDto sysUserRoleDto = new SysUserRoleDto();
        sysUserRoleDto.setUserId(userId);
        List<SysUserRoleDto> currentList = selectList(sysUserRoleDto);


        //获取当前登录用户能操作的角色 默认是本机构的所有角色
        SysRoleDto sysRoleDto = new SysRoleDto();
        sysRoleDto.setOrgId(LoginUtil.getCurrentUserOrgId());
        sysRoleDto.setEnabled(BaseConstants.STATUS_NORMAL);
        List<SysRoleDto> allList = sysRoleService.selectList(sysRoleDto);

        //当前被操作用户已有的角色
        Set<Long> currentRoleIds = new HashSet<>(currentList.size());
        for(SysUserRoleDto tmp:currentList){
            currentRoleIds.add(tmp.getRoleId());
        }

        //当前操作人自己拥有的角色Id
        Set<Long> allRoleIds = new HashSet<>(allList.size());
        for(SysRoleDto tmp:allList){
            allRoleIds.add(tmp.getRoleId());
        }
        Example example = new Example(SysRole.class);
        Set<Long> targetRoleIds = new HashSet<>(allRoleIds.size());
        targetRoleIds.addAll(allRoleIds);
        targetRoleIds.addAll(currentRoleIds);
        List<SysRole> roles = new ArrayList<>();
        if(!CollectionUtils.isEmpty(targetRoleIds)){
            example.createCriteria().andIn("roleId",targetRoleIds);
            roles = sysRoleDao.selectByExample(example);
        }
        List<UserRoleDto> result = mapperFactory.getMapperFacade().mapAsList(roles, UserRoleDto.class);

        for(UserRoleDto userRoleDto:result){
            //选中已经分配的角色
            if(currentRoleIds.contains(userRoleDto.getRoleId())){
                userRoleDto.setChecked(true);
            }
            //禁用掉没有权限操作的角色
            if(!allRoleIds.contains(userRoleDto.getRoleId())){
                userRoleDto.setDisable(true);
            }
        }

        return result;
    }
}
