package com.llw.hospital.ds.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.jcl.orm.tkmapper.BaseServiceImpl;
import com.llw.hospital.api.MedicalService;
import com.llw.hospital.api.RecogService;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.ds.entity.Recog;
import com.llw.hospital.ds.mapper.RecogMapper;
import com.llw.hospital.dto.MedicalDto;
import com.llw.hospital.dto.RecogDto;
import com.llw.hospital.dto.RecogExtendDto;
import com.llw.hospital.dto.SysOrganizationDto;


@Component
@Service(timeout = 3000)
public class RecogServiceImpl extends BaseDtoExtendServiceImpl<RecogDto, Recog> implements RecogService  {

	@Autowired
	RecogMapper recogMapper;
	
	@Autowired
	MedicalService medicalService;
	
	@Autowired
	SysOrganizationService sysOrganizationService;
	
	@Override
	public PageInfo<?> getRecogDtoList(RecogExtendDto recogExtendDto,
			Integer pageNo, Integer pageSize) {
			
			Map<String,Object> params = new HashMap<>();
	        params.put("pageNo",pageNo);
	        params.put("pageSize", pageSize);
	        params.put("keyword",recogExtendDto.getKeyword());
	        params.put("recogResult",recogExtendDto.getRecogResult());//认证状态或者认证结果
	        params.put("busiType", recogExtendDto.getBusiType());//参保险种
	        params.put("personFrom", recogExtendDto.getPersonFrom());//参保地点
	        params.put("departId", recogExtendDto.getDepartId());//科室ID
	        params.put("departName", recogExtendDto.getDepartName());//科室名称
	        params.put("startTime", recogExtendDto.getStartTime());//认证开始时间
	        params.put("endTime", recogExtendDto.getEndTime());//认证结束时间
	        params.put("userOrgId", recogExtendDto.getUserOrgId());//登录用户组织机构Id
	        params.put("medicalId", recogExtendDto.getMedicalId());//住院id

	        PageHelper.startPage(pageNo, pageSize);
	        
	        /*
	        List<RecogExtendDto> recogList = new ArrayList<RecogExtendDto>();
	        if(recogExtendDto.getRecogResult() == null){//查询全部
	        	recogList = getAllRecogByParam(params); 
	        }else if(recogExtendDto.getRecogResult() != null && recogExtendDto.getRecogResult().longValue() == 10){//仅查询未认证的
	        	recogList = getNoRecogByParam(params);
	        }else if(recogExtendDto.getRecogResult() != null && recogExtendDto.getRecogResult().longValue() == 11){//仅查询成功的
	        	recogList = getSusRecogByParam(params);
	        }else if(recogExtendDto.getRecogResult() != null && recogExtendDto.getRecogResult().longValue() == 12){//仅查询失败的
	        	recogList = getFailRecogByParam(params);
	        }*/
	        List<RecogExtendDto> recogList = recogMapper.getallRecogDtoByParam(params);
	        PageInfo page = new PageInfo(recogList);
	        return page;
	}

	
	
	@Override
	public PageInfo<?> getRecogStatistics(RecogExtendDto recogExtendDto,
			Integer pageNo, Integer pageSize) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("keyword",recogExtendDto.getKeyword());
        params.put("monthKey",recogExtendDto.getMonthKey());//月份查询关键字
        params.put("departId", recogExtendDto.getDepartId());//科室ID
        params.put("departName", recogExtendDto.getDepartName());//科室名称
        params.put("startTime", recogExtendDto.getStartTime());//认证开始时间
        params.put("endTime", recogExtendDto.getEndTime());//认证结束时间
        params.put("userOrgId", recogExtendDto.getUserOrgId());//登录用户组织机构Id
        params.put("hospitalId", recogExtendDto.getHospitalId());//医院ID
        
        PageHelper.startPage(pageNo, pageSize);
        List<RecogExtendDto> backList = recogMapper.getRecogDtoStatistics(params);
        PageInfo page = new PageInfo(backList);
        return page;
        /*
        List<RecogExtendDto> recogList = new ArrayList<RecogExtendDto>();
        List<RecogExtendDto> backList = new ArrayList<RecogExtendDto>();
        //查询全部认证记录
        recogList = getAllRecogByParam(params); 
        
        List<SysOrganizationDto> departList = new ArrayList<SysOrganizationDto>();
        Map<String, List<RecogExtendDto>> groupMap = new HashMap<String, List<RecogExtendDto>>();
        if(!recogList.isEmpty()){//科室分组
        	SysOrganizationDto sysOrgDto = new SysOrganizationDto();
			sysOrgDto.setOrgCategory(2);
			departList = sysOrganizationService.selectList(sysOrgDto);
			if(!departList.isEmpty()){
				
				for(SysOrganizationDto sysOrg : departList){//循环分组
					String departId = String.valueOf(sysOrg.getOrgId());
					String departName = sysOrg.getName();
					List<RecogExtendDto> childList = new ArrayList<RecogExtendDto>();
					for(RecogExtendDto recogDto : recogList ){
						String orgId = String.valueOf(recogDto.getOrgId());
						if(departId.equals(orgId)){
							recogDto.setDepartName(departName);
							childList.add(recogDto);
						}
					}
					if(childList.size() > 0){
						groupMap.put(departId, childList);
					}
				}
				
				if(groupMap.size() > 0){
					
					for(Map.Entry<String, List<RecogExtendDto>> entry : groupMap.entrySet()){
						
						RecogExtendDto tempDto = new  RecogExtendDto();
						tempDto.setDepartName(entry.getValue().get(0).getDepartName());
						int allTotal = entry.getValue().size();
						int susTotal = 0;
						int faiTotal = 0;
						int norTotal = 0;
						for(RecogExtendDto tempRecog :  entry.getValue()){
							if(tempRecog.getRecogResult().longValue()  == 10 ){//认证状态（10、未认证；11、认证成功；12、认证失败）
								norTotal ++;
							}else if(tempRecog.getRecogResult().longValue()  == 11){
								susTotal ++;
							}else if(tempRecog.getRecogResult().longValue()  == 12){
								faiTotal ++;
							}
						}
						
						tempDto.setPersonTotal(allTotal);
						tempDto.setRecogSusTotal(susTotal);
						tempDto.setRecogFaiTotal(faiTotal);
						tempDto.setNoRecogTotal(norTotal);
						float susRate = 0f;
						if(susTotal >0){
							susRate = ((float)susTotal/(float)allTotal)*100;
						}
						tempDto.setSusRate(susRate);
						backList.add(tempDto);
					}
				}
				
			}
        }
        */
       
        
	}



	@Override
	public List<RecogExtendDto> getRecogStatistics(RecogExtendDto recogExtendDto) {
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("keyword",recogExtendDto.getKeyword());
        params.put("monthKey",recogExtendDto.getMonthKey());//月份查询关键字
        params.put("departId", recogExtendDto.getDepartId());//科室ID
        params.put("departName", recogExtendDto.getDepartName());//科室名称
        params.put("startTime", recogExtendDto.getStartTime());//认证开始时间
        params.put("endTime", recogExtendDto.getEndTime());//认证结束时间
        params.put("userOrgId", recogExtendDto.getUserOrgId());//登录用户组织机构Id
        params.put("hospitalId", recogExtendDto.getHospitalId());//医院ID
        List<RecogExtendDto> list = recogMapper.getRecogDtoStatistics(params);
        return list;
	}

	
	public List<RecogExtendDto> getAllRecogByParam(Map<String,Object> params){
		//查询条件应该返回的list
    	List<RecogExtendDto> backList = new ArrayList<RecogExtendDto>();
    	
    	try {
    		//所有的当前在院人员
            List<RecogExtendDto> allRecogList = recogMapper.getallRecogDtoList(params);
            //特殊建模认证记录，默认成功
            List<RecogExtendDto> specialList = recogMapper.getSpecialPersonCreateModel(params);
            //特殊人员登记认证记录，默认成功
            List<RecogExtendDto> registerList = recogMapper.getSpecialPersonRegister(params);
            
            Map<String, RecogExtendDto> map = new HashMap<String, RecogExtendDto>();
            
            if(!allRecogList.isEmpty()){
            	//1、循环判断是否有认证状态成功的认证记录
            	for(RecogExtendDto recogDto : allRecogList){
            		
            		Long reocogId = recogDto.getRecogId();
            		if(reocogId != null){
            			long recogStatus = recogDto.getRecogResult().longValue();
    	        		if( recogStatus == 11){
    	        			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
    	        			if(!map.containsKey(medicalIdStr)){
    	        				recogDto.setRecogEvent(10);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
    	        				map.put(medicalIdStr, recogDto);
    	        			}
    	        		}
            		}
            		
            	}
            	
            	//2、循环判断是否有认证状态失败的认证记录，前提条件是没有认证成功的认证记录
            	for(RecogExtendDto recogDto : allRecogList){
            		Long reocogId = recogDto.getRecogId();
            		if(reocogId != null){
            			long recogStatus = recogDto.getRecogResult().longValue();
    	        		if( recogStatus == 12){
    	        			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
    	        			if(!map.containsKey(medicalIdStr)){
    	        				recogDto.setRecogEvent(10);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
    	        				map.put(medicalIdStr, recogDto);
    	        			}
    	        			
    	        		}
            		}
            	}
            }
            //3、循环判断是否有特殊建模人员认证记录存在
            if(!specialList.isEmpty()){
            	for(RecogExtendDto recog : specialList){
            		String medicalIdStr = String.valueOf(recog.getMedicalId().longValue());
            		if(!map.containsKey(medicalIdStr)){
            			recog.setRecogResult(11L);
            			recog.setRecogEvent(12);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
            			map.put(medicalIdStr, recog);
        			}
            	}
            }
            
            //4、循环判断是否有特殊人员登记认证记录存在 
            if(!registerList.isEmpty()){
            	for(RecogExtendDto recog : registerList){
            		String medicalIdStr = String.valueOf(recog.getMedicalId().longValue());
            		if(!map.containsKey(medicalIdStr)){
            			recog.setRecogResult(11L);
            			recog.setRecogEvent(11);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
            			map.put(medicalIdStr, recog);
        			}
            	}
            }
            
            //5、循环判断是否有未认证状态的认证记录
            if(!allRecogList.isEmpty()){
            	for(RecogExtendDto recogDto : allRecogList){
            		Long reocogId = recogDto.getRecogId();
            		if(reocogId == null){
            			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
            			if(!map.containsKey(medicalIdStr)){
            				recogDto.setRecogResult(10L);
            				recogDto.setRecogEvent(13);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
            				map.put(medicalIdStr, recogDto);
            			}
            		}
            		
            	}
            }
        	
        	//经过上面五次循环，可过滤到每个在院人员入院认证时最近的一条认证记录
        	for(Map.Entry<String, RecogExtendDto> entry : map.entrySet()){
        			backList.add(entry.getValue());
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return backList;
	}
	
	public List<RecogExtendDto> getNoRecogByParam(Map<String, Object> params){
		
		//查询条件应该返回的list
    	List<RecogExtendDto> backList = new ArrayList<RecogExtendDto>();
    	
    	try {
    		//所有的当前在院人员
            List<RecogExtendDto> allRecogList = recogMapper.getallRecogDtoList(params);
            //特殊建模认证记录，默认成功
            List<RecogExtendDto> specialList = recogMapper.getSpecialPersonCreateModel(params);
            //特殊人员登记认证记录，默认成功
            List<RecogExtendDto> registerList = recogMapper.getSpecialPersonRegister(params);
            
            Map<String, RecogExtendDto> map = new HashMap<String, RecogExtendDto>();
            
            if(!allRecogList.isEmpty()){
            	//1、循环判断是否有认证状态成功的认证记录
            	for(RecogExtendDto recogDto : allRecogList){
            		
            		Long reocogId = recogDto.getRecogId();
            		if(reocogId != null){
            			long recogStatus = recogDto.getRecogResult().longValue();
    	        		if( recogStatus == 11){
    	        			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
    	        			if(!map.containsKey(medicalIdStr)){
    	        				recogDto.setRecogEvent(10);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
    	        				map.put(medicalIdStr, recogDto);
    	        			}
    	        		}
            		}
            		
            	}
            	
            	//2、循环判断是否有认证状态失败的认证记录，前提条件是没有认证成功的认证记录
            	for(RecogExtendDto recogDto : allRecogList){
            		Long reocogId = recogDto.getRecogId();
            		if(reocogId != null){
            			long recogStatus = recogDto.getRecogResult().longValue();
    	        		if( recogStatus == 12){
    	        			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
    	        			if(!map.containsKey(medicalIdStr)){
    	        				recogDto.setRecogEvent(10);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
    	        				map.put(medicalIdStr, recogDto);
    	        			}
    	        			
    	        		}
            		}
            	}
            }
            //3、循环判断是否有特殊建模人员认证记录存在
            if(!specialList.isEmpty()){
            	for(RecogExtendDto recog : specialList){
            		String medicalIdStr = String.valueOf(recog.getMedicalId().longValue());
            		if(!map.containsKey(medicalIdStr)){
            			recog.setRecogResult(11L);
            			recog.setRecogEvent(12);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
            			map.put(medicalIdStr, recog);
        			}
            	}
            }
            
            //4、循环判断是否有特殊人员登记认证记录存在 
            if(!registerList.isEmpty()){
            	for(RecogExtendDto recog : registerList){
            		String medicalIdStr = String.valueOf(recog.getMedicalId().longValue());
            		if(!map.containsKey(medicalIdStr)){
            			recog.setRecogResult(11L);
            			recog.setRecogEvent(11);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
            			map.put(medicalIdStr, recog);
        			}
            	}
            }
            
            Map<String, RecogExtendDto> nomap = new HashMap<String, RecogExtendDto>();
            //5、循环判断是否有未认证状态的认证记录
            if(!allRecogList.isEmpty()){
            	for(RecogExtendDto recogDto : allRecogList){
            		Long reocogId = recogDto.getRecogId();
            		if(reocogId == null){
            			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
            			if(!map.containsKey(medicalIdStr)){
            				if(!map.containsKey(medicalIdStr)){
            					recogDto.setRecogResult(10L);
            					recogDto.setRecogEvent(13);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
            					nomap.put(medicalIdStr, recogDto);
            				}
            			}
            		}
            		
            	}
            }
        	
            if(!nomap.isEmpty()){
            	//经过上面五次循环，可过滤到每个在院人员入院认证时最近的一条认证记录
            	for(Map.Entry<String, RecogExtendDto> entry : nomap.entrySet()){
            		backList.add(entry.getValue());
            	}
            }
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return backList;
	}

	public List<RecogExtendDto> getSusRecogByParam(Map<String, Object> params){
		
		//查询条件应该返回的list
    	List<RecogExtendDto> backList = new ArrayList<RecogExtendDto>();
    	
    	try {
    		//所有的当前在院人员
            List<RecogExtendDto> allRecogList = recogMapper.getallRecogDtoList(params);
            //特殊建模认证记录，默认成功
            List<RecogExtendDto> specialList = recogMapper.getSpecialPersonCreateModel(params);
            //特殊人员登记认证记录，默认成功
            List<RecogExtendDto> registerList = recogMapper.getSpecialPersonRegister(params);
            
            Map<String, RecogExtendDto> map = new HashMap<String, RecogExtendDto>();
            
            if(!allRecogList.isEmpty()){
            	//1、循环判断是否有认证状态成功的认证记录
            	for(RecogExtendDto recogDto : allRecogList){
            		
            		Long reocogId = recogDto.getRecogId();
            		if(reocogId != null){
            			long recogStatus = recogDto.getRecogResult().longValue();
    	        		if( recogStatus == 11){
    	        			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
    	        			if(!map.containsKey(medicalIdStr)){
    	        				recogDto.setRecogEvent(10);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
    	        				map.put(medicalIdStr, recogDto);
    	        			}
    	        		}
            		}
            		
            	}
            	
            }
            //3、循环判断是否有特殊建模人员认证记录存在
            if(!specialList.isEmpty()){
            	for(RecogExtendDto recog : specialList){
            		String medicalIdStr = String.valueOf(recog.getMedicalId().longValue());
            		if(!map.containsKey(medicalIdStr)){
            			recog.setRecogResult(11L);
            			recog.setRecogEvent(12);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
            			map.put(medicalIdStr, recog);
        			}
            	}
            }
            
            //4、循环判断是否有特殊人员登记认证记录存在 
            if(!registerList.isEmpty()){
            	for(RecogExtendDto recog : registerList){
            		String medicalIdStr = String.valueOf(recog.getMedicalId().longValue());
            		if(!map.containsKey(medicalIdStr)){
            			recog.setRecogResult(11L);
            			recog.setRecogEvent(11);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
            			map.put(medicalIdStr, recog);
        			}
            	}
            }
            
        	
            if(!map.isEmpty()){
            	//经过上面五次循环，可过滤到每个在院人员入院认证时最近的一条认证记录
            	for(Map.Entry<String, RecogExtendDto> entry : map.entrySet()){
            		backList.add(entry.getValue());
            	}
            }
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return backList;
	}


	public List<RecogExtendDto> getFailRecogByParam(Map<String, Object> params){
		
		//查询条件应该返回的list
		List<RecogExtendDto> backList = new ArrayList<RecogExtendDto>();
		
		try {
			//所有的当前在院人员
	        List<RecogExtendDto> allRecogList = recogMapper.getallRecogDtoList(params);
	        Map<String, RecogExtendDto> map = new HashMap<String, RecogExtendDto>();
	        
	        if(!allRecogList.isEmpty()){
	        	//1、循环判断是否有认证状态成功的认证记录
	        	for(RecogExtendDto recogDto : allRecogList){
	        		
	        		Long reocogId = recogDto.getRecogId();
	        		if(reocogId != null){
	        			long recogStatus = recogDto.getRecogResult().longValue();
		        		if( recogStatus == 11){
		        			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
		        			if(!map.containsKey(medicalIdStr)){
		        				recogDto.setRecogEvent(10);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
		        				map.put(medicalIdStr, recogDto);
		        			}
		        		}
	        		}
	        		
	        	}
	        	
	        	Map<String, RecogExtendDto> failmap = new HashMap<String, RecogExtendDto>();
	        	//2、循环判断是否有认证状态失败的认证记录，前提条件是没有认证成功的认证记录
	        	for(RecogExtendDto recogDto : allRecogList){
	        		Long reocogId = recogDto.getRecogId();
	        		if(reocogId != null){
	        			long recogStatus = recogDto.getRecogResult().longValue();
		        		if( recogStatus == 12){
		        			String medicalIdStr = String.valueOf(recogDto.getMedicalId().longValue());
		        			if(!map.containsKey(medicalIdStr)){
		        				if(!failmap.containsKey(medicalIdStr)){
		        					recogDto.setRecogEvent(10);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
		        					failmap.put(medicalIdStr, recogDto);
		        				}
		        			}
		        			
		        		}
	        		}
	        	}
	        	
	        	if(!failmap.isEmpty()){
		        	//经过上面五次循环，可过滤到每个在院人员入院认证时最近的一条认证记录
		        	for(Map.Entry<String, RecogExtendDto> entry : failmap.entrySet()){
		        		backList.add(entry.getValue());
		        	}
		        }
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return backList;
	}



	@Override
	public List<RecogExtendDto> getImgUrl(RecogExtendDto recogExtendDto) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("medicalId",recogExtendDto.getMedicalId());
        params.put("recogId",recogExtendDto.getRecogId());
        params.put("recogEvent", recogExtendDto.getRecogEvent());
        List<RecogExtendDto> list = recogMapper.getImgUrl(params);
        return list;
	}



	@Override
	public Long recognize(RecogDto bean) throws Exception {
		RecogDto newbean = this.insertAndGet(bean);
		return newbean.getRecogId();
	}



}
