package com.llw.hospital.ds.sqlprovide;

import java.util.Map;

import com.llw.hospital.util.OrgUtil;

public class VideoRecogSqlProvide {
	 public String getVideoRecogList(Map<String,Object> params) {
         Integer auditStatus = params.get("auditStatus") == null ? null : (Integer) params.get("auditStatus");
         String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
         String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
         String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");
         String avaliableSysCodes = params.get("avaliableSysCodes") == null ? null : (String) params.get("avaliableSysCodes");
         String avaliableZoneCode = params.get("avaliableZoneCode") == null ? null : (String) params.get("avaliableZoneCode");
         String avaliableBusiTypes = params.get("avaliableBusiTypes") == null ? null : (String) params.get("avaliableBusiTypes");
         Long departId = params.get("departId") == null ? null : (Long) params.get("departId");
         Integer fromType = params.get("fromType") == null ? null : (Integer) params.get("fromType");
         Long userOrgId = params.get("userOrgId") == null ? null : (Long) params.get("userOrgId");
         
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append(" select v.audit_status as auditStatus,m.name as name,m.idcard as idcard,m.hospital_name as hospitalName,m.depart_name as departName,v.reason as reason, ")
	     .append(" m.diagnosis as diagnosis,m.medical_no as medicalNo,m.bed_no as bedNo,v.input_time as inputTime,v.recog_time as recogTime,v.video_id as videoId,")
	     .append(" v.video_address as videoAddress,m.sex as sex ,m.age as age,per.head_photo as modelAddress ,m.in_date as inDate,m.out_date as outDate")
	     .append(" from t_video_recog v, t_medical_1 m,t_person per,T_SCENE_RECOG sr ")
	     .append(" where v.SCENE_ID = SR.SCENE_ID(+) AND v.MEDICAL_ID = m.MEDICAL_ID AND m.PERSON_ID = per.PERSON_ID and m.revoke_flag = 0 ");
	     if(null != auditStatus){
	    	 sqlBuf.append(" and v.audit_status = ")
	    	 .append(auditStatus);
	     }
	     if(null != keyword){
	    	 if(null != fromType && fromType == 2){//医院端
	    		 sqlBuf.append(" and (m.name like '%")
		    	 .append(keyword)
		    	 .append("%' or m.depart_name like '%")
		    	 .append(keyword)
		    	 .append("%' or m.medical_no like '%")
		    	 .append(keyword)
		    	 .append("%')");
	    	 }else{
	    		 sqlBuf.append(" and (m.name like '%")
		    	 .append(keyword)
		    	 .append("%' or m.idcard like '%")
		    	 .append(keyword)
		    	 .append("%' or m.hospital_name like '%")
		    	 .append(keyword)
		    	 .append("%' or m.medical_no like '%")
		    	 .append(keyword)
		    	 .append("%')");
	    	 }
	    	 
	     }
	     if(null != startTime){	
	    	 sqlBuf.append(" and v.recog_time >= to_date('")
			  .append(startTime)
			  .append("','yyyy-MM-dd hh24:mi:ss')");
	     }
	     if(null != endTime){
	    	 sqlBuf.append(" and v.recog_time <= to_date('")
			  .append(endTime)
			  .append("','yyyy-MM-dd hh24:mi:ss')");
	     }
	     if(null != avaliableSysCodes){
	    	 sqlBuf.append(" and v.sys_code in (")
	    	 .append(avaliableSysCodes+")");
	     }
	     if(null != avaliableZoneCode){
	    	 sqlBuf.append(" and v.zone_code like '")
	    	 .append(avaliableZoneCode+"%'");
	     }
	     if(null != avaliableBusiTypes){
	    	 sqlBuf.append(" and v.busi_type in (")
	    	 .append(avaliableBusiTypes+")");
	     }
	     if(null != departId){
	    	 sqlBuf.append(" and m.depart_id = ")
	    	 .append(departId);
	     }
	     if(null != userOrgId){
	    	 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
	    	 sqlBuf.append(" and v.org_id >= ")
	    	 .append(userOrgId);
	    	 sqlBuf.append(" and v.org_id <= ")
	    	 .append(endOrgId);
	     }
	     sqlBuf.append(" order by v.audit_status, v.input_time desc");
		 return sqlBuf.toString();
	 }
	 
	 public String getVideoRecog(Long videoId) {
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select v.audit_status as auditStatus,v.input_time as inputTime,m.name as name ,m.idcard as idcard,m.hospital_name as hospitalName,m.depart_name as departName,")
		 .append(" v.sys_code as sysCode,v.zone_code as zoneCode,v.busi_type as busiType,v.org_id as orgId,")
		 .append(" m.diagnosis as diagnosis,m.medical_no as medicalNo,m.bed_no as bedNo,m.sex as sex,m.age as age,m.status as status,")
	     .append(" m.in_date as inDate,m.out_date as outDate,m.person_id as personId,p.SCENE_ID as planId,v.video_id as videoId,v.audit_desc as auditDesc,")
	     .append(" v.recog_time as recogTime,v.device_type as deviceType,v.device_code as deviceCode,v.video_address as videoAddress,v.reason ")
	     .append(" from t_video_recog v, t_scene_recog p, t_medical_1 m")
	     .append("  where v.SCENE_ID = p.SCENE_ID(+) and v.MEDICAL_ID = m.MEDICAL_ID ");
		 if(null != videoId){
	    	 sqlBuf.append(" and v.video_id = ")
	    	 .append(videoId);
	     }
		 return sqlBuf.toString();
	 }
}
