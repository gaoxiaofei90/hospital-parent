package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.MonitorService;
import com.llw.hospital.ds.entity.Monitor;
import com.llw.hospital.ds.mapper.MonitorMapper;
import com.llw.hospital.dto.MonitorDto;
import com.llw.hospital.dto.MonitorExtendDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Service(timeout = 3000)
public class MonitorServiceImpl extends BaseDtoExtendServiceImpl<MonitorDto, Monitor> implements MonitorService {

	@Autowired
	MonitorMapper mapper;
	
	@Override
	public PageInfo getMonitorDtoList(MonitorExtendDto monitorDto, Integer pageNo, Integer pageSize) {
		Map<String, Object> params = new HashMap<>();
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		params.put("keyword", monitorDto.getKeyword());
		params.put("deviceType", monitorDto.getDeviceType());
		PageHelper.startPage(pageNo, pageSize);
		List<MonitorExtendDto> list = mapper.getMonitorList(params);
		PageInfo page = new PageInfo(list);
		return page;
	}

}
