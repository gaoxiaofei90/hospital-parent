package com.llw.hospital.ds.mapper;


import com.llw.hospital.dto.SysPermissionDto;
import com.llw.hospital.ds.sqlprovide.UpmsApiSqlProvide;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 用户VOMapper
 * Created by shuzheng on 2017/01/07.
 */
public interface SysUpmsApiMapper {

	// 根据角色Id获取所拥有的权限
	@ResultType(SysPermissionDto.class)
	@Select("<script>"+
			"SELECT permission_id, pid, name, type, permission_value, uri, icon, enabled, orders\n" +
			"FROM sys_permission up\n" +
			"WHERE " +
			"up.enabled = 1" +
			"<if test='modules != null'>"+
				" AND up.modules = #{modules} " +
			"</if>"+
			"AND up.permission_id IN " +
				"(SELECT permission_id\n" +
				"FROM  sys_role_permission rp\n" +
				"WHERE rp.role_id in ( select role_id from sys_user_role where user_id = #{user_id}" +
				")" +
			")\n" +
			"ORDER BY up.orders asc"
	+"</script>")
	List<SysPermissionDto> selectPermissionByUserId(Map<String, Object> params);

	// 根据角色Id获取指定模块的所有权限
	@ResultType(SysPermissionDto.class)
	@SelectProvider(type = UpmsApiSqlProvide.class,method = "selectPermissionByUserIdInModules")
	List<SysPermissionDto> selectPermissionByUserIdInModules(Map<String, Object> params);
}