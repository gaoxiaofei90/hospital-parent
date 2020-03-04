package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.hospital.api.SysOperLogService;
import com.llw.hospital.dto.SysOperLogDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 操作日志
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
@RestController
@RequestMapping("/medical/sysOperLog")
public class SysOperLogController extends BaseController{
    @Autowired
    private SysOperLogService sysOperLogService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("medical:sysOperLog:list")
    public BaseResp list(SysOperLogDto sysOperLogDto,Date createTimeStart,Date createTimeEnd,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            LLwExample lLwExample=new LLwExample(sysOperLogDto);
            if(createTimeStart!=null){
                lLwExample.andGreaterThanOrEqualTo("createTime",createTimeStart);
            }
            if(createTimeEnd!=null){
                  lLwExample.andLessThan("createTime",createTimeEnd);
            }
            PageInfo pageInfo = sysOperLogService.selectByPageInOrg(pageNo,pageSize,lLwExample);
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
    @RequiresPermissions("medical:sysOperLog:info")
    public BaseResp info(Long pk) {
        try {
            SysOperLogDto sysOperLogDto = sysOperLogService.selectByPrimaryKey(pk);
            return BaseResp.success(sysOperLogDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save.jspx")
    @RequiresPermissions("medical:sysoperlog:save")
    public BaseResp save(SysOperLogDto sysOperLogDto) {
        try {
            int rows = sysOperLogService.insertSelective(sysOperLogDto);
            if (rows > 0) {
                return BaseResp.success(sysOperLogDto);
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
    @RequiresPermissions("medical:sysoperlog:update")
    public BaseResp update(SysOperLogDto sysOperLogDto) {
        try {
            int rows = sysOperLogService.updateByPrimaryKeySelective(sysOperLogDto);
            if (rows > 0) {
                return BaseResp.success(sysOperLogDto);
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
    @RequiresPermissions("medical:sysoperlog:delete")
    public BaseResp delete(Long pk) {
        try {
            int rows = sysOperLogService.deleteByPrimaryKey(pk);
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
