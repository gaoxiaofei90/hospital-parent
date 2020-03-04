package com.llw.hospital.ds.sqlprovide;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class DeviceSqlProvide {
	 public String getDeviceList(Map<String,Object> params) {
		 String deviceStatus = params.get("deviceStatus") == null ? null : (String) params.get("deviceStatus");
		 String netStatus = params.get("netStatus") == null ? null : (String) params.get("netStatus");
         String keyword = params.get("keyword") == null ? null : (String) params.get("keyword");
         String zoneCode = params.get("zoneCode") == null ? null : (String) params.get("zoneCode");
         String fromType = params.get("fromType") == null ? null : (String) params.get("fromType");
         String deviceType = params.get("deviceType") == null ? null : (String) params.get("deviceType");
         Long hospitalId = params.get("hospitalId") == null ? null : (Long) params.get("hospitalId");
         
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select t.*,t2.name hospitalName from T_DEVICE t,sys_organization t2")
	     .append(" where t.hospital_id = t2.org_id");
		 if(null != deviceStatus){
	    	 sqlBuf.append(" and t.device_status = '")
	    	 .append(deviceStatus+"'");
	     }
		 if(null != deviceType){
	    	 sqlBuf.append(" and t.device_type = '")
	    	 .append(deviceType+"'");
	     }
		 if(null != netStatus){
	    	 sqlBuf.append(" and t.net_status = '")
	    	 .append(netStatus+"'");
	     }
//		 if(null != zoneCode){
//	    	 sqlBuf.append(" and t.zone_code = '")
//	    	 .append(zoneCode+"'");
//	     }
		 if(StringUtils.isNotBlank(fromType) && "allocation".equals(fromType)){
	    	 sqlBuf.append(" and t.device_type in ('CF310','CF320','CF330','CF340','CF350','CF360','CF370','CF380','CF390') ");
	     }
		 if(null != keyword){
	    	 sqlBuf.append(" and (t.device_code like '%")
	    	 .append(keyword)
	    	 .append("%' or t2.name like '%")
	    	 .append(keyword)
	    	 .append("%')");
	     }
		 if(null != hospitalId){
			 sqlBuf.append(" and t.hospital_id = ")
			 .append(hospitalId);
		 }
	     sqlBuf.append(" order by t.create_time desc");
		 return sqlBuf.toString();
	 }

	 public String getDevice(String pk) {
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select t.*,t2.name hospitalName from T_DEVICE t,sys_organization t2")
	     .append(" where t.hospital_id = t2.org_id");
		 if(null != pk){
	    	 sqlBuf.append(" and t.device_code = '")
	    	 .append(pk+"'");
	     }
		 return sqlBuf.toString();
	 }

	 public String getDeviceCount(Long serverId,String zoneCode) {
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select COUNT(device_code) from T_DEVICE t where t.device_type in ('CF310','CF320','CF330','CF340','CF350','CF360','CF370','CF380','CF390')");
		 if(null != serverId){
	    	 sqlBuf.append(" and t.server_id = ")
	    	 .append(serverId);
	     }
		 if(null != zoneCode){
	    	 sqlBuf.append(" and t.zone_code = '")
	    	 .append(zoneCode+"'");
	     }
		 return sqlBuf.toString();
	 }

	 public String getMapData() {
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select t.*,s.name as orgName,s.longitude,s.latitude,s.org_category as orgCategory,s.pid")
		 .append(" from T_DEVICE t ,sys_organization s where t.org_id = s.org_id");
		 return sqlBuf.toString();
	 }
	 
	 public String getDeviceCountByOrg(Map<String, Object> params) {
		 Long beginOrgId = params.get("beginOrgId") == null ? null : (Long) params.get("beginOrgId");
		 Long endOrgId = params.get("endOrgId") == null ? null : (Long) params.get("endOrgId");
		 StringBuffer sqlBuf = new StringBuffer();
		 sqlBuf.append("select t.*,(select count(1) from T_DEVICE d where d.hospital_id = t.org_id) deviceCount")
		 .append(" from sys_organization t where t.longitude is not null and t.latitude is not null");
		 if(null != beginOrgId){
			 sqlBuf.append(" and t.org_id >= ").append(beginOrgId);
		 }
		 if(null != endOrgId){
			 sqlBuf.append(" and t.org_id <= ").append(endOrgId);
		 }
		 return sqlBuf.toString();
	 }
}
