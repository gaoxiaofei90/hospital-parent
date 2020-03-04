package com.llw.hospital.api;

import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.BusinessDto;

/**
 * 险种/业务
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-08-26 17:30:44
 */
public interface BusinessService extends BaseDtoExtendService<BusinessDto> {

	long DeleteBusiness(String busiType);
	
	long SelectBusiness(BusinessDto businessDto);
}

