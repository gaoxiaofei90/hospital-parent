package com.llw.hospital.api;

import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.MonitorDto;
import com.llw.hospital.dto.MonitorExtendDto;

/**
 * ${comments}
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-11 10:30:27
 */
public interface MonitorService extends BaseDtoExtendService<MonitorDto> {

	public PageInfo getMonitorDtoList(MonitorExtendDto monitorDto, Integer pageNo, Integer pageSize);
}

