package com.llw.hospital.ds.sqlprovide;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.llw.hospital.util.OrgUtil;

public class MedicalSqlProvide {
	 public String getMedicalList(Map<String,Object> params) {
         Integer medicalType = params.get("medicalType") == null ? null : (Integer) params.get("medicalType");
         String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
         Long[]  personArr = (Long[]) params.get("personArr");
         
         Integer blackAndWhite = (Integer) params.get("blackAndWhite") ;
         String label = (String) params.get("label") ;
         String zoonCode = params.get("zoonCode") == null ? null : (String) params.get("zoonCode");
		
         StringBuffer sqlBuf = new StringBuffer();
		// sqlBuf.append(" select m.PERSON_ID as personId,m.name,m.idcard ,p.IDCARD_PHOTO as photoAddress   FROM t_medical_1  m left join t_person p on m.PERSON_ID = p.PERSON_ID ");
	    //   where 1=1
         sqlBuf.append(" SELECT P.PERSON_ID as personId,P.name,P.idcard,P.IDCARD_PHOTO as photoAddress FROM T_PERSON P ");
         if(blackAndWhite != -1) {
			 sqlBuf.append(" left join T_BLACKLIST_PERSON bp on p.PERSON_ID = bp.obj_id ");
		 }
		 
		 if(!"-1".equals(label)) {
			 sqlBuf.append(" left join T_PERSON_LABEL pl on p.PERSON_ID = pl.PERSON_ID ");
		 }
		 
		 if(null != medicalType && medicalType != 0){
			 sqlBuf.append(" left join t_medical_1 m on p.PERSON_ID = m.PERSON_ID ");
	     }
		 
		 sqlBuf.append(" where 1=1 ");
		 
		 if(null != medicalType && medicalType != 0){
	    	 sqlBuf.append(" and m.medical_type = ")
	    	 .append(medicalType);
	     }
	    
	     if(null != keyword){
	    	 sqlBuf.append(" and (p.name like '%")
	    	 .append(keyword)
	    	 .append("%' or p.idcard like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
	     
	     if(personArr != null) {
        	 String personids = StringUtils.join(personArr,",");
        	 sqlBuf.append(" and p.PERSON_ID in ( ")
        	 .append(personids)
        	 .append(" ) ");
         }
	     
	     if(blackAndWhite != -1) {
			 sqlBuf.append(" and bp.BLACKLIST_GRADE = ")
			 .append(blackAndWhite);
		 }
		 
		 if(!"-1".equals(label)) {
			 sqlBuf.append(" and pl.LABEL_CODE = ")
			 .append(label);
		 }
		 
		/* if(StringUtils.isNotBlank(zoonCode)) {
			 sqlBuf.append(" and m.ZONE_CODE = ")
			 .append(zoonCode);
		 }*/
	     sqlBuf.append(" GROUP BY  p.PERSON_ID ,p.name ,p.idcard,p.IDCARD_PHOTO ");
		 return sqlBuf.toString();
	 }
	
	 
	 
	 public String medicalCount(Map<String,Object> params) {
		 Long orgBeginId = params.get("orgBeginId") == null ? null : (Long) params.get("orgBeginId");
         Long orgEndId = params.get("orgEndId") == null ? null : (Long) params.get("orgEndId");
         StringBuffer sqlBuffer = new StringBuffer(); 
         sqlBuffer.append("select count(distinct a.medical_id) medicalCount");
         sqlBuffer.append("  from t_medical_1 a, t_recog b");
         sqlBuffer.append(" where a.person_id = b.person_id");
         sqlBuffer.append("   and trunc(a.in_date) <= trunc(b.recog_time)");
         sqlBuffer.append("   and trunc(nvl(a.out_date,sysdate)) >= trunc(b.recog_time)");
         sqlBuffer.append("   and b.device_type in ('CF310','CF320','CF330','CF340','CF350','CF360','CF370','CF380','CF390')");
         sqlBuffer.append("   and a.org_id >= ");
         sqlBuffer.append(orgBeginId);
         sqlBuffer.append("   and a.org_id <= ");
         sqlBuffer.append(orgEndId);

		 return sqlBuffer.toString();
	 }
	 
	 
	 public String getPersonId(Map<String,Object> params) {
		 String name = params.get("name") == null ? null : (String) params.get("name");
	     String idcard = params.get("idcard") == null ? null : (String) params.get("idcard");

		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select b.person_id "
				 	   + "from t_person b "
				 	  + "where 1 = 1 ");

		 if(null != name){
	    	 sqlBuf.append(" and name = "+name);
	     }
	     if(null != idcard){
	    	 sqlBuf.append(" and idcard = "+idcard);
	     }
	     sqlBuf.append(" and rownum = 1 ");
		 return sqlBuf.toString();
	 }
	 
	 public String getPerson() {
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select m.medical_id,m.name,m.idcard,m.sicard "
				 	   + "from t_medical_1 m "
				 	  + "where m.status = 1 ");
		 return sqlBuf.toString();
	 }
	 
	 public String update(Long medicalId) {
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("update t_medical_1 m "
		 				+ "set m.out_date = sysdate " 
				 	      + ", m.update_time =  sysdate " 
				 	      + ", m.status = 2 " 
				 	  + "where m.medical_id = " + medicalId);
		 return sqlBuf.toString();
	 }
	 
	 
	 public String medicalHistoryList(Map<String,Object> params) {
		 String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
		 StringBuffer sqlBuffer = new StringBuffer(); 
		 sqlBuffer.append(" select a.person_id as personId,a.name,a.idcard,a.birthday,b.amount as medicalCount ,a.sex");
		 sqlBuffer.append("   from t_person a, ");
		 sqlBuffer.append("        (select t.person_id, count(1) amount ");
		 sqlBuffer.append("           from t_medical_1 t ");
		 sqlBuffer.append("          where t.medical_type = 1 and t.status = 2 ");
		 sqlBuffer.append("          group by t.person_id) b ");
		 sqlBuffer.append("  where a.person_id = b.person_id ");
		 if(null != keyword){
			 sqlBuffer.append(" and (a.name like '%")
	    	 .append(keyword)
	    	 .append("%' or a.idcard like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
		 sqlBuffer.append(" order by a.person_id");
		 return sqlBuffer.toString();
	 }
	 

	 //一级页面查询---库-》在院人员 
	 public String getInhospital(Map<String ,Object> params){
			 
			 String keyword = params.get("keyword") == null ? null :(String)params.get("keyword");
			 
			 StringBuffer sqlBuffer = new StringBuffer(); 
			 sqlBuffer.append("SELECT t.hospital_id,t.hospital_code,t.hospital_name, count(decode(t.person_type, 1, 1)) AS medicalPersonTotal, count(decode(t.person_type, 2, 1)) AS noMedicalPersonTotal, count(1) AS inHospitalAcount");
			 sqlBuffer.append("   FROM t_medical_1 t  where t.status = 1 and t.medical_type = 1 ");
			 
			 if(null != keyword){
				// sqlBuffer.append(" where (t.hospital_code like '%")
				 sqlBuffer.append(" and  (t.hospital_code like '%")
		    	 .append(keyword)
		    	 .append("%' or t.hospital_name like '%")
		    	 .append(keyword)
		    	 .append("%')");
		     }
			 sqlBuffer.append("    group by t.hospital_id, t.hospital_code, t.hospital_name ");
			 sqlBuffer.append("   order by t.hospital_id  ");
			 return sqlBuffer.toString();
		 }
		 
		 
		 
	 //二级页面查询---库-》在院人员 详情
	 public String getInhospitaPersonList(Map<String ,Object> params){
			 
		 	 Long hospitalId 		= params.get("hospitalId") == null ? null :(Long)params.get("hospitalId");
			 String keyword 		= params.get("keyword") == null ? null :(String)params.get("keyword");
			 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
			 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
			 Integer warningStatus 	= 	params.get("warningStatus") 	== null ? null : (Integer)params.get("warningStatus");
			 Integer leaveStatus 	= 	params.get("leaveStatus") 		== null ? null : (Integer)params.get("leaveStatus");
			 String startInHosTime 	= 	params.get("startInHosTime") 	== null ? null : (String)params.get("startInHosTime");
			 String endInHosTime 	= 	params.get("endInHosTime") 		== null ? null : (String)params.get("endInHosTime");
			 Long departId 		= params.get("departId") == null ? null :(Long)params.get("departId");
			 
			 StringBuffer sqlBuffer = new StringBuffer(); 
			 sqlBuffer.append("select t.hospital_id, t.medical_id,t.person_id, t.name, t.idcard, t.medical_no, t.depart_name, t.depart_id, t.depart_code, t.bed_no, t.in_date ,t.person_type, t.warning_status, t.leave_status ");
			 sqlBuffer.append("   from t_medical_1 t ");
			 sqlBuffer.append("	   where t.medical_type = 1 and t.status = 1 ");  
			 
			 if(null != hospitalId){
				 sqlBuffer.append(" and  t.hospital_id ="+hospitalId);
	    	 }
			 if(null != keyword){
				 sqlBuffer.append(" and  (t.idcard like '%")
		    	 .append(keyword)
		    	 .append("%' or t.name like '%")
		    	 .append(keyword)
		    	 .append("%' or t.medical_no like '%")
		    	 .append(keyword)
		    	 .append("%' or t.bed_no like '%")
		    	 .append(keyword)
		    	 .append("%')");
		     }
			 
			 if(null != departId){
				 sqlBuffer.append(" and  t.depart_id ="+departId);
			 }
			 
			 if(null != departName){
				 sqlBuffer.append(" and t.depart_name = '" + departName +"'");
	    	 }
			 if(null != personType){
				 sqlBuffer.append(" and t.person_type = '" + personType + "'");
			 }
			 if(null != warningStatus){ //暂时字段数据库中未加--预警状态
				 sqlBuffer.append(" and t.warning_status = "+warningStatus);
			 }
			 if(null != leaveStatus){ //暂时字段数据库中未加--请假状态
				 sqlBuffer.append(" and t.leave_status = "+leaveStatus);
			 }
			 if(null != startInHosTime){
				 sqlBuffer.append(" and trunc(t.in_date) >= to_date('" + startInHosTime + "','yyyy/mm/dd')" );
			 }
			 if(null != endInHosTime){
				 sqlBuffer.append(" and trunc(t.in_date) <= to_date('" + endInHosTime   + "','yyyy/mm/dd')" );
			 }
			 
			 sqlBuffer.append(" order by t.in_date desc,t.person_id");
			 
			// System.out.println("sql==="+sqlBuffer.toString());
			 
			 return sqlBuffer.toString();
		 }
		 
		 
	 //二级页面查询---有入院信息人员 
	 public String getPersonbyStatus(Map<String ,Object> params){
				 
		 String keyword = params.get("keyword") == null ? null :(String)params.get("keyword");
		 Long hospitalId = params.get("hospitalId") == null ? null :(Long)params.get("hospitalId");
		 String sqlStr = new SQL(){{
			 SELECT(" distinct p.person_id, p.name, p.idcard, p.idcard_photo, m.medical_no, m.depart_name, m.bed_no, m.in_date"); 
			 FROM("t_medical_1 m");
			 JOIN("t_person p on m.person_id = p.person_id");
			 WHERE("m.status = 1");
			 if(null != hospitalId){
	    		 WHERE("m.hospital_id ="+hospitalId);
	    	 }
			 if(null != keyword){
	    		 WHERE("p.name like '%" + keyword + "%' or p.idcard like '%"+keyword+"%'");
	    	 }
			 ORDER_BY(" m.in_date desc");
		 }}.toString();
		 return sqlStr;
		}
		
	 
	 public String getMedical1List( Map<String ,Object> params){
		 String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
		 Integer medicalType = params.get("medicalType") == null ? null : (Integer) params.get("medicalType");
		 Integer recogBusi = params.get("recogBusi") == null ? null : (Integer) params.get("recogBusi");
		 String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
         String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");
		 StringBuffer sqlBuffer = new StringBuffer(); 
		 sqlBuffer.append(" select a.hospital_id,a.hospital_code,a.hospital_name, ");
		 sqlBuffer.append("	count(1) inHospitalAcount,");
		 if(null != recogBusi){
			 sqlBuffer.append(" sum(b.recog"+recogBusi+"_fin) inHospitalRecogAcount,");
		 }
		 
		 sqlBuffer.append(" count(decode(b.warning,0,null,1)) warningAcount ");
		 sqlBuffer.append("   from t_medical_1 a, t_medical_index b ");
		 sqlBuffer.append("  where a.medical_id = b.medical_id ");
		 if(null != medicalType){
			 sqlBuffer.append("    and a.medical_type =  ")
			 .append(medicalType);
			 if(medicalType == 1){//住院带上 在院状态
				 sqlBuffer.append("  and a.status = 1");
			 }
		 }
		 if(null != keyword){
			 sqlBuffer.append(" and (a.hospital_code like '%")
	    	 .append(keyword)
	    	 .append("%' or a.hospital_name like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
		 if(null != startTime){
        	 sqlBuffer.append("   and trunc(a.in_date) >= to_date('")
        	 .append(startTime)
        	 .append("','yyyy/mm/dd')");
         }
         if(null != endTime){
        	 sqlBuffer.append("   and trunc(a.in_date) <= to_date('")
        	 .append(endTime)
        	 .append("','yyyy/mm/dd')");
         }
		 sqlBuffer.append("  group by a.hospital_id, a.hospital_code, a.hospital_name");
		 return sqlBuffer.toString();
	 }
	 
	 public String getMedical2List( Map<String ,Object> params){
		 String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
		 String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
         String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");
		 StringBuffer sqlBuffer = new StringBuffer(); 
		 sqlBuffer.append(" select t.hospital_id,t.hospital_code,t.hospital_name,count(1) inHospitalAcount, ")
		 .append(" count(case when t.status in (11, 21, 31) then 1 end) inHospitalRecogAcount from t_medical_2 t where 1=1");
		 if(null != keyword){
			 sqlBuffer.append(" and (t.hospital_code like '%")
	    	 .append(keyword)
	    	 .append("%' or t.hospital_name like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
		 if(null != startTime){
        	 sqlBuffer.append("  and trunc(t.seek_time) >= to_date('")
        	 .append(startTime)
        	 .append("','yyyy/mm/dd')");
         }
         if(null != endTime){
        	 sqlBuffer.append("  and trunc(t.seek_time) <= to_date('")
        	 .append(endTime)
        	 .append("','yyyy/mm/dd')");
         }
		 sqlBuffer.append(" group by t.hospital_id, t.hospital_code, t.hospital_name");
		 return sqlBuffer.toString();
	 }
	 
	 
	 public String getMedical4List( Map<String ,Object> params){
		 String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
		 String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
         String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");
		 StringBuffer sqlBuffer = new StringBuffer(); 
		 sqlBuffer.append(" select t.hospital_id,t.hospital_code,t.hospital_name,count(1) inHospitalAcount, ")
		 .append(" count(case when t.status in (11, 21, 31) then 1 end) inHospitalRecogAcount from t_medical_4 t where 1=1");
		 if(null != keyword){
			 sqlBuffer.append(" and (t.hospital_code like '%")
	    	 .append(keyword)
	    	 .append("%' or t.hospital_name like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
		 if(null != startTime){
        	 sqlBuffer.append("  and trunc(t.medical_time) >= to_date('")
        	 .append(startTime)
        	 .append("','yyyy/mm/dd')");
         }
         if(null != endTime){
        	 sqlBuffer.append("  and trunc(t.medical_time) <= to_date('")
        	 .append(endTime)
        	 .append("','yyyy/mm/dd')");
         }
		 sqlBuffer.append(" group by t.hospital_id, t.hospital_code, t.hospital_name");
		 return sqlBuffer.toString();
	 }
	 
	 
	 public String getMedical3List( Map<String ,Object> params){
		 String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
		 String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
         String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");
		 StringBuffer sqlBuffer = new StringBuffer(); 
		 sqlBuffer.append(" select t.hospital_id,t.hospital_code,t.hospital_name,count(1) inHospitalAcount, ")
		 .append(" count(case when t.status in (11, 21, 31) then 1 end) inHospitalRecogAcount from t_medical_3 t where 1=1 and t.medical_type = 32");
		 if(null != keyword){
			 sqlBuffer.append(" and (t.hospital_code like '%")
	    	 .append(keyword)
	    	 .append("%' or t.hospital_name like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
		 if(null != startTime){
        	 sqlBuffer.append("  and trunc(t.seek_time) >= to_date('")
        	 .append(startTime)
        	 .append("','yyyy/mm/dd')");
         }
         if(null != endTime){
        	 sqlBuffer.append("  and trunc(t.seek_time) <= to_date('")
        	 .append(endTime)
        	 .append("','yyyy/mm/dd')");
         }
		 sqlBuffer.append(" group by t.hospital_id, t.hospital_code, t.hospital_name");
		 return sqlBuffer.toString();
	 }
	 
	 public String getMedicalIndexDetailList( Map<String ,Object> params){
		 String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
		 Integer medicalType = params.get("medicalType") == null ? null : (Integer) params.get("medicalType");
		 Integer recogBusi = params.get("recogBusi") == null ? null : (Integer) params.get("recogBusi");
		 Long hospitalId = params.get("hospitalId") == null ? null :(Long)params.get("hospitalId");
		 String personType = params.get("personType") == null ? null :(String)params.get("personType");
		 String orderType = params.get("orderType") == null ? null : (String) params.get("orderType");
         String orderField = params.get("orderField") == null ? null : (String) params.get("orderField");
         
		 StringBuffer sqlBuffer = new StringBuffer(); 
		 sqlBuffer.append(" select * from (select a.hospital_id as hospitalId, ");
		 sqlBuffer.append("        a.hospital_code as hospitalCode, ");
		 sqlBuffer.append("        a.hospital_name as hospitalName, ");
		 if(null != recogBusi){
			 sqlBuffer.append("        b.recog"+recogBusi+" shouldCensusACount, ");
			 sqlBuffer.append("        b.recog"+recogBusi+"_fin censusACount, ");
		 }
		 sqlBuffer.append("        b.recog12_fin firstRecogAcount, ");
		 
		 sqlBuffer.append("        decode(b.warning, 0, null, 1) warningAcount, ");
		 sqlBuffer.append("        a.name, a.medical_id as medicalId,");
		 sqlBuffer.append("        a.idcard, ");
		 sqlBuffer.append("        a.medical_no as medicalNo, ");
		 sqlBuffer.append("        a.bed_no as bedNo, ");
		 sqlBuffer.append("        a.depart_name as departName, ");
		 sqlBuffer.append("        a.in_date as inDate, ");
		 sqlBuffer.append("        a.person_type as personType ");
		 sqlBuffer.append("   from t_medical_1 a, t_medical_index b ");
		 sqlBuffer.append("  where a.medical_id = b.medical_id ");
		 
		 if(null != medicalType){
			 sqlBuffer.append("    and a.medical_type =  ")
			 .append(medicalType);
			 if(medicalType == 1){//住院带上 在院状态
				 sqlBuffer.append("  and a.status = 1");
			 }
		 }
		 if(null != hospitalId){
			 sqlBuffer.append(" and a.hospital_id=" + hospitalId );
		 }
		 
		 if(null != keyword){
			 sqlBuffer.append(" and (a.name like '%")
	    	 .append(keyword)
	    	 .append("%' or a.idcard like '%")
	    	 .append(keyword)
	    	 .append("%' or a.medical_no like '%")
	    	 .append(keyword)
	    	 .append("%' or a.bed_no like '%")
	    	 .append(keyword)
	    	 .append("%' or a.depart_name like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
		 if(null != personType){
			 sqlBuffer.append(" and a.person_type=" + personType);
		 }
		 sqlBuffer.append(" ) t");
		 if(null != orderField){
        	 sqlBuffer.append(" order by ").append(orderField);
         }
         if(null != orderType){
        	 sqlBuffer.append(" ").append(orderType);
         }
         if(null == orderField && null == orderType){
        	 sqlBuffer.append(" order by inDate desc,medicalId");
         }
		 return sqlBuffer.toString();
	 }
	 
	 public String getOutpatPharmacyDetailList( Map<String ,Object> params){
		 String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
		 String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
         String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");
         Long hospitalId = params.get("hospitalId") == null ? null : (Long) params.get("hospitalId");
         Integer medicalType = params.get("medicalType") == null ? null : (Integer) params.get("medicalType");
         Integer recogBusi = params.get("recogBusi") == null ? null : (Integer) params.get("recogBusi");
         String orderType = params.get("orderType") == null ? null : (String) params.get("orderType");
         String orderField = params.get("orderField") == null ? null : (String) params.get("orderField");
         
		 StringBuffer sqlBuffer = new StringBuffer(); 
		 sqlBuffer.append(" select * from (select a.hospital_id, ");
		 sqlBuffer.append("        a.hospital_code, ");
		 sqlBuffer.append("        a.hospital_name, ");
		 sqlBuffer.append("        a.name, ");
		 sqlBuffer.append("        a.idcard, ");
		 sqlBuffer.append("        a.medical_id as medicalId, ");
		 sqlBuffer.append("        a.medical_no as medicalNo, ");
		 sqlBuffer.append("        a.bed_no as bedNo, ");
		 sqlBuffer.append("        a.depart_name as departName, ");
		 sqlBuffer.append("        a.in_date as inDate, ");
		 sqlBuffer.append("        b.recog_time as recogTime, ");
		 sqlBuffer.append("        b.status as recogResult");
		 sqlBuffer.append("   from t_medical_1 a, t_scene_recog b where a.medical_id = b.medical_id");
		 
		 if(null != medicalType){
        	 sqlBuffer.append("   and a.medical_type = ")
        	 .append(medicalType);
         }
         if(null != hospitalId){
        	 sqlBuffer.append(" and a.hospital_id = ")
			 .append(hospitalId);
		 }
         if(null != recogBusi){
        	 sqlBuffer.append(" and b.recog_busi(+) = ")
			 .append(recogBusi);
		 }
         if(null != startTime){
        	 sqlBuffer.append("   and trunc(a.in_date) >= to_date('")
        	 .append(startTime)
        	 .append("','yyyy/mm/dd')");
         }
         if(null != endTime){
        	 sqlBuffer.append("   and trunc(a.in_date) <= to_date('")
        	 .append(endTime)
        	 .append("','yyyy/mm/dd')");
         }
         if(null != keyword){
        	 sqlBuffer.append(" and (a.name like '%")
	    	 .append(keyword)
	    	 .append("%' or a.idcard like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
         sqlBuffer.append(" )t");
         if(null != orderField){
        	 sqlBuffer.append(" order by ").append(orderField);
         }
         if(null != orderType){
        	 sqlBuffer.append(" ").append(orderType);
         }
         if(null == orderField && null == orderType){
        	 sqlBuffer.append(" order by inDate desc,medicalId");
         }
		 return sqlBuffer.toString();
	 }

	 
	 public String getOutHospitalPersonRecord(Map<String,Object> params) {
		 Integer status = params.get("status") == null ? null : (Integer) params.get("status");
         String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
         String startTime = params.get("startTime") == null ? null : (String) params.get("startTime");
         String endTime = params.get("endTime") == null ? null : (String) params.get("endTime");
         Long departId = params.get("departId") == null ? null : (Long) params.get("departId");
         Long userOrgId = params.get("userOrgId") == null ? null : (Long) params.get("userOrgId");
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select * from t_medical_1 m where 1=1");
		 if(null != status){
			 sqlBuf.append(" and m.status = ")
	    	 .append(status);
		 }
	     if(null != keyword){
	    	 sqlBuf.append(" and (m.name like '%")
	    	 .append(keyword)
	    	 .append("%' or m.medical_no like '%")
	    	 .append(keyword)
	    	 .append("%' or m.depart_name like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
	     if(null != startTime){
	    	 sqlBuf.append(" and m.out_date >= to_date('")
			  .append(startTime)
			  .append("','yyyy-MM-dd hh24:mi:ss')");
	     }
	     if(null != endTime){
	    	 sqlBuf.append(" and  m.out_date <= to_date('")
			  .append(endTime)
			  .append("','yyyy-MM-dd hh24:mi:ss')");
	     }
	     if(null != departId){
	    	 sqlBuf.append(" and m.depart_id = ")
	    	 .append(departId);
	     }
	     if(null != userOrgId){
	    	 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
	    	 sqlBuf.append(" and m.org_id >= ")
	    	 .append(userOrgId);
	    	 sqlBuf.append(" and m.org_id <= ")
	    	 .append(endOrgId);
	     }
	     sqlBuf.append(" order by m.out_date desc,m.person_id");
		 return sqlBuf.toString();
	 }
	 
	 public String updateStatus(Long medicalId) {
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("update t_medical_1 m "
		 				+ "set m.out_date = null " 
				 	      + ", m.update_time =  null " 
				 	      + ", m.out_date0 =  null " 
				 	      + ", m.status = 1 " 
				 	  + "where m.medical_id = " + medicalId);
		 return sqlBuf.toString();
	 }
	 
	 public String getInHosPersonList(Map<String, Object> params){
			 
			 Long hospitalId   		= 	params.get("hospitalId") 		== null ? null : (Long)params.get("hospitalId");
			 Long departId   		= 	params.get("departId") 		    == null ? null : (Long)params.get("departId");
			 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
			 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
			 Integer warningStatus 	= 	params.get("warningStatus") 	== null ? null : (Integer)params.get("warningStatus");
			 Integer leaveStatus 	= 	params.get("leaveStatus") 		== null ? null : (Integer)params.get("leaveStatus");
			 String startInHosTime 	= 	params.get("startInHosTime") 	== null ? null : (String)params.get("startInHosTime");
			 String endInHosTime 	= 	params.get("endInHosTime") 		== null ? null : (String)params.get("endInHosTime");
			 String busiType 	    = 	params.get("busiType") 		    == null ? null : (String)params.get("busiType");
			 Integer personFrom     =   params.get("personFrom") 		== null ? null : (Integer)params.get("personFrom");
			 String keyword 		= 	params.get("keyword") 			== null ? null : (String) params.get("keyword");
			 Long userOrgId 		= 	params.get("userOrgId") 		== null ? null : (Long) params.get("userOrgId");
			 
			 StringBuffer sqlBuffer = new StringBuffer(); 
			 sqlBuffer.append("select m.hospital_id, m.medical_id,m.person_id, m.name,m.idcard, m.medical_no,  m.depart_name, m.bed_no, m.in_date, ")
					  .append(" m.person_type , m.warning_status , m.leave_status, m.person_from, m.busi_type, m.org_id, b.busi_name ")
					  .append(" from t_medical_1 m , SYS_BUSINESS b  ")
					  .append(" where m.busi_type = b.busi_type(+)  ")
					  .append(" and m.medical_type = 1 and m.status = 1 ");
			 
			 if(null != userOrgId){
		    	 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
		    	 sqlBuffer.append(" and m.org_id >= ")
		    	 .append(userOrgId);
		    	 sqlBuffer.append(" and m.org_id <= ")
		    	 .append(endOrgId);
		     }
			 /*
			 if(null != hospitalId){
				 sqlBuffer.append(" and m.hospital_id = " + hospitalId);
	    	 }
			 if(null != departId){
				 sqlBuffer.append(" and m.depart_id = " + departId);
	    	 }*/
			 
			 if(null != departName){
				 sqlBuffer.append(" and m.depart_name = '" + departName +"'");
	    	 }
			 if(null != personType){
				 sqlBuffer.append(" and m.person_type = '" + personType + "'");
			 }
			 if(null != warningStatus){ //暂时字段数据库中未加--预警状态
				 sqlBuffer.append(" and m.warning_status = "+warningStatus);
			 }
			 if(null != leaveStatus){ //暂时字段数据库中未加--请假状态
				 sqlBuffer.append(" and m.leave_status = "+leaveStatus);
			 }
			 if(null != busiType){
				 sqlBuffer.append(" and m.busi_type = '"+busiType +"'");
			 }
			 if(null != personFrom){
				 sqlBuffer.append(" and m.person_from = "+personFrom);
			 }
			 if(null != keyword){
				 
				 if(departId != null){ //当登录用户是科室用户时，则关键字中不包括科室关键字查询
					 sqlBuffer.append(" and  (m.name like '%")
			    	 .append(keyword)
			    	 .append("%' or m.idcard like '%")
			    	 .append(keyword)
			    	 .append("%' or m.medical_no like '%")
			    	 .append(keyword)
			    	 .append("%' or m.bed_no like '%")
			    	 .append(keyword)
			    	 .append("%')");
				 }else{//当登录用户是医院用户时，关键字中可以包括科室关键字查询
					 sqlBuffer.append(" and  (m.depart_name like '%")
			    	 .append(keyword)
			    	 .append("%' or m.name like '%")
			    	 .append(keyword)
			    	 .append("%' or m.idcard like '%")
			    	 .append(keyword)
			    	 .append("%' or m.medical_no like '%")
			    	 .append(keyword)
			    	 .append("%' or m.bed_no like '%")
			    	 .append(keyword)
			    	 .append("%')");
				 }
				 
		     }
			 if(null != startInHosTime){
				 sqlBuffer.append(" and trunc(m.in_date) >= to_date('" + startInHosTime + "','yyyy/mm/dd')" );
			 }
			 if(null != endInHosTime){
				 sqlBuffer.append(" and trunc(m.in_date) <= to_date('" + endInHosTime   + "','yyyy/mm/dd')" );
			 }
			 
			 sqlBuffer.append(" order by  m.depart_name, m.bed_no ");
			 //System.out.println("sql---"+sqlBuffer.toString());
		 return sqlBuffer.toString();
		 
	 	}
	 
	 
	 
	 //医院端---在院人员查询
	 public String getPersonListByLoginUser(Map<String ,Object> params){
			 
		 	 Long hospitalId 		=   params.get("hospitalId") 		== null ? null :(Long)params.get("hospitalId");
			 String keyword 		= 	params.get("keyword") 			== null ? null :(String)params.get("keyword");
			 String departName 		= 	params.get("departName") 		== null ? null : (String)params.get("departName");
			 String personType 		= 	params.get("personType") 		== null ? null : (String)params.get("personType");
			 Integer warningStatus 	= 	params.get("warningStatus") 	== null ? null : (Integer)params.get("warningStatus");
			 Integer leaveStatus 	= 	params.get("leaveStatus") 		== null ? null : (Integer)params.get("leaveStatus");
			 String startInHosTime 	= 	params.get("startInHosTime") 	== null ? null : (String)params.get("startInHosTime");
			 String endInHosTime 	= 	params.get("endInHosTime") 		== null ? null : (String)params.get("endInHosTime");
			 String busiType 	    = 	params.get("busiType") 		    == null ? null : (String)params.get("busiType");
			 Integer personFrom     =   params.get("personFrom") 		== null ? null : (Integer)params.get("personFrom");
			 Long departId 			= 	params.get("departId") 			== null ? null :(Long)params.get("departId");
			 Long userOrgId 		= 	params.get("userOrgId")         == null ? null  :(Long)params.get("userOrgId");
			 
			 StringBuffer sqlBuffer = new StringBuffer(); 
			
			 sqlBuffer.append("select t.hospital_id, t.medical_id,t.person_id, t.name, t.idcard, t.medical_no, t.depart_name, t.depart_id, t.depart_code, t.bed_no, t.in_date ,t.person_type, t.warning_status, t.leave_status, t.person_from, t.busi_type, t.org_id, b.busi_name");
			 sqlBuffer.append("   from t_medical_1 t, SYS_BUSINESS b ");
			 sqlBuffer.append("	   where t.busi_type = b.busi_type(+) and t.medical_type = 1 and t.status = 1 ");  
			 
			 if(userOrgId != null){
				 Long endOrgId = OrgUtil.getMaxLeafTheoreticalValue(userOrgId);
				 sqlBuffer.append(" and t.org_id >= ")
		    	 .append(userOrgId);
				 sqlBuffer.append(" and t.org_id <= ")
		    	 .append(endOrgId);
			 }
			 
//			 if(null != hospitalId){
//				 sqlBuffer.append(" and  t.hospital_id ="+hospitalId);
//	    	 }
			 
			 if(null != keyword){
				 if(departId != null){ //当登录用户是科室用户时，则关键字中不包括科室关键字查询
					 sqlBuffer.append(" and  (t.name like '%")
			    	 .append(keyword)
			    	 .append("%' or t.idcard like '%")
			    	 .append(keyword)
			    	 .append("%' or t.medical_no like '%")
			    	 .append(keyword)
			    	 .append("%' or t.bed_no like '%")
			    	 .append(keyword)
			    	 .append("%')");
				 }else{//当登录用户是医院用户时，关键字中可以包括科室关键字查询
					 sqlBuffer.append(" and  (t.depart_name like '%")
			    	 .append(keyword)
			    	 .append("%' or t.name like '%")
			    	 .append(keyword)
			    	 .append("%' or t.idcard like '%")
			    	 .append(keyword)
			    	 .append("%' or t.medical_no like '%")
			    	 .append(keyword)
			    	 .append("%' or t.bed_no like '%")
			    	 .append(keyword)
			    	 .append("%')");
				 }
		     }
			 
//			 if(null != departId){
//				 sqlBuffer.append(" and  t.depart_id ="+departId);
//			 }
			 
			 if(null != departName){
				 sqlBuffer.append(" and t.depart_name = '" + departName +"'");
	    	 }
			 if(null != personType){
				 sqlBuffer.append(" and t.person_type = '" + personType + "'");
			 }
			 if(null != warningStatus){ 
				 sqlBuffer.append(" and t.warning_status = "+warningStatus);
			 }
			 if(null != leaveStatus){
				 sqlBuffer.append(" and t.leave_status = "+leaveStatus);
			 }
			 if(null != busiType){
				 sqlBuffer.append(" and t.busi_type = '"+busiType +"'");
			 }
			 if(null != personFrom){
				 sqlBuffer.append(" and t.person_from = "+personFrom);
			 }
			 if(null != startInHosTime){
				 sqlBuffer.append(" and trunc(t.in_date) >= to_date('" + startInHosTime + "','yyyy/mm/dd')" );
			 }
			 if(null != endInHosTime){
				 sqlBuffer.append(" and trunc(t.in_date) <= to_date('" + endInHosTime   + "','yyyy/mm/dd')" );
			 }
			 
			 sqlBuffer.append(" order by t.in_date desc,t.person_id");
			 
			// System.out.println("sql==="+sqlBuffer.toString());
			 
			 return sqlBuffer.toString();
		 }
			 
	 public static void main(String[] args){
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 MedicalSqlProvide sql = new MedicalSqlProvide();
		 System.err.println(sql.getInhospitaPersonList(map));
	 }
}
