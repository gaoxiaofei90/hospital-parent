package com.llw.hospital.bs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.llw.hospital.common.util.StringUtils;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.dto.RecogDto;
import com.llw.hospital.dto.RecogExtendDto;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.dto.SysOrganizationInhospitalDto;
import com.llw.hospital.util.DateUtils;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.hospital.api.RecogService;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.bs.util.LoginUtil;

import common.Logger;



/**
 * 认证记录
 *
 * @author gaoxiaofei
 * @email gaoxiaofei@a-eye.com
 * @date 2019-12-26 10:18:37
 */
@RestController
@RequestMapping("/medical/recog")
public class RecogController extends BaseController{
	
	private static Logger logger = Logger.getLogger(RecogController.class);
	
	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	
	@Autowired
	private RecogService recogService;
	
	
	
	
	/**
     * 列表
     */
    @RequestMapping("/list.jspx")
    public BaseResp list(RecogExtendDto recogExtendDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
//            PageInfo pageInfo = recogService.selectByPage(pageNo, pageSize, recogDto);
        	LoginUser user = LoginUtil.getCurrentLoginUser();
        	recogExtendDto.setUserOrgId(user.getOrgId());
            PageInfo pageInfo = recogService.getRecogDtoList(recogExtendDto, pageNo, pageSize);
            List<RecogExtendDto> list = pageInfo.getList();
        	List retList = new ArrayList();
        	if(null != list && list.size() > 0){
        		for (RecogExtendDto recogDto : list) {
        			String recogStr = recogDto.getRecogStr();
        			if(StringUtils.isEmpty(recogStr)){//未认证
        				recogDto.setRecogResult(10L);//未认证
        				recogDto.setRecogEvent(10);//认证类型（10、人脸核查；11、特殊登记；12、特殊建模;)
        			}else{
        				String[] recogArray = recogStr.split(",");
        				if(recogArray.length >= 1){//说明有多条认证记录或者一条认证记录
        					//仅仅取认证时间最近的一条认证记录
        					String[] paramArray = recogArray[0].split(";");
        					if(paramArray.length ==3){
        						recogDto.setRecogId(Long.valueOf(paramArray[0]));
            					recogDto.setRecogEvent(Integer.valueOf(paramArray[1]));
            					recogDto.setRecogResult(Long.valueOf(paramArray[2]));
        					}
        				}
        			}
        			Map map = BeanUtils.describe(recogDto);
         			map.put("inDate", DateUtils.date2String(recogDto.getInDate()));
         			retList.add(map);
        		}
        		pageInfo.setList(retList);
        	}
            return BaseResp.success(pageInfo);
        } catch (Exception ex) {
            logger.error("", ex);
            return BaseResp.error(ex);
        }
    }

	/**
     * 医院端---根据登录用户返回用户所在医院名称/科室名称
     * @return
     */
    @RequestMapping("/loginUser.jspx")
    public BaseResp LoginUser() {
    	 try {
    		
    		LoginUser user = LoginUtil.getCurrentLoginUser();
    		//用户所属的机构类型 权限过滤时需要用到
    		int orgCategory = 0;
    		if(null != user.getOrgCategory()){
    			orgCategory  = user.getOrgCategory().intValue();
    		}
    		Long orgId  = user.getOrgId();
    		
    		SysOrganizationDto sysOrgDto = new SysOrganizationDto();
			sysOrgDto.setOrgId(orgId);
			sysOrgDto = sysOrganizationService.selectOne(sysOrgDto);
			
			SysOrganizationInhospitalDto sysOrgHospitalDto = new SysOrganizationInhospitalDto();
			sysOrgHospitalDto.setOrgCategory(user.getOrgCategory());
			sysOrgHospitalDto.setOrgId(orgId);
			sysOrgHospitalDto.setName(sysOrgDto.getName());
			
    		if(orgCategory == 1){//1医院
    			
    			//获取到医院下的所有科室集合
    			LLwExample example = new LLwExample();
    			example.andEqualTo("pid", orgId);
    			List<SysOrganizationDto> list = sysOrganizationService.selectByExample(example);
    			sysOrgHospitalDto.setList(list);
    			
    		}else if (orgCategory == 2){//2医院>科室
    			
    			Long orgIdPid = sysOrgDto.getPid();//医院ID 或者 父机构ID
    			SysOrganizationDto sysOrgDto1 = new SysOrganizationDto();
    			sysOrgDto1.setOrgId(orgIdPid);
    			sysOrgDto1 = sysOrganizationService.selectOne(sysOrgDto1);//根据医院ID查询所在的医院实体
    			sysOrgHospitalDto.setPid(orgIdPid);//医院ID
    			sysOrgHospitalDto.setPidName(sysOrgDto1.getName());// 医院名称
    			//返回医院ID + 医院名称 + 科室ID + 科室名称
    		}
    		
            return BaseResp.success(sysOrgHospitalDto);
            
         } catch (Exception ex) {
             logger.error("", ex);
             return BaseResp.error(ex);
         }
    	 
    }

    
    /**
     * 查询科室列表集合
     * @return
     */
    @RequestMapping("/getDepartMentList.jspx")
    public BaseResp getDepartMentList() {
    	
    	try {
    		
    		SysOrganizationDto sysOrgDto = new SysOrganizationDto();
			sysOrgDto.setOrgCategory(2);//查询科室集合
			List<SysOrganizationDto> list = sysOrganizationService.selectList(sysOrgDto);
            return BaseResp.success(list);
            
         } catch (Exception ex) {
             logger.error("", ex);
             return BaseResp.error(ex);
         }
    	
    }
    
    @RequestMapping("/getImgUrl.jspx")
    public BaseResp getImgUrl(Long medicalId, Long recogId, Integer recogEvent){
		try {
		    		
				RecogExtendDto recogDto = new RecogExtendDto();
				recogDto.setMedicalId(medicalId);
				recogDto.setRecogId(recogId);
				recogDto.setRecogEvent(recogEvent);
				List<RecogExtendDto> list = recogService.getImgUrl(recogDto);
				List<Map<Object, Object>> retList = new ArrayList<Map<Object, Object>>();
				if(null != list && list.size() > 0){
	        		for (RecogExtendDto recog : list) {
	        			Map map = BeanUtils.describe(recog);
	         			map.put("recogTime", DateUtils.date2String(recog.getRecogTime()));
	         			retList.add(map);
	        		}
	        		return BaseResp.success(retList);
	        	}
	            return BaseResp.success(list);
	         } catch (Exception ex) {
	             logger.error("", ex);
	             return BaseResp.error(ex);
	         }
    }

}
