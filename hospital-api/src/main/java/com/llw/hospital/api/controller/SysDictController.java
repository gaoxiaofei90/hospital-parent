package com.llw.hospital.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jcl.common.base.BaseController;
import com.llw.hospital.api.BusinessService;
import com.llw.hospital.api.SysDictService;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.api.util.MapUtil;
import com.llw.hospital.api.vo.ResponseParam;
import com.llw.hospital.api.vo.common.BusinessSystem;
import com.llw.hospital.api.vo.common.SysDictResponse;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.ds.entity.Business;
import com.llw.hospital.dto.BusinessDto;
import com.llw.hospital.dto.SysDictDto;
import com.llw.hospital.dto.SysOrganizationDto;

@RestController
@Api(tags="字典相关接口列表")
@RequestMapping("/api/public/dict")
public class SysDictController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SysDictController.class);

	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
    SysOrganizationService organizationService;
	
	@Autowired
	BusinessService businessService;
	

	@PostMapping("/list")
	@ApiOperation(value="获取系统字典数据",notes="获取系统字典数据")
	@ApiImplicitParams(@ApiImplicitParam(name = "auth",value = "access token，可以通过token接口获取，也可以用户名和密码加mac地址生成",required = true,dataType = "String",paramType = "header"))
	@ApiResponses({
			@ApiResponse(code = 200,message = "success"),
			@ApiResponse(code = 400,message = "系统异常")
	})
	public ResponseParam<List<SysDictResponse>> getSysDict(){
		try{
			SysDictDto sysDictDto=new SysDictDto();
			sysDictDto.setStatus(BaseConstants.STATUS_NORMAL);
			List<SysDictDto> list= sysDictService.selectList(sysDictDto);
			//List<SysDictDto> list=sysDictService.selectListInOrg(sysDictDto);
			List<SysDictResponse> result=new ArrayList<>();
			for(SysDictDto dto:list){
				SysDictResponse sysDictResponse=MapUtil.mapObejctTo(dto,SysDictResponse.class);
				result.add(sysDictResponse);
			}
			return ResponseParam.ok().setData(result);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ResponseParam.error("400","系统异常");
		}
	}
	

	@PostMapping("/departList")
	@ApiOperation(value="获取医院科室",notes="获取医院科室")
	@ApiImplicitParams(@ApiImplicitParam(name = "auth",value = "access token，登录接口返回的",required = true,dataType = "String",paramType = "header"))
	@ApiResponses({
		@ApiResponse(code = 200,message = "success"),
		@ApiResponse(code = 400,message = "系统异常")
	})
	public ResponseParam<List<Map<String,String>>> getDepartList(){
		try{
			List retList = new ArrayList();
            List<SysOrganizationDto> list = organizationService.selectListInOrg(new SysOrganizationDto());
            if(list!=null && list.size()>0){
            	for(SysOrganizationDto bean : list){
					Map<String,String> mm = new HashMap<String,String>();
					mm.put("orgId", bean.getOrgId()+"");
					mm.put("name", bean.getName());
					retList.add(mm);
        		}
            }
			return ResponseParam.ok().setData(retList);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return ResponseParam.error("400","系统异常");
		}
	}
	
	
	@GetMapping("/getBusiness")
	@ApiOperation(value="中心端：获取业务类型")
	@ApiImplicitParams({@ApiImplicitParam(name = "auth",value = "access token，可以通过token接口获取，也可以用户名和密码加mac地址生成",required = true,dataType = "String",paramType = "header")
	})
	@ApiResponses({
		@ApiResponse(code = 1001,message = "参数不正确"),
		@ApiResponse(code = 1002,message = "无数据")
	})
	public  ResponseParam<BusinessSystem> getBusiness()
	{
		
		BusinessDto bd = new BusinessDto();
		List<BusinessDto> list = businessService.selectList(bd);
		List<Business> result = new ArrayList<Business>();
		if(!CollectionUtils.isEmpty(list)) {
			for(BusinessDto businessDto : list) {
				Business business = new Business();
				BeanUtils.copyProperties(businessDto, business);
				result.add(business);
 			}
		}else {
			return ResponseParam.error("1002","无数据");
		}	
		return  ResponseParam.ok().setData(result);
	}
}
