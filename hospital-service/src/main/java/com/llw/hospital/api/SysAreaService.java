package com.llw.hospital.api;

import com.jcl.orm.tkmapper.BaseService;
import com.llw.hospital.dto.SysAreaDto;

import java.util.List;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:23
 */
public interface SysAreaService extends BaseService<SysAreaDto> {

    List<SysAreaDto> getAreaListByParentId(String pid);

}

