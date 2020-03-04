package com.llw.hospital.api;

import java.util.List;

import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.dto.SysOrganizationTreeDto;

/**
 * 机构
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public interface SysOrganizationService extends BaseDtoExtendService<SysOrganizationDto> {

    SysOrganizationDto createSysOrganizationDto(SysOrganizationDto sysOrganizationDto);

    int updateSysOrganizationDto(SysOrganizationDto sysOrganizationDto);

    List<SysOrganizationTreeDto> selectChildren(Long pid);
    
    List<SysOrganizationTreeDto> selectChildren2(Long beginId,Long endId);

    int deleteOrganization(Long pk);
}

