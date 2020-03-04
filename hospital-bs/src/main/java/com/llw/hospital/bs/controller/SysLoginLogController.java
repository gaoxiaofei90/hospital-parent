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
 * 登录日志
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:23
 */
@RestController
@RequestMapping("/medical/sysLoginLog")
@RequiresPermissions("medical:sysLoginLog:list")
public class SysLoginLogController extends BaseController{

    @Autowired
    private SysOperLogService sysOperLogService;

    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("medical:sysLoginLog:list")
    public BaseResp list(SysOperLogDto sysOperLogDto,Date createTimeStart,Date createTimeEnd,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
             sysOperLogDto.setModules("登录模块");
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
}
