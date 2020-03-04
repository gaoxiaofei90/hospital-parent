package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.SysParamService;
import com.llw.hospital.dto.SysParamDto;
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
@RequestMapping("/medical/sysParam")
public class SysParamController extends BaseController{
    @Autowired
    private SysParamService sysParamService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("medical:sysParam:list")
    public BaseResp list(SysParamDto sysParamDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageInfo pageInfo = sysParamService.selectByPage(pageNo, pageSize, sysParamDto);
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
    @RequiresPermissions("medical:sysParam:info")
    public BaseResp info(Long pk) {
        try {
            SysParamDto sysParamDto = sysParamService.selectByPrimaryKey(pk);
            return BaseResp.success(sysParamDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    @RequiresPermissions("medical:sysParam:save")
    public BaseResp save(SysParamDto sysParamDto) {
        try {
            int rows = sysParamService.insertSelective(sysParamDto);
            if (rows > 0) {
                return BaseResp.success(sysParamDto);
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
    @RequiresPermissions("medical:sysParam:update")
    public BaseResp update(SysParamDto sysParamDto) {
        try {
            int rows = sysParamService.updateByPrimaryKeySelective(sysParamDto);
            if (rows > 0) {
                return BaseResp.success(sysParamDto);
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
    @RequiresPermissions("medical:sysParam:delete")
    public BaseResp delete(Long pk) {
        try {
            int rows = sysParamService.deleteByPrimaryKey(pk);
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
