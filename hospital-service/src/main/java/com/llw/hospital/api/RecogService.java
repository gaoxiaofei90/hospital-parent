package com.llw.hospital.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.RecogDto;
import com.llw.hospital.dto.RecogExtendDto;


public interface RecogService extends BaseDtoExtendService<RecogDto>{
	
	
	
	Long recognize(RecogDto bean) throws Exception;
	/**
	 * 认证记录查询
	 * @param recogExtendDto
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageInfo<?> getRecogDtoList(RecogExtendDto recogExtendDto,Integer pageNo,Integer pageSize);
	
	
	/**
	 * 认证统计记录查询
	 * @param recogExtendDto
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageInfo<?> getRecogStatistics(RecogExtendDto recogExtendDto, Integer pageNo, Integer pageSize);
	
	
	/**
	 * 认证统计记录查询
	 * @param recogExtendDto
	 * @return
	 */
	List<RecogExtendDto> getRecogStatistics(RecogExtendDto recogExtendDto);
	
	
	/**
	 * 模板照及身份证照查询
	 * @param recogExtendDto
	 * @return
	 */
	List<RecogExtendDto> getImgUrl(RecogExtendDto recogExtendDto);

}
