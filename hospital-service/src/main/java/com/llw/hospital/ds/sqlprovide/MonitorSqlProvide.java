package com.llw.hospital.ds.sqlprovide;

import java.util.HashMap;
import java.util.Map;

public class MonitorSqlProvide {
	 public String getMonitorList(Map<String,Object> params) {
         String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
         String deviceType = params.get("deviceType") == null ? null : (String) params.get("deviceType");
         
		 StringBuffer sqlBuf = new StringBuffer();
		 /*
		 sqlBuf.append("select t.*,d.location,d.depart_id as departId, (case when d.depart_id is null then s.name else dn.depart_name end)  as hospitalName ")
		 .append(" from t_monitor t,t_device d,sys_organization s, t_depart_relation dn  ")
	     .append(" where t.device_code = d.device_code and d.org_id = s.org_id and d.depart_id = dn.depart_id(+) ");*/
		 
		 sqlBuf.append("select t.*, d.location, d.depart_id as departId, d.hospital_id as hospitalId ")
		 .append(" from T_MONITOR t,T_DEVICE d,sys_organization s  ")
	     .append(" where t.device_code = d.device_code and d.org_id = s.org_id  ");
		 if(null != deviceType){
	    	 sqlBuf.append(" and t.device_type = '")
	    	 .append(deviceType+"'");
	     }
		 if(null != keyword){
	    	 sqlBuf.append(" and (t.device_code like '%")
	    	 .append(keyword)
	    	 .append("%' or s.name like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
	     sqlBuf.append(" order by t.create_time");
		 return sqlBuf.toString();
	 }
	 
	 public static void main(String[] args){
		 
		 
		 MonitorSqlProvide msq = new MonitorSqlProvide();
		 Map<String,Object> params = new HashMap<String,Object>();
		 System.out.println(msq.getMonitorList(params));
	 }
}
