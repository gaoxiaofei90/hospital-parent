package com.llw.hospital.ds.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import com.llw.hospital.ds.sqlprovide.RecogSqlProvide;
import com.llw.hospital.dto.RecogExtendDto;

//@Mapper@Repository注解
public interface RecogMapper {
	
	@SelectProvider(type = RecogSqlProvide.class,method = "getRecogDtoList")
	List<RecogExtendDto> getRecogDtoList(Map<String, Object> params);
	
	
	@SelectProvider(type = RecogSqlProvide.class, method = "getStatisticsList")
	List<RecogExtendDto> getRecogDtoStatistics(Map<String, Object> params);
	
	
	@SelectProvider(type = RecogSqlProvide.class, method = "specialPersonCreateModelList")
	List<RecogExtendDto> getSpecialPersonCreateModel(Map<String, Object> params);
	
	
	@SelectProvider(type = RecogSqlProvide.class, method = "specialPersonRegisterList")
	List<RecogExtendDto> getSpecialPersonRegister(Map<String, Object> params);
	
	@SelectProvider(type = RecogSqlProvide.class,method = "getallRecogDtoList")
	List<RecogExtendDto> getallRecogDtoList(Map<String, Object> params);
	
	@SelectProvider(type = RecogSqlProvide.class,method = "getImgUrl")
	List<RecogExtendDto> getImgUrl(Map<String, Object> params);
	
	@SelectProvider(type = RecogSqlProvide.class,method = "getallRecogDtoByParam")
	List<RecogExtendDto> getallRecogDtoByParam(Map<String, Object> params);
	
}
