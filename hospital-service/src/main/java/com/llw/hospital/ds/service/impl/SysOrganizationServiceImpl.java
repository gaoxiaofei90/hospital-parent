package com.llw.hospital.ds.service.impl;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.api.SysRolePermissionService;
import com.llw.hospital.api.SysRoleService;
import com.llw.hospital.api.SysUserRoleService;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.util.DatabaseTimeUtil;
import com.llw.hospital.ds.entity.SysOrganization;
import com.llw.hospital.ds.mapper.SysOrganizationDao;
import com.llw.hospital.ds.mapper.SysUserDao;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.dto.SysOrganizationTreeDto;
import com.llw.hospital.dto.SysRoleDto;
import com.llw.hospital.dto.SysRolePermissionDto;
import com.llw.hospital.dto.SysUserDto;
import com.llw.hospital.dto.SysUserRoleDto;
import com.llw.hospital.util.OrgUtil;


@Component
@Service(timeout = 3000)
public class SysOrganizationServiceImpl extends BaseDtoExtendServiceImpl<SysOrganizationDto, SysOrganization> implements SysOrganizationService {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysOrganizationDao organizationDao;
    @Autowired
    SysUserDao sysUserDao;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysOrganizationDto createSysOrganizationDto(SysOrganizationDto sysOrganizationDto) {
        //新增机构前新增用户
        sysOrganizationDto.setIsDel(BaseConstants.NOT_DELETED);
        sysOrganizationDto.setEnabled(BaseConstants.STATUS_NORMAL);
        sysOrganizationDto.setOrgId(generateOrgId(sysOrganizationDto.getPid()));
        sysOrganizationDto.setCtime(DatabaseTimeUtil.getCurrentTime());
        sysOrganizationDto.setUpdateTime(DatabaseTimeUtil.getCurrentTime());
        sysOrganizationDto=insertSelectiveAndGet(sysOrganizationDto);
        return sysOrganizationDto;
    }

    //生成机构id
    private Long generateOrgId(Long pid) {
        List<Long> childIds = organizationDao.getChildOrgIds(pid);
        return OrgUtil.generatorOrgId(pid,childIds);
    }

    @Override
    public int updateSysOrganizationDto(SysOrganizationDto sysOrganizationDto) {
        SysOrganizationDto old = selectByPrimaryKey(sysOrganizationDto.getOrgId());
        //只有pid不让改
        if(null != old){
            sysOrganizationDto.setPid(old.getPid());
            sysOrganizationDto.setUpdateTime(DatabaseTimeUtil.getCurrentTime());
            return updateByPrimaryKey(sysOrganizationDto);
        }else{
            return 0;
        }
    }

    @Override
    public List<SysOrganizationTreeDto> selectChildren(Long pid) {
        return organizationDao.selectChildren(pid);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteOrganization(Long pk) {
		SysOrganizationDto sysOrganizationDto = selectByPrimaryKey(pk);
		 int rows = deleteByPrimaryKey(pk);
         if (rows > 0) {
        	//根据机构id获取此机构下的所有用户
        	 SysUserDto sysUserDto = new SysUserDto();
        	 sysUserDto.setOrgId(sysOrganizationDto.getOrgId());
        	 List<SysUserDto> sysUserList = sysUserService.selectList(sysUserDto);
        	 
        	 if(!CollectionUtils.isEmpty(sysUserList)) {
        		 for(SysUserDto sud : sysUserList) {
        			 sysUserService.deleteByPrimaryKey(sud.getUserId());
        			 //删除用户关联的角色
        			 SysUserRoleDto sysUserRoleDto = new SysUserRoleDto();
            		 sysUserRoleDto.setUserId(sud.getUserId());
            		 sysUserRoleService.delete(sysUserRoleDto);
        		 }
        	 }
        	 
        	 //根据机构id获取此机构下的所有角色
        	 SysRoleDto sysRoleDto = new SysRoleDto();
        	 sysRoleDto.setOrgId(sysOrganizationDto.getOrgId());
        	 List<SysRoleDto> sysRoleList = sysRoleService.selectList(sysRoleDto);
        	 if(!CollectionUtils.isEmpty(sysRoleList)) {
        		 for(SysRoleDto srd : sysRoleList) {
            		 sysRoleService.deleteRole(srd.getRoleId());
            		 
            		 //删除角色菜单表对应数据
            		 SysRolePermissionDto sysRolePermissionDto = new SysRolePermissionDto();
            		 sysRolePermissionDto.setRoleId(srd.getRoleId());
            		 sysRolePermissionService.delete(sysRolePermissionDto);
            		 
            	 }
        	 }
         } else {
             return 0;
         }
		return 1;
	}

	@Override
	public List<SysOrganizationTreeDto> selectChildren2(Long beginId,Long endId) {
		 return organizationDao.selectChildren2(beginId,endId);
	}
}
