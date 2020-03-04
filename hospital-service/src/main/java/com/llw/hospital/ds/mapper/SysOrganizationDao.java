package com.llw.hospital.ds.mapper;

import com.llw.hospital.ds.entity.SysOrganization;
import com.jcl.orm.tkmapper.BaseMapper;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.dto.SysOrganizationTreeDto;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 机构
 * 
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public interface SysOrganizationDao extends BaseMapper<SysOrganization> {

	@Select("select max(org_id) from sys_organization where pid = #{pid} ")
    Long getMaxChildOrgId(Long pid);

    @Select("select org_id from sys_organization where pid = #{pid} ")
    List<Long> getChildOrgIds(Long pid);

    @ResultType(SysOrganizationTreeDto.class)
    @Select("select t.org_id,t.pid,t.name,t.org_category,t.zone_code,(select count(1) from sys_organization where pid = t.org_id) as child_count from sys_organization t where pid =  #{pid} order by ctime")
    List<SysOrganizationTreeDto> selectChildren(Long pid);
    
    @ResultType(SysOrganizationTreeDto.class)
    @Select("select t.org_id from sys_organization t where org_category = 3 and pid = 0")
    List<SysOrganizationTreeDto> getOrgId();
    
    @ResultType(SysOrganizationTreeDto.class)
    @Select("select t.org_id,t.name,t.pid,t.org_category,t2.name pname from sys_organization t,sys_organization t2 where t.pid = t2.org_id and t.org_id > #{bd} and t.org_id <= #{ed}")
    List<SysOrganizationTreeDto> selectChildren2(@Param("bd") Long bd,@Param("ed") Long ed);
    
    @ResultType(SysOrganizationTreeDto.class)
    @Select("select * from sys_organization t where org_category = 3 and pid != 0")
    List<SysOrganizationDto> getSysOrganizationDto();
    
    @ResultType(SysOrganizationTreeDto.class)
    @Select("select t.org_id from sys_organization t")
    List<SysOrganizationTreeDto> getAllOrgId();
    
    @Select("select org_id from sys_organization where name = #{orgName} ")
    Long getOrgIds(String orgName);
    
    @ResultType(SysOrganizationTreeDto.class)
    @Select("select t.org_id,t.name,t.pid,t.org_category,t2.name pname from sys_organization t,sys_organization t2 where t.pid = t2.org_id and t.org_id > #{bd} and t.org_id <= #{ed} and t.org_category in (1,3,4,5,6,7) and t2.pid != 0")
    List<SysOrganizationTreeDto> selectChildren3(@Param("bd") Long bd,@Param("ed") Long ed);
}
