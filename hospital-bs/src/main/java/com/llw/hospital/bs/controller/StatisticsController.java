package com.llw.hospital.bs.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.llw.hospital.api.RecogService;
import com.llw.hospital.bs.util.LoginUtil;
import com.llw.hospital.bs.util.POIUtil;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.dto.RecogExtendDto;
import com.llw.hospital.util.DateUtils;

import common.Logger;




/**
 * 认证统计记录
 *
 * @author gaoxiaofei
 * @email gaoxiaofei@a-eye.com
 * @date 2019-12-26 10:18:37
 */
@RestController
@RequestMapping("/medical/statistics")
public class StatisticsController extends BaseController{

	private static Logger logger = Logger.getLogger(StatisticsController.class);
	
	@Autowired
	RecogService recogService;
	
	/**
     * 列表
     */
    @RequestMapping("/list.jspx")
    public BaseResp list(RecogExtendDto recogExtendDto,
                         @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
    	 try {

	    		 LoginUser user = LoginUtil.getCurrentLoginUser();
	             recogExtendDto.setUserOrgId(user.getOrgId());
	             PageInfo pageInfo = recogService.getRecogStatistics(recogExtendDto, pageNo, pageSize);
	             List<RecogExtendDto> list = pageInfo.getList();
	         	List retList = new ArrayList();
	         	if(null != list && list.size() > 0){
	         		for (RecogExtendDto recogDto : list) {
	         			if(recogDto.getPersonTotal() > 0){
             				float susRate = ((float)recogDto.getRecogSusTotal()/ (float)recogDto.getPersonTotal()) * 100;
             				recogDto.setSusRate(susRate);
             			}else{
             				recogDto.setSusRate(0.0f);
             			}
	         			
	         			retList.add(recogDto);
	         		}
	         	}
	             return BaseResp.success(pageInfo);
	       } catch (Exception ex) {
	           logger.error("", ex);
	           return BaseResp.error(ex);
	       }
    }
    
    
    /**
     *  医院端--在院人员---在院人员导出
     */
    @RequestMapping("/exportRecogPerson.jspx")
    //@RequiresPermissions("medical:person:list")
    public void  exportRecogPerson(HttpServletResponse response ,
						    		@RequestParam(value="departName", required=false)     String    departName,
									@RequestParam(value="monthKey", required=false)        String    monthKey,//月份关键字查询
									@RequestParam(value="startTime", required=false) String    startTime,
									@RequestParam(value="endTime", required=false)   String    endTime
									
						    	  ) {
    	
    	
    	LoginUser user = LoginUtil.getCurrentLoginUser();
		RecogExtendDto recogDto = new RecogExtendDto();
		recogDto.setUserOrgId(user.getOrgId());
		recogDto.setDepartName(departName);
		recogDto.setMonthKey(monthKey);
		recogDto.setStartTime(startTime);
		recogDto.setEndTime(endTime);
		
    	String fileName = "nanYangRecogStatistics.xls";
    	OutputStream outputStream = null;
    	
    	
        try {
        	
        	List<String []> recogList = new ArrayList<String []>();
        	List<RecogExtendDto> list = new ArrayList<RecogExtendDto>();
        	//PageInfo pageInfo = recogService.getRecogStatistics(recogDto, 1, 10);
        	PageInfo pageInfo = recogService.getRecogStatistics(recogDto, 1, 10);
        	list =  pageInfo.getList();
        	if(null != list && list.size() > 0){
        		for (int i=0; i < list.size(); i++) {
        			String [] InhosPersonTmp = new String[10];
        			RecogExtendDto recogExtendDto = list.get(i);
        			try {
        				
             			InhosPersonTmp[0] = recogExtendDto.getDepartName();
             			InhosPersonTmp[1] = String.valueOf(recogExtendDto.getPersonTotal());
             			InhosPersonTmp[2] = String.valueOf(recogExtendDto.getRecogSusTotal());
             			InhosPersonTmp[3] = String.valueOf(recogExtendDto.getRecogFaiTotal());
             			InhosPersonTmp[4] = String.valueOf(recogExtendDto.getNoRecogTotal());
             			
             			if(recogExtendDto.getPersonTotal() > 0){
             				float susRate = ((float)recogExtendDto.getRecogSusTotal()/ (float)recogExtendDto.getPersonTotal())* 100;
             				recogExtendDto.setSusRate(susRate);
             			}else{
             				recogExtendDto.setSusRate(0.0f);
             			}
             			InhosPersonTmp[5] = String.valueOf(recogExtendDto.getSusRate());
             			recogList.add(InhosPersonTmp);
					} catch (Exception e) {
						e.printStackTrace();
					}
        			
        			
        		}
        	}
        	
        	
        	//sheet名
            String   sheet = "就诊人员";
            String[] title = {"科室", "入科人次", "认证成功人次",  "认证失败人次",  "未认证人次", "完成率(单位：%)"};
            //创建HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook(); //创建工作簿
            POIUtil poiUtil = new POIUtil();
            poiUtil.createRecogPersonExcel(sheet, title, recogList, workbook);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(),"UTF-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            try {
                if(outputStream != null){
                    outputStream.flush();  
                    outputStream.close();  
                }                
            } catch (IOException e) {
                e.printStackTrace();
            }  
        }
    }
  
	
}
