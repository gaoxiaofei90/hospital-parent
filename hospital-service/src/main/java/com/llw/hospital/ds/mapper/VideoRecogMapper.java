package com.llw.hospital.ds.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import com.llw.hospital.ds.sqlprovide.VideoRecogSqlProvide;
import com.llw.hospital.dto.VideoRecogExtendDto;

/**
 * @author houdi
 *
 */
public interface VideoRecogMapper {
		@SelectProvider(type = VideoRecogSqlProvide.class,method = "getVideoRecogList")
		List<VideoRecogExtendDto> getVideoRecogList(Map<String, Object> params);
		
		@SelectProvider(type = VideoRecogSqlProvide.class,method = "getVideoRecog")
		VideoRecogExtendDto getVideoRecog(Long videoId);
}
