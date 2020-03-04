package com.llw.hospital.ds.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import com.llw.hospital.ds.sqlprovide.MonitorSqlProvide;
import com.llw.hospital.dto.MonitorExtendDto;

public interface MonitorMapper {
		@SelectProvider(type = MonitorSqlProvide.class,method = "getMonitorList")
		List<MonitorExtendDto> getMonitorList(Map<String, Object> params);
}
