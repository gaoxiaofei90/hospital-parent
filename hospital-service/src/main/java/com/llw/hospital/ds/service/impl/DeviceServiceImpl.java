package com.llw.hospital.ds.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendServiceImpl;
import com.llw.cache.CacheClient;
import com.llw.hospital.api.DeviceService;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.ds.entity.Device;
import com.llw.hospital.ds.mapper.DeviceMapper;
import com.llw.hospital.dto.DeviceDto;
import com.llw.hospital.dto.DeviceExtendDto;

@Component
@Service(timeout = 3000)
public class DeviceServiceImpl extends
		BaseDtoExtendServiceImpl<DeviceDto, Device> implements DeviceService {

	private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	@Autowired
	DeviceMapper deviceMapper;
	

	@Override
	public PageInfo getDeviceDtoList(DeviceExtendDto deviceExtendDto,
			Integer pageNo, Integer pageSize) {
		Map<String, Object> params = new HashMap<>();
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		params.put("deviceStatus", deviceExtendDto.getDeviceStatus());
		params.put("deviceType", deviceExtendDto.getDeviceType());
		params.put("zoneCode", deviceExtendDto.getZoneCode());
		params.put("keyword", deviceExtendDto.getKeyword());
		params.put("netStatus", deviceExtendDto.getNetStatus());
		params.put("fromType", deviceExtendDto.getFromType());
		params.put("hospitalId", deviceExtendDto.getHospitalId());
		PageHelper.startPage(pageNo, pageSize);
		List<DeviceExtendDto> list = deviceMapper.getDeviceList(params);
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public DeviceExtendDto getDeviceExtendDto(String pk) {
		return deviceMapper.getDevice(pk);
	}

	@Override
	public int insertDevice(DeviceDto deviceDto) {
		DeviceDto where = new DeviceDto();
		where.setDeviceCode(deviceDto.getDeviceCode());
		List<DeviceDto> dsList = this.selectList(where);
		int rows = 0;
		if (dsList == null || dsList.size() == 0) {
			rows = this.insert(deviceDto);
		} else {
			rows = 1102; // 设备编码已存在！
		}
		return rows;
	}

	
	@Override
	public Long getOrgId(String deviceCode, String mac) {
		Long orgId = -1L;
		DeviceDto device = null;
		Map<String,DeviceDto> deviceCache = (Map<String, DeviceDto>) CacheClient.getCachedObject(CacheConstants.DEVICE_CACHE);
		if(null != deviceCache){
			device = deviceCache.get(deviceCode);
		}
		if(null == device){
			device = this.selectByPrimaryKey(deviceCode);
		}
		if (device == null) {
			mac = StringUtils.isBlank(mac)?deviceCode:mac;
			DeviceDto where = new DeviceDto();
			where.setMacAddress(mac);
			device = this.selectOne(where);
		}
		//orgId = device == null ? -1L : device.getHospitalId();
		orgId = device == null ? -1L : device.getOrgId();//20200114
		return orgId;
	}

	@Override
	public Set<Integer> getN1BusiType(String deviceCode, String mac) {
		// FIXME: 2019/7/8 根据设备类型获取1比n方式 默认All
		Set<Integer> set = new HashSet<>(1);
		//set.add(BaseConstants.BusiType.ALL);
		return set;
	}

	@Override
	public DeviceDto getByCameraIndexCode(String cameraIndexCode) {
		return ((DeviceMapper) deviceMapper).getByCameraIndexCode(cameraIndexCode);
	}


	@Override
	public int changeStatus(String deviceCode, String status) {
		DeviceDto dto = this.selectByPrimaryKey(deviceCode);
		if (dto != null) {
			dto.setNetStatus(status);
			if("1".equals(status)){
				dto.setOnlineTime(new Date());
			}
			return this.updateByPrimaryKey(dto);
		}
		return 0;
	}

	@Override
	public void batchUpdateOnlineTime(List<String> devices, List<Date> dateList) {
		List<Map<String,Object>> list = new ArrayList(devices.size());
		for(int i = 0; i < devices.size(); i++){
			Map<String,Object> map = new HashMap<>();
			map.put("deviceCode",devices.get(i));
			map.put("date",dateList.get(i));
			list.add(map);
		}

//		ChangeMinitorInterceptor.getSkipNextSql().set(true);
//		if(!CollectionUtils.isEmpty(list)){
//			((DeviceMapper) deviceMapper).batchUpdateOnlineTime(list);
//		}
	}

	@Override
	public void batchUpdateDevices(List<String> devices,String whereColumnName, List values,String valueColumnName) {
		List<Map<String,Object>> list = new ArrayList(devices.size());
		for(int i = 0; i < devices.size(); i++){
			Map<String,Object> map = new HashMap<>();
			map.put("valueColumnName",valueColumnName);
			map.put("whereColumnName",whereColumnName);
			map.put("whereValue",devices.get(i));
			map.put("value",values.get(i));
			list.add(map);
		}

//		ChangeMinitorInterceptor.getSkipNextSql().set(true);
//		if(list.size()  > 0){
//			((DeviceMapper) deviceMapper).batchUpdateDevices(list);
//		}
	}

	@Override
	public void batchUpdateOnlineStatus(List<String> cameraIndexCodes, List statusList) {
		List<Map<String,Object>> list = new ArrayList(cameraIndexCodes.size());
		for(int i = 0; i < cameraIndexCodes.size(); i++){
			Map<String,Object> map = new HashMap<>();
			map.put("valueColumnName","net_status");
			map.put("whereColumnName","camera_index_code");
			map.put("whereValue",cameraIndexCodes.get(i));
			map.put("value",statusList.get(i));
			list.add(map);
		}
//		ChangeMinitorInterceptor.getSkipNextSql().set(true);
//		if(list.size() > 0){
//			((DeviceMapper) deviceMapper).batchUpdateDevices(list);
//		}
	}

	@Override
	public void batchUpdateNetStatus(List<String> devices,String status) {
		List<Map<String,Object>> list = new ArrayList(devices.size());
		for(int i = 0; i < devices.size(); i++){
			Map<String,Object> map = new HashMap<>();
			map.put("valueColumnName","net_status");
			map.put("whereColumnName","device_code");
			map.put("whereValue",devices.get(i));
			map.put("value",status);
			list.add(map);
		}
//		ChangeMinitorInterceptor.getSkipNextSql().set(true);
//		if(list.size() > 0){
//			((DeviceMapper) deviceMapper).batchUpdateDevices(list);
//		}
	}

	@Override
	public int getDeviceCount(Long serverId, String zoneCode) {
		return deviceMapper.getDeviceCount(serverId, zoneCode);
	}

	@Override
	public List<DeviceExtendDto> getMapData() {
		return deviceMapper.getMapData();
	}

	@Override
	public int update(DeviceDto deviceDto) {
/*		Map<String, Object> params = new HashMap<>();
		params.put("deviceType", deviceDto.getDeviceType());
		params.put("orgId", deviceDto.getOrgId());
		params.put("hospitalId", deviceDto.getHospitalId());
		params.put("location", deviceDto.getLocation());
		params.put("cameraDirection", deviceDto.getCameraDirection());
		params.put("recogBusi", deviceDto.getRecogBusi());
		params.put("departId", deviceDto.getDepartId());
		params.put("deviceCode", deviceDto.getDeviceCode());
		params.put("deviceName", deviceDto.getDeviceName());
		params.put("netType", deviceDto.getNetType());*/
		Map params = null;
		try {
			params = BeanUtils.describe(deviceDto);
			return ((DeviceMapper) deviceMapper).update(params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List getRecentPicture(String deviceCode, int i,Date startTime,Date endTime) {
		//return shardingService.getRecentPicture(deviceCode,i,startTime,endTime);
		return null;

	}

	@Override
	public String getPicUrl(Long picId) {
		//return shardingService.getPicUrl(picId);
		return "";
	}

	@Override
	public List<Map<String, Object>> getDeviceCountByOrg(Long beginOrgId,Long endOrgId) {
		Map<String, Object> params = new HashMap<>();
		params.put("beginOrgId", beginOrgId);
		params.put("endOrgId", endOrgId);
		return deviceMapper.getDeviceCountByOrg(params);
	}

	@Override
	public boolean isCamera(String deviceType) {
		return null != deviceType && deviceType.startsWith("CF3");
	}
	
}
