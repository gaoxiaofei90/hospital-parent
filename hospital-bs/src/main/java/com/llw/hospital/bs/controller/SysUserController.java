package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.common.util.JSONUtils;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.api.SysUserRoleService;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.bs.util.LoginUtil;
import com.llw.hospital.common.util.StringUtils;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.dto.SysUserDto;
import com.llw.hospital.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@RestController
@RequestMapping("/medical/sysUser")
public class SysUserController extends BaseController{
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysOrganizationService organizationService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    public BaseResp list(SysUserDto sysUserDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageInfo pageInfo = sysUserService.selectByPageInOrg(pageNo, pageSize, sysUserDto, UserVO.class);
            System.out.println("hhhhhaaaaaaaaa99995689-------");
            List<SysUserDto> list = pageInfo.getList();
            if (list != null && list.size() > 0) {
                    List resultList = new ArrayList();
                for (SysUserDto sysUser : list) {
                    SysOrganizationDto sysOrganizationDto =  new SysOrganizationDto();
                    sysOrganizationDto.setOrgId(sysUser.getOrgId());
                    sysOrganizationDto = organizationService.selectOne(sysOrganizationDto);
                    if(sysOrganizationDto != null){
                        sysUser.setOrgName(sysOrganizationDto.getName());
                        resultList.add(sysUser);
                    }
                }
                pageInfo.setList(resultList);
            }
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
    public BaseResp info(Long pk) {
        try {
            SysUserDto sysUserDto = sysUserService.selectByPrimaryKey(pk);
            sysUserDto.setPassword(null);
            JSONObject result = JSONUtils.parseObject(JSONUtils.toJSONString(sysUserDto));
            result.put("roles",sysUserRoleService.selectRoles(sysUserDto.getUserId(), LoginUtil.getCurrentUser().getUserId()));
            return BaseResp.success(sysUserDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 角色信息
     */
    @RequestMapping("/roles.jspx")
    public BaseResp roles(Long pk) {
        try {
            if(null != pk){
                return BaseResp.success(sysUserRoleService.selectRoles(pk, LoginUtil.getCurrentUser().getUserId()));
            }else{
                return BaseResp.success(sysUserRoleService.selectRoles(LoginUtil.getCurrentUser().getUserId()));
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    public BaseResp save(SysUserDto sysUserDto,String roleIds) {
        try {
            List<Long> roleId = new ArrayList<>(1);
            if(!StringUtils.isEmpty(roleIds)){
                String[] roleIdArr = roleIds.split(",");
                for(String id:roleIdArr){
                    roleId.add(Long.parseLong(id));
                }
            }
            sysUserService.createUser(sysUserDto,roleId);
            return BaseResp.success("保存成功");
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update.jspx")
    public BaseResp update(SysUserDto sysUserDto,String roleIds) {
        try {
            List<Long> roleId = new ArrayList<>(1);
            if(!StringUtils.isEmpty(roleIds)){
                String[] roleIdArr = roleIds.split(",");
                for(String id:roleIdArr){
                    roleId.add(Long.parseLong(id));
                }
            }
            sysUserService.updateUser(sysUserDto,roleId);
            return BaseResp.success("修改成功");
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete.jspx")
    public BaseResp delete(Long pk) {
        try {
            sysUserService.deleteUser(pk);
            return BaseResp.success(true);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }



}
