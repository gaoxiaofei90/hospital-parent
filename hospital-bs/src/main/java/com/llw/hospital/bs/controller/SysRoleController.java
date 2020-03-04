package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.hospital.api.SysPermissionService;
import com.llw.hospital.api.SysRoleService;
import com.llw.hospital.api.SysUpmsApiService;
import com.llw.hospital.bs.util.LoginUtil;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.dto.SysRolePermissionDto;
import com.llw.hospital.dto.SysPermissionDto;
import com.llw.hospital.dto.SysRoleDto;
import com.llw.hospital.vo.role.RolePermissionVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@RestController
@RequestMapping("/medical/sysRole")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysUpmsApiService sysUpmsApiService;
    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("medical:sysRole:list")
    public BaseResp list(SysRoleDto sysRoleDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            sysRoleDto.setOrgId(LoginUtil.getCurrentUser().getOrgId());
            PageInfo pageInfo = sysRoleService.selectByPageInOrg(pageNo, pageSize, sysRoleDto);
            LLwExample example = new LLwExample();
            return BaseResp.success(pageInfo);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 信息
     */
    @RequestMapping("/info.jspx")
    @RequiresPermissions("medical:sysRole:info")
    public BaseResp info(Long pk) {
        try {
            SysRoleDto sysRoleDto = sysRoleService.selectByPrimaryKeyInOrg(pk);
            return BaseResp.success(sysRoleDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    @RequiresPermissions("medical:sysRole:save")
    public BaseResp save(SysRoleDto sysRoleDto) {
        try {
            sysRoleDto.setOrgId(LoginUtil.getCurrentUser().getOrgId());
            sysRoleDto.setEnabled(BaseConstants.STATUS_NORMAL);
            sysRoleDto.setCreateUser(String.valueOf(LoginUtil.getCurrentUser().getUserId()));
            int rows = sysRoleService.insertSelective(sysRoleDto);
            if (rows > 0) {
                return BaseResp.success(sysRoleDto);
            } else {
                return BaseResp.error(500, "记录保存失败");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update.jspx")
    @RequiresPermissions("medical:sysRole:update")
    public BaseResp update(SysRoleDto sysRoleDto) {
        try {
            int rows = sysRoleService.updateByPrimaryKeySelective(sysRoleDto);
            if (rows > 0) {
                return BaseResp.success(sysRoleDto);
            } else {
                return BaseResp.error(500, "记录修改失败");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 获取权限
     */
    @RequestMapping(value = "/permissionAssign.jspx")
    public BaseResp toPermissionAssign(Long roleId,Integer modules) {
        try {
            SysPermissionDto sysPermissionDto = new SysPermissionDto();
            sysPermissionDto.setEnabled(BaseConstants.STATUS_NORMAL);
            //系统所有权限
            List<SysPermissionDto> allPermission = sysPermissionService.selectList(sysPermissionDto);
            final Map<Long,SysPermissionDto> permissionDtoMap = new HashMap<>(allPermission.size());
            allPermission.forEach((item)->permissionDtoMap.put(item.getPermissionId(),item));

            //被操作角色已有的权限
            List<SysRolePermissionDto> rolePermissionDtos = sysRoleService.getPermissions(roleId);
            Map<Long, SysRolePermissionDto> rolePermissionMap = new HashMap<>(rolePermissionDtos.size());
            rolePermissionDtos.forEach((item) -> rolePermissionMap.put(item.getPermissionId(),item));

            //当前登录人拥有的权限
            List<SysPermissionDto> ownPermissionDtos = sysUpmsApiService.selectPermissionByUserId(LoginUtil.getCurrentUser().getUserId());
            List<RolePermissionVO> result = new ArrayList<>(ownPermissionDtos.size());
            ownPermissionDtos.forEach((item)->{
                RolePermissionVO vo = new RolePermissionVO();
                SysPermissionDto dto = permissionDtoMap.get(item.getPermissionId());
                BeanUtils.copyProperties(dto,vo);
                vo.setChecked(rolePermissionMap.containsKey(item.getPermissionId())?1:0);
                result.add(vo);
            });

            if(null != modules){
                return BaseResp.success(result.stream().filter(p -> p.getModules().equals(modules)).collect(Collectors.toList()));
            }else{
                return BaseResp.success(result);
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 分配权限
     */
    @RequestMapping(value = "/savePermission.jspx")
    public BaseResp permissionAssign(Long roleId,String permissionIds) {
        try {
            List<Long> permissionList = new ArrayList<>();
            if(!StringUtils.isEmpty(permissionIds)){
                StringTokenizer t=new StringTokenizer(permissionIds,",");
                while(t.hasMoreElements()){
                    permissionList.add(Long.valueOf(t.nextToken()));
                }
            }

            sysRoleService.reAssignPermissions(roleId,permissionList);
            return BaseResp.success("保存成功");
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete.jspx")
    @RequiresPermissions("medical:sysRole:delete")
    public BaseResp delete(Long pk) {
        try {
             sysRoleService.deleteRole(pk);
             return BaseResp.success(true);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }
}
