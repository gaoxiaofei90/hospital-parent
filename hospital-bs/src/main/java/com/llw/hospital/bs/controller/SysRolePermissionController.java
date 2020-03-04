package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.SysRolePermissionService;
import com.llw.hospital.dto.SysRolePermissionDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:23
 */
@RestController
@RequestMapping("/medical/rolePermission")
public class SysRolePermissionController extends BaseController{
    @Autowired
    private SysRolePermissionService rolePermissionService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("medical:rolePermission:list")
    public BaseResp list(SysRolePermissionDto rolePermissionDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageInfo pageInfo = rolePermissionService.selectByPage(pageNo, pageSize, rolePermissionDto);
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
    @RequiresPermissions("medical:rolePermission:info")
    public BaseResp info(Long pk) {
        try {
            SysRolePermissionDto rolePermissionDto = rolePermissionService.selectByPrimaryKey(pk);
            return BaseResp.success(rolePermissionDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    @RequiresPermissions("medical:rolePermission:save")
    public BaseResp save(SysRolePermissionDto rolePermissionDto) {
        try {
            int rows = rolePermissionService.insertSelective(rolePermissionDto);
            if (rows > 0) {
                return BaseResp.success(rolePermissionDto);
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
    @RequiresPermissions("medical:rolePermission:update")
    public BaseResp update(SysRolePermissionDto rolePermissionDto) {
        try {
            int rows = rolePermissionService.updateByPrimaryKeySelective(rolePermissionDto);
            if (rows > 0) {
                return BaseResp.success(rolePermissionDto);
            } else {
                return BaseResp.error(500, "记录修改失败");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete.jspx")
    @RequiresPermissions("medical:rolePermission:delete")
    public BaseResp delete(Long pk) {
        try {
            int rows = rolePermissionService.deleteByPrimaryKey(pk);
            if (rows > 0) {
                return BaseResp.success(true);
            } else {
                return BaseResp.error(500, "记录删除失败");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

}
