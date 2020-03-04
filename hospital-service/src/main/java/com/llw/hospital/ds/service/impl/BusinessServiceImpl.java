package com.llw.hospital.ds.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.hospital.api.BusinessService;
import com.llw.hospital.ds.entity.Business;
import com.llw.hospital.dto.BusinessDto;


@Component
@Service(timeout = 3000)
public class BusinessServiceImpl extends BaseDtoExtendServiceImpl<BusinessDto, Business> implements BusinessService {

	
	@Override
	@Transactional
	public long DeleteBusiness(String busiType) {
		
		//删除之前先判断机构与系统关系表中是否有对应的业务系统
//    	Example example = new Example(OrgSys.class);
//    	Example.Criteria criteria = example.createCriteria().andLike("busiTypes", "%"+busiType+"%");
//    	List<OrgSys> list = orgSysDao.selectByExample(example);
//    	
//    	if(list.size() > 0){
//    		return -9;
//    	}
    	return this.deleteByPrimaryKey(busiType);
		
	}

	@Override
	@Transactional
	public long SelectBusiness(BusinessDto businessDto) {
		
		BusinessDto business = new BusinessDto();
		business.setBusiType(businessDto.getBusiType());
		List<BusinessDto> list = this.selectList(business);
		if (list.size() > 0) {	
			return -9;
		}else {
			return this.insertSelective(businessDto);
		}
		
	}

}
