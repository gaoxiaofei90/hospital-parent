package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jcl.common.base.BaseController;
import com.jcl.common.util.PropertiesUtils;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.api.SysUpmsApiService;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.bs.util.LoginUtil;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.util.StringUtils;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.dto.SysPermissionDto;
import com.llw.hospital.dto.SysUserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单controller
 * Created by shengpeng on 2018/11/05.
 */
@Controller
@RequestMapping("/manage")
public class SysManageController extends BaseController {

	@Autowired
	private SysUpmsApiService upmsApiService;

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysOrganizationService upmsOrganizationService;



	@RequestMapping(value = "/index.jspx", method = RequestMethod.GET)
	public String index(HttpServletRequest request,ModelMap modelMap) {
		// 当前登录用户权限
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		SysUserDto dto = new SysUserDto();
		dto.setUsername(username);
		SysUserDto sysUser = LoginUtil.getCurrentUser();
		SysOrganizationDto organizationDto = upmsOrganizationService.selectByPrimaryKey(sysUser.getOrgId());
		List<SysPermissionDto> upmsPermissions = upmsApiService.selectPermissionByUserId(sysUser.getUserId(), BaseConstants.MODULES_BS);
		//超级管理员 给一个代码生成器的权限
		if(sysUser.getUserId().equals(1L) && PropertiesUtils.getEnv().equals("dev")){
			SysPermissionDto codePermission = new SysPermissionDto();
			codePermission.setEnabled(1);
			codePermission.setUri("/modules/code/index.html");
			codePermission.setPermissionId(999999L);
			codePermission.setIcon("icon-xitong");
			codePermission.setName("代码生成");
			codePermission.setModules(0);
			codePermission.setPid(1L);
			codePermission.setType(2);
			codePermission.setOrders(9999);
			upmsPermissions.add(codePermission);
		}
		List<SysMenuEntity> menu = changePermissionToMenu(upmsPermissions,request.getServletContext().getContextPath());
		modelMap.put("menu",menu);
		modelMap.put("username",username);
		modelMap.put("sysUser", sysUser);
		modelMap.put("realName",sysUser.getName());
		modelMap.put("upmsPermissions", upmsPermissions);
		modelMap.put("org",organizationDto);

		//是否有子机构
		SysOrganizationDto sysOrganizationDto = new SysOrganizationDto();
		sysOrganizationDto.setPid(organizationDto.getOrgId());
		if(upmsOrganizationService.selectCount(sysOrganizationDto) > 0){
			modelMap.put("hasSubOrg","false");
		}else{
			modelMap.put("hasSubOrg","true");
		}
		return "index.jsp";
	}

	private List<SysMenuEntity> changePermissionToMenu(List<SysPermissionDto> upmsPermissions, String contextPath) {
		List<SysMenuEntity> entityList = new ArrayList<>(upmsPermissions.size());
		for(SysPermissionDto permission:upmsPermissions){
			SysMenuEntity entity = new SysMenuEntity();
			entity.setIcon(permission.getIcon());
			entity.setMenuId(permission.getPermissionId().longValue());
			entity.setName(permission.getName());
			entity.setParentId(permission.getPid().longValue());
			entity.setType(permission.getType());
			if(permission.getType() == 2 && StringUtils.isNotBlank(permission.getUri())){
				if(permission.getUri().startsWith("/")){
					entity.setUrl(contextPath+permission.getUri());
				}else{
					entity.setUrl(contextPath+"/"+permission.getUri());
				}
			}
			entity.setOrderNum(permission.getOrders().intValue());
			entity.setPerms(permission.getPermissionValue());
			entityList.add(entity);
		}

		//获取所有的顶级菜单 暂时支持两级菜单
		List<SysMenuEntity> topMenu =  getMenuListByParentId(entityList,0L);
		for(SysMenuEntity entity:topMenu){
			List<SysMenuEntity> children = getMenuListByParentId(entityList,entity.getMenuId());
			entity.setList(children);
		}

		return topMenu;
	}

	private List<SysMenuEntity> getMenuListByParentId(List<SysMenuEntity> entityList, Long parentId) {
		List<SysMenuEntity> children = new ArrayList<>(2);
		for(SysMenuEntity menu:entityList){
			if(menu.getParentId().equals(parentId)){
				children.add(menu);
			}
		}
		return children;
	}
}