package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.SysPermissionService;
import com.llw.hospital.dto.SysPermissionDto;
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
 * @date 2018-11-05 11:12:22
 */
@RestController
@RequestMapping("/medical/sysPermission")
public class SysPermissionController extends BaseController{
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("medical:sysPermission:list")
    public BaseResp list(SysPermissionDto sysPermissionDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageInfo pageInfo = sysPermissionService.selectByPage(pageNo, pageSize, sysPermissionDto);
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
    @RequiresPermissions("medical:sysPermission:info")
    public BaseResp info(Long pk) {
        try {
            SysPermissionDto sysPermissionDto = sysPermissionService.selectByPrimaryKey(pk);
            return BaseResp.success(sysPermissionDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    @RequiresPermissions("medical:sysPermission:save")
    public BaseResp save(SysPermissionDto sysPermissionDto) {
        try {
            sysPermissionDto.setEnabled(1);
            int rows = sysPermissionService.insertSelective(sysPermissionDto);
            if (rows > 0) {
                return BaseResp.success(sysPermissionDto);
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
    @RequiresPermissions("medical:sysPermission:update")
    public BaseResp update(SysPermissionDto sysPermissionDto) {
        try {
            int rows = sysPermissionService.updateByPrimaryKeySelective(sysPermissionDto);
            if (rows > 0) {
                return BaseResp.success(sysPermissionDto);
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
    @RequiresPermissions("medical:sysPermission:delete")
    public BaseResp delete(Long pk) {
        try {
            int rows = sysPermissionService.deleteByPrimaryKey(pk);
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
