package com.llw.hospital.api.vo.login;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 菜单管理
 * 
 */
@ApiModel(value = "SysMenu",description = "菜单信息")
public class SysMenu  {
	
	@ApiModelProperty(value = " 菜单ID")
	private Long menuId;

	@ApiModelProperty(value = "父菜单ID，一级菜单为0")
	private Long parentId;
	
	@ApiModelProperty(value = "父菜单名称")
	private String parentName;

	@ApiModelProperty(value = "菜单名称")
	private String name;

	@ApiModelProperty(value = "菜单URL")
	private String url;

/*	@ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
	private String perms;

	@ApiModelProperty(value = "类型     0：目录   1：菜单   2：按钮")
	private Integer type;
*/
	@ApiModelProperty(value = "菜单图标")
	private String icon;
	
	@ApiModelProperty(value = "ios菜单图标")
	private String iconIos;

	@ApiModelProperty(value = " 排序")
	private Integer orderNum;
	
	@ApiModelProperty(value = "权限值")
    private JSONObject permissionValue;
/*	
	@ApiModelProperty(value = " ztree属性")
	private Boolean open;*/

	@ApiModelProperty(value = " 子菜单列表")
	private List<?> list;

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getMenuId() {
		return menuId;
	}
	
	/**
	 * 设置：父菜单ID，一级菜单为0
	 * @param parentId 父菜单ID，一级菜单为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取：父菜单ID，一级菜单为0
	 * @return Long
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 设置：菜单名称
	 * @param name 菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：菜单名称
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置：菜单URL
	 * @param url 菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：菜单URL
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	

	

	
	/**
	 * 设置：排序
	 * @param orderNum 排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取：排序
	 * @return Integer
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconIos() {
		return iconIos;
	}

	public void setIconIos(String iconIos) {
		this.iconIos = iconIos;
	}

	public JSONObject getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
//		this.permissionValue = permissionValue;
		if(!StringUtils.isEmpty(permissionValue)){
			this.permissionValue = JSONObject.parseObject(permissionValue);
		}
	}

	
	public static void main(String[] args){
		
		String str ="";
		String str1 = null;
		String str2 = " ";
		
		System.out.println(StringUtils.isEmpty(str));
		System.out.println(StringUtils.isEmpty(str1));
		System.out.println(StringUtils.isEmpty(str2));
	}
	
}
