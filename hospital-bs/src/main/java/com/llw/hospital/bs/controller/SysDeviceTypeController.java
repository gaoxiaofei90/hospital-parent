package com.llw.hospital.bs.controller;

import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.SysDeviceTypeService;
import com.llw.hospital.dto.SysDeviceTypeDto;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.alibaba.dubbo.config.annotation.Reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${comments}
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-09-10 15:58:44
 */
@RestController
@RequestMapping("/medical/sysDeviceType")
public class SysDeviceTypeController extends BaseController{
    
	
	@Autowired
    SysDeviceTypeService sysDeviceTypeService;


    /**
     * 列表
     */
    @RequestMapping("/list.jspx")
    @RequiresPermissions("medical:sysDeviceType:list")
    public BaseResp list(SysDeviceTypeDto sysDeviceTypeDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageInfo pageInfo = sysDeviceTypeService.selectByPage(pageNo, pageSize, sysDeviceTypeDto);
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
            SysDeviceTypeDto sysDeviceTypeDto = sysDeviceTypeService.selectByPrimaryKey(pk);
            return BaseResp.success(sysDeviceTypeDto);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }
    
    /**
     * 信息
     */
    @RequestMapping("/getByDeviceLargeType.jspx")
    public BaseResp getByDeviceLargeType(Long deviceLargeType) {
        try {
        	SysDeviceTypeDto queryDto = new SysDeviceTypeDto();
        	queryDto.setDeviceLargeType(deviceLargeType);
            List<SysDeviceTypeDto> list = sysDeviceTypeService.selectList(queryDto);
            return BaseResp.success(list);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

}
