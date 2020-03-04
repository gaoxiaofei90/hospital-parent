package com.llw.hospital.api;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.DeviceDto;
import com.llw.hospital.dto.DeviceExtendDto;
import com.llw.hospital.dto.DeviceMonitorDataDto;

/**
 * 设备管理表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-04-30 14:09:28
 */
public interface DeviceService extends BaseDtoExtendService<DeviceDto> {
	
	public int insertDevice(DeviceDto deviceDto);

	public PageInfo getDeviceDtoList(DeviceExtendDto deviceExtendDto, Integer pageNo, Integer pageSize);

	public DeviceExtendDto getDeviceExtendDto(String pk);

	public Long getOrgId(String deviceCode, String mac);

	/**
	 * 根据设备编码，获取该设备进行1比n的业务类型
	 * @param deviceCode
	 * @param mac
	 * @return
	 */
	public Set<Integer> getN1BusiType(String deviceCode, String mac);


    public DeviceDto getByCameraIndexCode(String cameraIndexCode);


    /**
     * 变更设备状态变更
     * @param deviceCode
     * @param status 设备状态 0 离线 1 在线
     * @return
     */
    public int changeStatus(String deviceCode, String status);

	public void batchUpdateOnlineStatus(List<String> cameraIndexCodes, List statusList);

    public void batchUpdateOnlineTime(List<String> devices,List<Date> dateList);

	public void batchUpdateDevices(List<String> devices,String whereColumnName, List values,String valueColumnName);

	public void batchUpdateNetStatus(List<String> devices,String status);
	public int getDeviceCount(Long serverId,String zoneCode);

    public List<DeviceExtendDto> getMapData();
    
    public int update(DeviceDto deviceDto);

    List getRecentPicture(String deviceCode, int i,Date startTime,Date endTime);

    String getPicUrl(Long picId);
    
    public List<Map<String, Object>> getDeviceCountByOrg(Long beginOrgId,Long endOrgId);


	boolean isCamera(String deviceType);
}

