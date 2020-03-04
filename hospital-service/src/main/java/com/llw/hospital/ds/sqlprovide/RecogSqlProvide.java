package com.llw.hospital.ds.sqlprovide;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.llw.hospital.util.OrgUtil;

public class RecogSqlProvide {
	
	 public String getRecogDtoList(Map<String,Object> params) {
		 
		
		 String keyword 		= 	params.get("keyword") 			== null ? null :(String)params.get("keyword");
		 Long   recogResult 	= 	params.get("recogResult") 		== null ? null :(Long)params.get("recogResult");
		 String busiType 	    = 	params.get("busiType") 		    == null ? null : (String)params.get("busiType");
		 Integer personFrom     =   params.get("personFrom") 		== null ? null : (Integer)params.get("personFrom");
		 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
		 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
		 String startTime 		= 	params.get("startTime") 		== null ? null : (String)params.get("startTime");
		 String endTime 		= 	params.get("endTime") 			== null ? null : (String)params.get("endTime");
		 Long departId 			= 	params.get("departId") 			== null ? null :(Long)params.get("departId");
		 Long userOrgId 		= 	params.get("userOrgId")         == null ? null  :(Long)params.get("userOrgId");
		 
		 
		 /*
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append(" SELECT m.NAME, m.DEPART_NAME, m.BED_NO, m.MEDICAL_NO, m.PERSON_FROM, m.BUSI_TYPE, m.IN_DATE, m.RECOG_MODE, m.ORG_ID, ")
		 .append(" r.recog_id, r.recog_result ")
		 .append(" FROM T_MEDICAL m, ")
		 .append(" 		( ")
		 .append(" 			SELECT r1.recog_id, r1.medical_id, r1.recog_result ")
		 .append(" 			FROM ( SELECT recog_id, medical_id, recog_result from  t_recog  where recog_result = 11 ORDER BY recog_time DESC  LIMIT 1 ) as r1 ")
		 .append(" 		UNION  ")
		 .append(" 			SELECT r2.recog_id, r2.medical_id, r2.recog_result ")
		 .append(" 			FROM ( SELECT recog_id, medical_id, recog_result from  t_recog  where recog_result = 12 ORDER BY recog_time DESC  LIMIT 1 ) as r2  ")
		 .append(" 		UNION  ")
		 .append(" 			SELECT r3.recog_id, r3.medical_id, r3.recog_result  ")
		 .append(" 			FROM ( SELECT recog_id, medical_id, recog_result from  t_recog  where recog_result = 10 ORDER BY recog_time DESC  LIMIT 1 ) as r3  ")
		 .append(" 		) r  ")
		 .append("where r.person_id  = m.PERSON_ID ");
		 */
		 
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select m.NAME, m.MEDICAL_ID, 10 as recogEvent, m.MEDICAL_NO, m.DEPART_NAME, m.BED_NO, m.PERSON_FROM, m.BUSI_TYPE, m.IN_DATE, m.RECOG_MODE, m.person_id,m.org_id,  ")
		 .append(" r.recog_id, r.recog_result ")
		 .append(" from t_recog r, T_MEDICAL m ")
		 .append("where r.medical_id  = m.MEDICAL_ID and m.STATUS = 1 ");

		 if(userOrgId != null){
			 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
			 sqlBuf.append(" and m.org_id >= ")
	    	 .append(userOrgId);
			 sqlBuf.append(" and m.org_id <= ")
	    	 .append(endOrgId);
		 }
		 
		 if(keyword != null){
			 
			 if(departId != null){ //当登录用户是科室用户时，则关键字中不包括科室关键字查询
				 sqlBuf.append(" and ( m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }else{//当登录用户是医院用户时，关键字中可以包括科室关键字查询
				 sqlBuf.append(" and ( m.depart_name like '%")
				 .append(keyword)
				 .append("%' or m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }
			 
		 }
		 
//		 if(recogResult != null){
//			 sqlBuf.append(" and r.recog_result = " + recogResult );
//		 }
		 if(null != departName){
			 sqlBuf.append(" and m.depart_name = '" + departName +"'");
    	 }
		 if(null != personType){
			 sqlBuf.append(" and m.person_type = '" + personType + "'");
		 }
		
		 if(null != busiType){
			 sqlBuf.append(" and m.busi_type = '"+busiType +"'");
		 }
		 if(null != personFrom){
			 sqlBuf.append(" and m.person_from = "+personFrom);
		 }
		 
		 if(null != startTime){
			 startTime += " 00:00:00";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  >= '" + startTime +"'");
		 }
		 if(null != endTime){
			 endTime += " 59:59:59";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  <= '" + endTime + "'" );
		 }
		 
		 sqlBuf.append(" order by m.IN_DATE desc");
		 
		 System.out.println("sql---->"+sqlBuf.toString());
		 return sqlBuf.toString();
	 }

	public String getStatisticsList(Map<String, Object> params){
		
		
		 String keyword 		= 	params.get("keyword") 			== null ? null : (String)params.get("keyword");
		 Long   recogResult 	= 	params.get("recogResult") 		== null ? null : (Long)params.get("recogResult");
		 String busiType 	    = 	params.get("busiType") 		    == null ? null : (String)params.get("busiType");
		 Integer personFrom     =   params.get("personFrom") 		== null ? null : (Integer)params.get("personFrom");
		 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
		 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
		 String startTime 		= 	params.get("startTime") 		== null ? null : (String)params.get("startTime");
		 String endTime 		= 	params.get("endTime") 			== null ? null : (String)params.get("endTime");
		 Long departId 			= 	params.get("departId") 			== null ? null : (Long)params.get("departId");
		 Long userOrgId 		= 	params.get("userOrgId")         == null ? null : (Long)params.get("userOrgId");
		 String monthKey        =   params.get("monthKey") 			== null ? null : (String)params.get("monthKey");
		 
		 
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append(" select  m.depart_name, COUNT(*) AS personTotal, ")
		 .append(" 				 SUM( CASE m.RECOG_STATUS  WHEN 1 THEN 1 ELSE 0 END ) as recogSusTotal, ")
		 .append(" 				 SUM( CASE m.RECOG_STATUS  WHEN 2 THEN 1 ELSE 0 END) as recogFaiTotal, ")
		 .append(" 				 SUM( CASE m.RECOG_STATUS  WHEN 0 THEN 1 ELSE 0 END) as noRecogTotal ")
		 .append(" 		from T_MEDICAL m  where m.STATUS = 1 ");
//		 strBuffer.append(" select m.hospital_name, m.hospital_id, m.depart_name, m.depart_id, m.name, m.medical_no, m.bed_no, m.org_id, r.extra_param as temperatureStr, r.recog_time, r.device_code ")
//		 		  .append(" from T_MEDICAL m, t_recog r  ")
//		 		  .append(" where m.medical_id = r.medical_id and r.device_type = 'JXB309' ");
		 
		 if(userOrgId != null){
			 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
			 sqlBuf.append(" and m.org_id >= ")
	    	 .append(userOrgId);
			 sqlBuf.append(" and m.org_id <= ")
	    	 .append(endOrgId);
		 }
		 
		 if(keyword != null){
			 
			 if(departId != null){ //当登录用户是科室用户时，则关键字中不包括科室关键字查询
				 sqlBuf.append(" and ( m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }else{//当登录用户是医院用户时，关键字中可以包括科室关键字查询
				 sqlBuf.append(" and ( m.depart_name like '%")
				 .append(keyword)
				 .append("%' or m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }
			 
		 }
		 
		 if(null != departName){
			 sqlBuf.append(" and m.depart_name = '" + departName +"'");
		 }
		 if(null != personType){
			 sqlBuf.append(" and m.person_type = '" + personType + "'");
		 }
		
		 if(null != busiType){
			 sqlBuf.append(" and m.busi_type = '"+busiType +"'");
		 }
		 if(null != personFrom){
			 sqlBuf.append(" and m.person_from = "+personFrom);
		 }
		 
		 if(null != recogResult){
			 sqlBuf.append(" and m.recog_status = " + recogResult);
		 }
		 
		 if(null != startTime){
			 startTime += " 00:00:00";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  >= '" + startTime +"'");
		 }
		 if(null != endTime){
			 endTime += " 59:59:59";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  <= '" + endTime + "'" );
		 }
		 
		 if(null != monthKey){
			 
			 sqlBuf.append(" and month(m.in_date) in (" + monthKey + ")");
		 }
		 sqlBuf.append(" GROUP BY m.depart_name ");
		 
		 System.out.println("sql---->"+sqlBuf.toString());
		 return sqlBuf.toString();	 	
	}
	
	public String specialPersonCreateModelList(Map<String,Object> params){
		 
		 String keyword 		= 	params.get("keyword") 			== null ? null :(String)params.get("keyword");
		 Long   recogResult 	= 	params.get("recogResult") 		== null ? null :(Long)params.get("recogResult");
		 String busiType 	    = 	params.get("busiType") 		    == null ? null : (String)params.get("busiType");
		 Integer personFrom     =   params.get("personFrom") 		== null ? null : (Integer)params.get("personFrom");
		 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
		 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
		 String startTime 		= 	params.get("startTime") 		== null ? null : (String)params.get("startTime");
		 String endTime 		= 	params.get("endTime") 			== null ? null : (String)params.get("endTime");
		 Long departId 			= 	params.get("departId") 			== null ? null :(Long)params.get("departId");
		 Long userOrgId 		= 	params.get("userOrgId")         == null ? null  :(Long)params.get("userOrgId");
		 String monthKey        =   params.get("monthKey") 			== null ? null : (String)params.get("monthKey");
		 
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select m.NAME, m.MEDICAL_ID, 12 as recogEvent, m.MEDICAL_NO, m.DEPART_NAME, m.BED_NO, m.PERSON_FROM, m.BUSI_TYPE, m.IN_DATE, m.RECOG_MODE, m.person_id, m.org_id, ")
		 .append(" d.DATA_URL  ")
		 .append("from T_MEDICAL m, (select PERSON_ID,GROUP_CONCAT(DATA_URL) as data_url from T_PERSON_DATA GROUP by PERSON_ID) d  ")
		 .append("where  m.person_id = d.PERSON_ID  and m.STATUS = 1  ");

		 if(userOrgId != null){
			 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
			 sqlBuf.append(" and m.org_id >= ")
	    	 .append(userOrgId);
			 sqlBuf.append(" and m.org_id <= ")
	    	 .append(endOrgId);
		 }
		 
		 if(keyword != null){
			 
			 if(departId != null){ //当登录用户是科室用户时，则关键字中不包括科室关键字查询
				 sqlBuf.append(" and ( m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }else{//当登录用户是医院用户时，关键字中可以包括科室关键字查询
				 sqlBuf.append(" and ( m.depart_name like '%")
				 .append(keyword)
				 .append("%' or m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }
			 
		 }
		 
		 if(null != departName){
			 sqlBuf.append(" and m.depart_name = '" + departName +"'");
    	 }
		 if(null != personType){
			 sqlBuf.append(" and m.person_type = '" + personType + "'");
		 }
		
		 if(null != busiType){
			 sqlBuf.append(" and m.busi_type = '"+busiType +"'");
		 }
		 if(null != personFrom){
			 sqlBuf.append(" and m.person_from = "+personFrom);
		 }
		 
		 if(null != startTime){
			 startTime += " 00:00:00";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  >= '" + startTime +"'");
		 }
		 if(null != endTime){
			 endTime += " 59:59:59";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  <= '" + endTime + "'" );
		 }
		 if(null != monthKey){
			 
			 sqlBuf.append(" and month(m.in_date) in (" + monthKey + ")");
		 }
		 sqlBuf.append(" order by m.IN_DATE desc");
		 
		 System.out.println("sql---->"+sqlBuf.toString());
		 return sqlBuf.toString();
	}
	
	public String specialPersonRegisterList(Map<String,Object> params){
		 
		 String keyword 		= 	params.get("keyword") 			== null ? null :(String)params.get("keyword");
		 Long   recogResult 	= 	params.get("recogResult") 		== null ? null :(Long)params.get("recogResult");
		 String busiType 	    = 	params.get("busiType") 		    == null ? null : (String)params.get("busiType");
		 Integer personFrom     =   params.get("personFrom") 		== null ? null : (Integer)params.get("personFrom");
		 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
		 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
		 String startTime 		= 	params.get("startTime") 		== null ? null : (String)params.get("startTime");
		 String endTime 		= 	params.get("endTime") 			== null ? null : (String)params.get("endTime");
		 Long departId 			= 	params.get("departId") 			== null ? null :(Long)params.get("departId");
		 Long userOrgId 		= 	params.get("userOrgId")         == null ? null  :(Long)params.get("userOrgId");
		 String monthKey        =   params.get("monthKey") 			== null ? null : (String)params.get("monthKey");
		 
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select m.NAME, m.MEDICAL_ID, 11 as recogEvent, m.MEDICAL_NO, m.DEPART_NAME, m.BED_NO, m.PERSON_FROM, m.BUSI_TYPE, m.IN_DATE, m.RECOG_MODE, m.person_id, m.org_id,  ")
		 .append(" v.VIDEO_ID as recog_id, v.AUDIT_STATUS as recog_result,  v.recog_time  ")
		 .append("from T_MEDICAL m, t_video_recog v ")
		 .append("where  m.MEDICAL_ID = v.MEDICAL_ID  and m.STATUS = 1 ");

		 if(userOrgId != null){
			 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
			 sqlBuf.append(" and m.org_id >= ")
	    	 .append(userOrgId);
			 sqlBuf.append(" and m.org_id <= ")
	    	 .append(endOrgId);
		 }
		 
		 if(keyword != null){
			 
			 if(departId != null){ //当登录用户是科室用户时，则关键字中不包括科室关键字查询
				 sqlBuf.append(" and ( m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }else{//当登录用户是医院用户时，关键字中可以包括科室关键字查询
				 sqlBuf.append(" and ( m.depart_name like '%")
				 .append(keyword)
				 .append("%' or m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }
			 
		 }
		 
		 if(null != departName){
			 sqlBuf.append(" and m.depart_name = '" + departName +"'");
   	 }
		 if(null != personType){
			 sqlBuf.append(" and m.person_type = '" + personType + "'");
		 }
		
		 if(null != busiType){
			 sqlBuf.append(" and m.busi_type = '"+busiType +"'");
		 }
		 if(null != personFrom){
			 sqlBuf.append(" and m.person_from = "+personFrom);
		 }
		 
		 if(null != startTime){
			 startTime += " 00:00:00";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  >= '" + startTime +"'");
		 }
		 if(null != endTime){
			 endTime += " 59:59:59";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  <= '" + endTime + "'" );
		 }
		 if(null != monthKey){
			 
			 sqlBuf.append(" and month(m.in_date) in (" + monthKey + ")");
		 }
		 sqlBuf.append(" order by m.IN_DATE desc");
		 
		 System.out.println("sql---->"+sqlBuf.toString());
		 return sqlBuf.toString();
	}
	
	public String getallRecogDtoList(Map<String,Object> params){
		 
		 String keyword 		= 	params.get("keyword") 			== null ? null : (String)params.get("keyword");
		 Long   recogResult 	= 	params.get("recogResult") 		== null ? null : (Long)params.get("recogResult");
		 String busiType 	    = 	params.get("busiType") 		    == null ? null : (String)params.get("busiType");
		 Integer personFrom     =   params.get("personFrom") 		== null ? null : (Integer)params.get("personFrom");
		 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
		 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
		 String startTime 		= 	params.get("startTime") 		== null ? null : (String)params.get("startTime");
		 String endTime 		= 	params.get("endTime") 			== null ? null : (String)params.get("endTime");
		 Long departId 			= 	params.get("departId") 			== null ? null : (Long)params.get("departId");
		 Long userOrgId 		= 	params.get("userOrgId")         == null ? null : (Long)params.get("userOrgId");
		 String monthKey        =   params.get("monthKey") 			== null ? null : (String)params.get("monthKey");
		 
		 
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select m.NAME, m.MEDICAL_ID, 0 as recogEvent, m.MEDICAL_NO, m.DEPART_NAME, m.BED_NO, m.PERSON_FROM, m.BUSI_TYPE, m.IN_DATE, m.RECOG_MODE, m.person_id, m.org_id,  ")
		 .append(" r.recog_id, r.recog_result ")
		 .append(" from T_MEDICAL m left join t_recog r  on  m.MEDICAL_ID  = r.medical_id  ")
		 .append("where m.status = 1  ");

		 if(userOrgId != null){
			 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
			 sqlBuf.append(" and m.org_id >= ")
	    	 .append(userOrgId);
			 sqlBuf.append(" and m.org_id <= ")
	    	 .append(endOrgId);
		 }
		 
		 if(keyword != null){
			 
			 if(departId != null){ //当登录用户是科室用户时，则关键字中不包括科室关键字查询
				 sqlBuf.append(" and ( m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }else{//当登录用户是医院用户时，关键字中可以包括科室关键字查询
				 sqlBuf.append(" and ( m.depart_name like '%")
				 .append(keyword)
				 .append("%' or m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }
			 
		 }
		 
		 if(null != departName){
			 sqlBuf.append(" and m.depart_name = '" + departName +"'");
   	 }
		 if(null != personType){
			 sqlBuf.append(" and m.person_type = '" + personType + "'");
		 }
		
		 if(null != busiType){
			 sqlBuf.append(" and m.busi_type = '"+busiType +"'");
		 }
		 if(null != personFrom){
			 sqlBuf.append(" and m.person_from = "+personFrom);
		 }
		 
		 if(null != startTime){
			 startTime += " 00:00:00";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  >= '" + startTime +"'");
		 }
		 if(null != endTime){
			 endTime += " 59:59:59";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  <= '" + endTime + "'" );
		 }
		 
		 if(null != monthKey){
			 
			 sqlBuf.append(" and month(m.in_date) in (" + monthKey + ")");
		 }
		 sqlBuf.append(" order by m.IN_DATE desc");
		 
		 System.out.println("sql---->"+sqlBuf.toString());
		 return sqlBuf.toString();
	}
	
	public String getImgUrl(Map<String,Object> params) {
			
		 Long medicalId     	= 	params.get("medicalId") == null ? null :(Long)params.get("medicalId");
		 Integer recogEvent 	=   params.get("recogEvent") == null ? null :(Integer)params.get("recogEvent");
		 Long recogId 			= 	params.get("recogId") == null ? null :(Long)params.get("recogId");
		 StringBuffer sqlBuf 	=   new StringBuffer();
		 //认证类型（10、人脸核查；11、特殊登记；12、特殊建模; 13、未认证）
		 if(recogEvent == 10){
			 sqlBuf.append(" select p.PERSON_ID, p.IDCARD_PHOTO,  ")
			 .append(" r.recog_id, r.recog_address, r.recog_time  ")
			 .append(" from T_PERSON p , t_recog r")
			 .append(" where p.PERSON_ID  = r.person_id and  r.recog_id = " + recogId);
		 }else if(recogEvent == 11){
			 sqlBuf.append("select p.PERSON_ID, p.IDCARD_PHOTO,  ")
			 .append(" v.VIDEO_ADDRESS as recog_address, v.RECOG_TIME ")
			 .append(" from T_PERSON p , t_video_recog v")
			 .append(" where p.PERSON_ID  = v.PERSON_ID and  v.VIDEO_ID = " + recogId);
		 }else if(recogEvent == 12){
			 sqlBuf.append("select m.PERSON_ID, p.IDCARD_PHOTO, m.CREATE_TIME as recog_time, ")
			 .append(" d.prove_Data, d.recog_address  ")
			 .append(" from T_MEDICAL m, T_PERSON p, (select PERSON_ID,GROUP_CONCAT(DATA_URL) as prove_Data, GROUP_CONCAT(RECOG_PIC) as recog_address from T_PERSON_DATA GROUP by PERSON_ID) d  ")
			 .append(" where m.PERSON_ID = p.PERSON_ID and  m.PERSON_ID = d.PERSON_ID  and m.MEDICAL_ID = " + medicalId);
			 
		 }else if(recogEvent == 13){
			 sqlBuf.append("select p.PERSON_ID, p.IDCARD_PHOTO ")
			 .append(" from T_PERSON p , T_MEDICAL m ")
			 .append("where p.PERSON_ID  = m.PERSON_ID and  m.MEDICAL_ID = " + medicalId);
			 
		 }
		 System.out.println("sql---->"+sqlBuf.toString());
		 return sqlBuf.toString();
	 }
	 
	
	public String getallRecogDtoByParam(Map<String,Object> params){
		 
		 String keyword 		= 	params.get("keyword") 			== null ? null : (String)params.get("keyword");
		 Long   recogResult 	= 	params.get("recogResult") 		== null ? null : (Long)params.get("recogResult");
		 String busiType 	    = 	params.get("busiType") 		    == null ? null : (String)params.get("busiType");
		 Integer personFrom     =   params.get("personFrom") 		== null ? null : (Integer)params.get("personFrom");
		 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
		 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
		 String startTime 		= 	params.get("startTime") 		== null ? null : (String)params.get("startTime");
		 String endTime 		= 	params.get("endTime") 			== null ? null : (String)params.get("endTime");
		 Long departId 			= 	params.get("departId") 			== null ? null : (Long)params.get("departId");
		 Long userOrgId 		= 	params.get("userOrgId")         == null ? null : (Long)params.get("userOrgId");
		 String monthKey        =   params.get("monthKey") 			== null ? null : (String)params.get("monthKey");
		 Long medicalId  		= 	params.get("medicalId")  		== null ? null : (Long)params.get("medicalId");
		 
		 
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select m.name, m.medical_id, m.medical_no, m.depart_name, m.bed_no, m.person_from, m.busi_type, m.in_date, m.person_id, m.org_id, m.recog_status,  ")
		 .append(" r.recogStr ")
		 .append(" from T_MEDICAL m left join ")
		 .append(" ( ")
		 .append(" 		select medical_id, GROUP_CONCAT( CONCAT_WS(';',recog_id,recog_mode,recog_result) ORDER BY recog_time DESC) as recogStr ")
		 .append(" 		from  t_recog  GROUP BY medical_id  ")
		 .append(" 	UNION ")
		 .append("		select  medical_id, GROUP_CONCAT( CONCAT_WS(';',video_id,   11,	   audit_status ) ORDER BY recog_time DESC) as recogStr ")
		 .append(" 		from  t_video_recog  GROUP BY medical_id  ")
		 .append(" ) r ")
		 .append(" on m.medical_id = r.medical_id ")
		 .append("where m.status = 1  ");

		 if(userOrgId != null){
			 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
			 sqlBuf.append(" and m.org_id >= ")
	    	 .append(userOrgId);
			 sqlBuf.append(" and m.org_id <= ")
	    	 .append(endOrgId);
		 }
		 
		 if(keyword != null){
			 
			 if(departId != null){ //当登录用户是科室用户时，则关键字中不包括科室关键字查询
				 sqlBuf.append(" and ( m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }else{//当登录用户是医院用户时，关键字中可以包括科室关键字查询
				 sqlBuf.append(" and ( m.depart_name like '%")
				 .append(keyword)
				 .append("%' or m.name like '%")
				 .append(keyword)
				 .append("%' or m.medical_no like '%")
				 .append(keyword)
				 .append("%' or m.bed_no like '%")
				 .append(keyword)
				 .append("%')");
			 }
			 
		 }
		 
		 if(null != medicalId){
			 sqlBuf.append(" and m.medical_id = " + medicalId );
		 }
		 if(null != departName){
			 sqlBuf.append(" and m.depart_name = '" + departName +"'");
		 }
		 if(null != personType){
			 sqlBuf.append(" and m.person_type = '" + personType + "'");
		 }
		
		 if(null != busiType){
			 sqlBuf.append(" and m.busi_type = '"+busiType +"'");
		 }
		 if(null != personFrom){
			 sqlBuf.append(" and m.person_from = "+personFrom);
		 }
		 
		 if(null != recogResult){
			 sqlBuf.append(" and m.recog_status = " + recogResult);
		 }
		 
		 if(null != startTime){
			 startTime += " 00:00:00";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  >= '" + startTime +"'");
		 }
		 if(null != endTime){
			 endTime += " 59:59:59";
			 sqlBuf.append(" and date_format(m.in_date,'%Y-%m-%d %H:%i:%s')  <= '" + endTime + "'" );
		 }
		 
		 if(null != monthKey){
			 
			 sqlBuf.append(" and month(m.in_date) in (" + monthKey + ")");
		 }
		 sqlBuf.append(" order by m.IN_DATE desc");
		 
		 System.out.println("sql---->"+sqlBuf.toString());
		 return sqlBuf.toString();
	}
	public static void main(String[] args){

		RecogSqlProvide recogSql = new RecogSqlProvide();
		Map<String, Object> param = new HashMap<String, Object>();
		recogSql.getallRecogDtoByParam(param);
		
		String str = "5;10;12,4;10;12,3;10;10,2;10;11,1;10;10";
		String[] array = str.split(",");
		for(String str1 : array){
//			System.out.println(str1);
		}
	}
}
