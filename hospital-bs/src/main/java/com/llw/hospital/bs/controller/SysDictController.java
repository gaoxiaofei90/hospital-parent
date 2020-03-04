package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.SysDictService;
import com.llw.hospital.dto.SysDictDto;
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
@RequestMapping("/medical/sysDict")
public class SysDictController extends BaseController{
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("inst:sysDict:list")
    public BaseResp list(SysDictDto sysDictDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            PageInfo pageInfo = sysDictService.selectByPage(pageNo, pageSize, sysDictDto);
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
    @RequiresPermissions("inst:sysDict:info")
    public BaseResp info(Long pk) {
        try {
            SysDictDto sysDictDto = sysDictService.selectByPrimaryKey(pk);
            return BaseResp.success(sysDictDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    @RequiresPermissions("inst:sysdict:save")
    public BaseResp save(SysDictDto sysDictDto) {
        try {
            int rows = sysDictService.insertSelective(sysDictDto);
            if (rows > 0) {
                return BaseResp.success(sysDictDto);
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
    @RequiresPermissions("inst:sysdict:update")
    public BaseResp update(SysDictDto sysDictDto) {
        try {
            int rows = sysDictService.updateByPrimaryKeySelective(sysDictDto);
            if (rows > 0) {
                return BaseResp.success(sysDictDto);
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
    @RequiresPermissions("inst:sysdict:delete")
    public BaseResp delete(Long pk) {
        try {
            int rows = sysDictService.deleteByPrimaryKey(pk);
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
