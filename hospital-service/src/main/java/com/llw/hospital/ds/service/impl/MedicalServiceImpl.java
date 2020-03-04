package com.llw.hospital.ds.service.impl;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.jcl.dto.IdcardUtils;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.cache.broadcast.BroadcastUtil;
import com.llw.hospital.api.MedicalService;
import com.llw.hospital.api.PersonService;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.ds.entity.Medical;
import com.llw.hospital.ds.mapper.MedicalMapper;
import com.llw.hospital.ds.mapper.PersonDao;
import com.llw.hospital.ds.mapper.PersonFeaturesDao;
import com.llw.hospital.dto.MedicalDto;
import com.llw.hospital.dto.MedicalExtendDto;
import com.llw.hospital.dto.PersonDto;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.event.ZhuyuanEvent;
import com.llw.hospital.util.DateUtils;
import com.llw.module.network.ftp.FtpUtil;

import common.Logger;


@Component
@Service(timeout = 3000)
public class MedicalServiceImpl extends BaseDtoExtendServiceImpl<MedicalDto, Medical> implements MedicalService {

    @Autowired
    PersonFeaturesDao personFeaturesDao;
    @Autowired
	MedicalMapper mapper;
    @Autowired
	PersonDao personDao;
//    @Autowired
//    HospitalRelationService hospitalRelationService;
    @Autowired
    SysOrganizationService sysOrganizationService;
//    @Autowired
//    DeKc21Service deKc21Service;
    @Autowired
    PersonService personService;
//    @Autowired
//    OrgSysService orgSysService;
    @Autowired
    SysOrganizationService organizationService;
    
//    @Autowired
//    private BusinessSystemService businessSystemService;
    
    
    
    @SuppressWarnings("AlibabaThreadPoolCreation")
	private ExecutorService executor = Executors.newCachedThreadPool(new NamedThreadFactory("MedicalServiceImpl-executor", true));
    
    
    private static Logger logger = Logger.getLogger(MedicalServiceImpl.class);
    


	@Override
	public PageInfo getMedicalList(Integer pageNo, Integer pageSize, String keyword, Integer medicalType,Long[] personArr,Integer blackAndWhite,String label,String zoonCode) {
		Map<String,Object> params = new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("keyword",keyword);
        params.put("medicalType",medicalType);
        params.put("personArr",personArr);
        
        params.put("blackAndWhite",blackAndWhite);
        params.put("label",label);
        params.put("zoonCode",zoonCode);
        PageHelper.startPage(pageNo, pageSize);
        List<MedicalDto> list = mapper.getMedicalList(params);
        PageInfo page = new PageInfo(list);
        return page;
	}


	
	/** 
     * 利用用ascii码 来判断字符串是否为数字 
     */   
     public static boolean isNumeric(String str){
    	 
    	 if(StringUtils.isEmpty(str)){
    		 return false;
    	 }
    	 
    	 for (int i = str.length(); --i>=0;){
    		 if(!Character.isDigit(str.charAt(i))){
    			 return false;
    		 }
    	 }
    	 return true;
     }
	 

	


	@Override
	public Integer medicalCount(Long orgBeginId, Long orgEndId) {
		Map<String,Object> params = new HashMap<>();
        params.put("orgBeginId",orgBeginId);
        params.put("orgEndId", orgEndId);
        List<MedicalExtendDto> list = mapper.medicalCount(params);
        if(null != list && list.size() > 0){
        	MedicalExtendDto dto = list.get(0);
        	return dto.getMedicalCount();
        }else{
        	return 0;
        }
	}
	
	@Override
	public Long getPersonId(String name,String idCard) {
		Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        params.put("idCard", idCard);
        Long personId = mapper.getPersonId(params);
        return personId;
	}


	@Override
	public void update(Long medicalId) {
		mapper.update(medicalId);
	}

	@Override
	public PageInfo getInhospital(MedicalExtendDto dto, Integer pageNo,
			Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("keyword",dto.getKeyword());
        params.put("startTime",dto.getStartTime());
        params.put("endTime",dto.getEndTime());
        PageHelper.startPage(pageNo, pageSize);
        List<MedicalExtendDto> list = mapper.GetInhospital(params);
        PageInfo page = new PageInfo(list);
        return page;
	}


	
	@Override
	public PageInfo GetPersonbyStatus(MedicalExtendDto dto, Integer pageNo,
			Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("keyword",dto.getKeyword());
        params.put("startTime",dto.getStartTime());
        params.put("endTime",dto.getEndTime());
        params.put("hospitalId",dto.getHospitalId());
        PageHelper.startPage(pageNo, pageSize);
        List<MedicalExtendDto> list = mapper.GetPersonbyStatus(params);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public PageInfo getInhospitaPersonList(MedicalExtendDto dto,
			Integer pageNo, Integer pageSize) {
			
			Map<String,Object> params = new HashMap<>();
	        params.put("pageNo",pageNo);
	        params.put("pageSize", pageSize);
	        params.put("keyword",dto.getKeyword());
	        params.put("hospitalId",dto.getHospitalId());
	        params.put("departName", dto.getDepartName());
	        params.put("departId", dto.getDepartId());
	        params.put("personType", dto.getPersonType());
	        params.put("warningStatus", dto.getWarningStatus());
	        params.put("leaveStatus", dto.getLeaveStatus());
	        params.put("startInHosTime", dto.getStartTime());
	        params.put("endInHosTime", dto.getEndTime());
	        params.put("busiType", dto.getBusiType());
	        params.put("personFrom", dto.getPersonFrom());
	        params.put("userOrgId", dto.getUserOrgId());
	        PageHelper.startPage(pageNo, pageSize);
	        List<MedicalExtendDto> list = mapper.getPersonListByLoginUser(params);
	        PageInfo page = new PageInfo(list);
	        return page;
	}


	
	@Override
	public boolean registration(MedicalDto medicalDto) {
		int rows = insertSelective(medicalDto);
		ZhuyuanEvent zhuyuanEvent = new ZhuyuanEvent(this,medicalDto.getOrgId(),medicalDto.getPersonId());
		BroadcastUtil.boradCast(zhuyuanEvent);
		return rows > 0;
	}
	

	@Override
	public PageInfo getMedical1List(MedicalExtendDto dto, Integer pageNo, Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("keyword",dto.getKeyword());
        params.put("startTime",dto.getStartTime());
        params.put("endTime",dto.getEndTime());
        params.put("medicalType",dto.getMedicalType());
        params.put("recogBusi",dto.getRecogBusi());
        PageHelper.startPage(pageNo, pageSize);
        List<MedicalExtendDto> list = mapper.getMedical1List(params);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public PageInfo getMedicalIndexDetailList(MedicalExtendDto dto, Integer pageNo, Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("keyword",dto.getKeyword());
        params.put("startTime",dto.getStartTime());
        params.put("endTime",dto.getEndTime());
        params.put("hospitalId",dto.getHospitalId());
        params.put("personType",dto.getPersonType());
        params.put("recogBusi",dto.getRecogBusi());
        params.put("medicalType",dto.getMedicalType());
        params.put("orderType",dto.getOrderType());
        params.put("orderField",dto.getOrderField());
        PageHelper.startPage(pageNo, pageSize);
        List<MedicalExtendDto> list = mapper.getMedicalIndexDetailList(params);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public PageInfo getOutpatPharmacyDetailList(MedicalExtendDto dto, Integer pageNo, Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("keyword",dto.getKeyword());
        params.put("startTime",dto.getStartTime());
        params.put("endTime",dto.getEndTime());
        params.put("hospitalId",dto.getHospitalId());
        params.put("recogBusi",dto.getRecogBusi());
        params.put("medicalType",dto.getMedicalType());
        params.put("orderType",dto.getOrderType());
        params.put("orderField",dto.getOrderField());
        PageHelper.startPage(pageNo, pageSize);
        List<MedicalExtendDto> list = mapper.getOutpatPharmacyDetailList(params);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public PageInfo getOutHospitalPersonRecord(MedicalExtendDto dto, Integer pageNo, Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("pageSize", pageSize);
        params.put("keyword",dto.getKeyword());
        params.put("startTime",dto.getStartTime());
        params.put("endTime",dto.getEndTime());
        params.put("userOrgId",dto.getUserOrgId());
        params.put("departId",dto.getDepartId());
        params.put("status",dto.getStatus());
        PageHelper.startPage(pageNo, pageSize);
        List<MedicalExtendDto> list = mapper.getOutHospitalPersonRecord(params);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public void updateStatus(Long medicalId) {
		mapper.updateStatus(medicalId);
	}


	@Override
	public List<MedicalDto> getInHosPersonList(MedicalExtendDto dto) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hospitalId", dto.getHospitalId());
		map.put("departId", dto.getDepartId());
		map.put("departName", dto.getDepartName());
		map.put("personType", dto.getPersonType());
		map.put("warningStatus", dto.getWarningStatus());
		map.put("leaveStatus", dto.getLeaveStatus());
		map.put("startInHosTime", dto.getInDateStr());
		map.put("endInHosTime", dto.getOutDateStr());
		map.put("keyword", dto.getKeyword());
		map.put("busiType", dto.getBusiType());
		map.put("personFrom", dto.getPersonFrom());
		map.put("userOrgId", dto.getUserOrgId());
		List<MedicalDto> list = mapper.getInHosPersonList(map);
		return list;
	}


	@Override
	public String hospitalRegistration(MedicalDto dto,LoginUser loginUser) {
		String idcard = dto.getIdcard();
		String name = dto.getName();
		try {
		if(StringUtil.isEmpty(idcard) || StringUtil.isEmpty(name)) {
			//return ResponseParam.error("1001", "参数不正确");
			return "参数不正确";
		}
		
		PersonDto personInfo = null;
		if(StringUtil.isNotEmpty(idcard) && StringUtil.isNotEmpty(name)){
			PersonDto where = new PersonDto();
			where.setIdcard(dto.getIdcard());
			//where.setName(request.getName());
			personInfo = personService.selectOne(where);
		}
		
		/*String idcardPhoto = null;
		if (StringUtil.isNotEmpty(dto.getIdcardPhoto())) {
			idcardPhoto = FtpUtil.getInstance(BaseConstants.MODULE_NAME).upload(new ByteArrayInputStream(Base64.decode(dto.getIdcardPhoto())), "idcard", "jpg");
			if (StringUtil.isEmpty(idcardPhoto)) {
				return "服务器异常";
			}
		}*/
		
		//插入人员信息
		if(personInfo==null){
			
			String birthday = idcard.substring(6, 10) + "-" + idcard.substring(10, 12) + "-" + idcard.substring(12, 14);
			String sex = Integer.parseInt(idcard.substring(16, 17)) % 2 == 0 ? "2": "1";
			PersonDto personDto = new PersonDto();
			personDto.setName(name);
			personDto.setIdcard(idcard);
			personDto.setSex(sex);
			personDto.setPersonType(dto.getPersonType());
			personDto.setBirthday(DateUtils.string2Date(birthday));
			//personDto.setIdcardPhoto(idcardPhoto);
			//personDto.setHeadPhoto(idcardPhoto);
			Long personId = personService.insertPerson(personDto);
			if (personId < 1) {
				return "登记失败";
			}
			personInfo = personService.selectByPrimaryKey(personId);
		}else {
			//要进行更新 而且只会更新到名字
			PersonDto personDto = new PersonDto();
			personDto.setPersonId(personInfo.getPersonId());
			personDto.setName(name);
			personDto.setIdcard(idcard);
			personService.updateByPrimaryKeySelective(personDto);
		}
		
			MedicalDto medicalDto = new MedicalDto();
			medicalDto.setIdcard(idcard);
			medicalDto.setStatus(1);
			medicalDto.setMedicalType(1);
			
			List<MedicalDto> l = this.selectListInOrg(medicalDto);
			
			//判断是否已在就诊记录中
			boolean hasMedical = false;
			if(l.size() > 0) {
				hasMedical = true;
				medicalDto.setMedicalId(l.get(0).getMedicalId());
				medicalDto.setDataFrom(l.get(0).getDataFrom());
				medicalDto.setPersonType(l.get(0).getPersonType());
				medicalDto.setCreateTime(l.get(0).getCreateTime());
			}else {

				medicalDto.setDataFrom(1);
				medicalDto.setCreateTime(new Date());
			}

			//查询住院号是否重复
			MedicalDto m = new MedicalDto();
			m.setStatus(1);
			m.setMedicalNo(dto.getMedicalNo());
			m = this.selectOneInOrg(m);
			if(m != null) {
				if(hasMedical){
					if( !m.getMedicalId().equals(medicalDto.getMedicalId())){
						return "住院号已存在";
					}
				}else{
					return "住院号已存在";
				}
			}

			/*if(null != dto.getOrgId()){
				medicalDto.setDepartId(dto.getOrgId());
				SysOrganizationDto sysOrganizationDto = sysOrganizationService.selectByPrimaryKey(dto.getOrgId());
				if(null != sysOrganizationDto){
					medicalDto.setDepartName(sysOrganizationDto.getName());
				}
			}else{
				medicalDto.setDepartId(null);
				medicalDto.setDepartName(null);
			}*/
			//medicalDto.setDepartCode(departCode);//前端无法获取
			
			medicalDto.setPersonType(dto.getPersonType());
			medicalDto.setName(name);
			medicalDto.setPersonId(personInfo.getPersonId());
			medicalDto.setSex(personInfo.getSex());
			medicalDto.setAge(IdcardUtils.getAgeByIdCard(idcard));
			medicalDto.setMedicalType(1);
			medicalDto.setMedicalNo(dto.getMedicalNo());
			medicalDto.setBedNo(dto.getBedNo());
			medicalDto.setDiagnosis(dto.getDiagnosis());
			medicalDto.setRevokeFlag(0);
			//业务系统以及统筹由界面选择
			medicalDto.setBusiType(dto.getBusiType());
			medicalDto.setPersonFrom(dto.getPersonFrom());
			medicalDto.setUpdateTime(new Date());
			
			if(dto.getInDate() != null) {
				medicalDto.setInDate(dto.getInDate());
				medicalDto.setInDate0(dto.getInDate());
			}else {
				medicalDto.setInDate(new Date());
				medicalDto.setInDate0(new Date());
			}
			
			
			//获取syscode zonecode  busitype  住院登记 根据医保局所包含的业务系统以及业务类型填值
//			SysOrganizationDto ybOraganization = getYBJOrg(loginUser.getOrgId());
//			if(ybOraganization != null) {
//				 OrgSysDto orgSysDto = new OrgSysDto();
//			        orgSysDto.setOrgId(ybOraganization.getOrgId());
//			        List<OrgSysDto> list = orgSysService.selectList(orgSysDto);
//			        List<String> busiTypes = new ArrayList<>();
//			        List<Integer> sysCodes = new ArrayList<>();
//			        if(!CollectionUtils.isEmpty(list)){
//			            list.forEach(item->{
//			                if(null != item.getSysCode()){
//			                    sysCodes.add(item.getSysCode());
//			                }
//			                if(!StringUtils.isEmpty(item.getBusiTypes())){
//			                    String[] busiTypeStr = item.getBusiTypes().split(",");
//			                    for(String type:busiTypeStr){
//			                        busiTypes.add(type);
//			                    }
//			                }
//			            });
//			            
//			            if(!CollectionUtils.isEmpty(sysCodes)) {
//			            	medicalDto.setSysCode(sysCodes.get(0));
//			            }
//			            
//			            if(StringUtils.isBlank(medicalDto.getBusiType()) && !CollectionUtils.isEmpty(busiTypes)) {
//			            	medicalDto.setBusiType(busiTypes.get(0));
//			            }
//			            
//			        }
//			        medicalDto.setZoneCode(ybOraganization.getZoneCode());
//			}
			
			//获取医院信息
			Long orgId = getOrgId(loginUser.getOrgId());
			if(orgId == null) {
				return "登录账号未绑定机构类型";
			}
			
//			HospitalRelationDto hr = new HospitalRelationDto();
//			hr.setOrgId(orgId);
//			hr = hospitalRelationService.selectOne(hr);
//			medicalDto.setHospitalId(hr.getHospitalId());
//			medicalDto.setHospitalName(hr.getHospitalName());
//			medicalDto.setHospitalCode(hr.getHospitalCode());
//			medicalDto.setOrgId(orgId);
			//科室非必填
			if(null != dto.getOrgId()){
				medicalDto.setOrgId(dto.getOrgId());
				medicalDto.setDepartId(dto.getOrgId());
				SysOrganizationDto sysOrganizationDto = sysOrganizationService.selectByPrimaryKey(dto.getOrgId());
				if(null != sysOrganizationDto){
					medicalDto.setDepartName(sysOrganizationDto.getName());
				}
			}else{
				medicalDto.setDepartId(null);
				medicalDto.setDepartName(null);
			}
			/*if(dto.getDepartId() != null) {
				medicalDto.setOrgId(dto.getDepartId());
				SysOrganizationDto so = organizationService.selectByPrimaryKey(dto.getDepartId());
				if(so != null) {
					medicalDto.setDepartId(so.getOrgId());
					medicalDto.setDepartName(so.getName());
				}
			}else{
				medicalDto.setDepartId(null);
				medicalDto.setDepartName(null);
			}*/
			
			int rows = 1;
			if(hasMedical) {
				rows = this.updateByPrimaryKey(medicalDto);
			}else {
				rows = this.registration(medicalDto)?1:0;
			}
			
	        if (rows > 0) {
	        	return "success";
	        } else {
	        	return"登记失败";
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
			return "登记失败";
		}
	}
	
	public Long getOrgId(Long orgId) {
		SysOrganizationDto so = organizationService.selectByPrimaryKey(orgId);
		if(so.getOrgCategory() == 4) {
			Long id = so.getPid();
			return getOrgId(id);
		}else if(so.getOrgCategory() == 3) {
			return so.getOrgId();
		}else {
			return null;
		}
	}
	
	public SysOrganizationDto getYBJOrg(Long orgId) {
		SysOrganizationDto so = organizationService.selectByPrimaryKey(orgId);
		if(so.getOrgCategory() == 2) {
			return so;
		}else {
			return getYBJOrg(so.getPid());
		}
	}
}
