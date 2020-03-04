package com.llw.hospital.ds.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.llw.hospital.ds.sqlprovide.DeviceSqlProvide;
import com.llw.hospital.dto.DeviceDto;
import com.llw.hospital.dto.DeviceExtendDto;

public interface DeviceMapper {
		@SelectProvider(type = DeviceSqlProvide.class,method = "getDeviceList")
		List<DeviceExtendDto> getDeviceList(Map<String, Object> params);

		@SelectProvider(type = DeviceSqlProvide.class,method = "getDevice")
		DeviceExtendDto getDevice(String pk);

		@SelectProvider(type = DeviceSqlProvide.class,method = "getDeviceCount")
		int getDeviceCount(Long serverId,String zoneCode);

		@SelectProvider(type = DeviceSqlProvide.class, method = "getMapData")
		List<DeviceExtendDto> getMapData();

		@Update(value = "<script>" +
			"<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"begin\" close=\";end;\" separator=\";\">" +
			"	UPDATE T_DEVICE" +
			"	set online_time = #{item.date}" +
			"	where online_time <![CDATA[ < ]]> #{item.date} and device_code = #{item.deviceCode}" +
			"</foreach>" +
			"</script>")
    	int batchUpdateOnlineTime(@Param(value="list")List<Map<String, Object>> list);

		@Update(value = "<script>" +
				"	UPDATE T_DEVICE" +
				"	set device_type = #{deviceType}," +
				"	device_name = #{deviceName}," +
				"	org_id = #{orgId}," +
				"	hospital_id = #{hospitalId}," +
				"	location = #{location}," +
				"	camera_direction = #{cameraDirection}," +
				"	recog_busi = #{recogBusi}," +
				"	depart_id = #{departId}," +
				"	net_Type = #{netType}," +
				"	camera_index_code = #{cameraIndexCode}," +
				"	stream_url = #{streamUrl}," +
				"	cure_item = #{cureItem}," +
				"	archive_type = #{archiveType}" +
				"	where device_code = #{deviceCode}" +
				"</script>")
		int update(Map<String, Object> params);

		@Update(value = "<script>" +
			"<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"begin\" close=\";end;\" separator=\";\">" +
			"	UPDATE T_DEVICE" +
			"	set ${item.valueColumnName} = #{item.value}" +
			"	where ${item.whereColumnName} = #{item.whereValue}" +
			"</foreach>" +
			"</script>")
		int batchUpdateDevices(List<Map<String, Object>> list);

		@Select("select * from T_DEVICE where camera_index_code = #{cameraIndexCode}")
    	DeviceDto getByCameraIndexCode(String cameraIndexCode);
		
		@SelectProvider(type = DeviceSqlProvide.class,method = "getDeviceCountByOrg")
		List<Map<String, Object>> getDeviceCountByOrg(Map<String, Object> params);

}
