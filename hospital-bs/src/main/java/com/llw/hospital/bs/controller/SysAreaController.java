package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.SysAreaService;
import com.llw.hospital.dto.SysAreaDto;
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
@RequestMapping("/medical/sysArea")
public class SysAreaController extends BaseController{
    @Autowired
    private SysAreaService sysAreaService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("inst:sysArea:list")
    public BaseResp list(SysAreaDto sysAreaDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageInfo pageInfo = sysAreaService.selectByPage(pageNo, pageSize, sysAreaDto);
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
    @RequiresPermissions("inst:sysArea:info")
    public BaseResp info(Long pk) {
        try {
            SysAreaDto sysAreaDto = sysAreaService.selectByPrimaryKey(pk);
            return BaseResp.success(sysAreaDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    @RequiresPermissions("inst:sysarea:save")
    public BaseResp save(SysAreaDto sysAreaDto) {
        try {
            int rows = sysAreaService.insertSelective(sysAreaDto);
            if (rows > 0) {
                return BaseResp.success(sysAreaDto);
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
    @RequiresPermissions("inst:sysarea:update")
    public BaseResp update(SysAreaDto sysAreaDto) {
        try {
            int rows = sysAreaService.updateByPrimaryKeySelective(sysAreaDto);
            if (rows > 0) {
                return BaseResp.success(sysAreaDto);
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
    @RequiresPermissions("inst:sysarea:delete")
    public BaseResp delete(Long pk) {
        try {
            int rows = sysAreaService.deleteByPrimaryKey(pk);
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
