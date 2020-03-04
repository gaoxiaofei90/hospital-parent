package com.llw.hospital.bs.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.llw.hospital.api.SysAreaService;
import com.llw.hospital.dto.SysAreaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AreaService {
	
	@Autowired
    private SysAreaService upmsAreaService;
	
	public  List<SysAreaDto> getAreaListByParentId(String pid) {
		List<SysAreaDto> dto =upmsAreaService.getAreaListByParentId(pid);
		if(CollectionUtils.isEmpty(dto)) {
			return null;
		}
		return dto;
	}
	
}
