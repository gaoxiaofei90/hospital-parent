package com.llw.hospital.api;

import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.VideoRecogDto;
import com.llw.hospital.dto.VideoRecogExtendDto;

/**
 * 录像拍照认证
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-03-03 10:18:37
 */
public interface VideoRecogService extends BaseDtoExtendService<VideoRecogDto> {
	public PageInfo getVideoRecogDtoList(VideoRecogExtendDto videoRecogExtendDto,Integer pageNo,Integer pageSize);
	
	public VideoRecogExtendDto getVideoRecogDto(Long videoId);
	
	public VideoRecogDto recognize(VideoRecogDto videoRecogDto) throws Exception;
	
	//public VideoRecogDto entrustPersonRecognize(VideoRecogDto videoRecogDto,Medical3Dto medical3Dto) throws Exception;
	
}

