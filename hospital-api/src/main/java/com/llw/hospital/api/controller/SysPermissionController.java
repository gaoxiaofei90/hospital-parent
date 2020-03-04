package com.llw.hospital.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jcl.common.base.BaseController;
import com.llw.hospital.api.SysUpmsApiService;
import com.llw.hospital.api.shiro.JWTFilter;
import com.llw.hospital.api.util.MapUtil;
import com.llw.hospital.api.vo.ResponseParam;
import com.llw.hospital.api.vo.common.SysPermissionResponse;
import com.llw.hospital.dto.SysPermissionDto;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags="权限相关接口列表")
@RequestMapping("/api/user/permission")
public class SysPermissionController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SysPermissionController.class);

	@Autowired
	private SysUpmsApiService upmsApiService;

	@PostMapping("/list")
	@ApiOperation(value="当前权限列表",notes="当前权限列表")
	@ApiResponses({
			@ApiResponse(code = 200,message = "success",response = SysPermissionResponse.class),
			@ApiResponse(code = 400,message = "系统异常")
	})
	@ApiImplicitParams(@ApiImplicitParam(name = "auth",value = "access token，登录接口返回的",required = true,dataType = "String",paramType = "header"))
	public ResponseParam<List<SysPermissionResponse>> getPermission(){
		try{
			Long userId=JWTFilter.currentUser.get().getUserId();
			List<SysPermissionDto> upmsPermissions=upmsApiService.selectPermissionByUserId(userId,1);
			List<SysPermissionResponse> list=new ArrayList<>();
			if(CollectionUtils.isNotEmpty(upmsPermissions)){
				for(SysPermissionDto s:upmsPermissions){
					s.setUri("");
					SysPermissionResponse ss=MapUtil.mapObejctTo(s,SysPermissionResponse.class);
					list.add(ss);
				}
			}
			return ResponseParam.ok().setData(list);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ResponseParam.error("400","系统异常");
		}
	}
}
