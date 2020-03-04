package com.llw.hospital.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jcl.common.base.BaseController;
import com.jcl.dto.BaseResp;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.common.redis.CommonRedisTemplate;
import com.llw.hospital.api.DeviceService;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.common.util.DictUtil;
import com.llw.hospital.dto.DeviceDto;
import com.llw.hospital.dto.DeviceExtendDto;
import com.llw.hospital.dto.SysOrganizationDto;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.util.*;

/**
 * 设备管理表
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-04-30 14:09:28
 */
@RestController
@RequestMapping("/medical/device")
public class DeviceController extends BaseController {
	public static final String ONLINE_DEVICE = "online_device";
	
	@Autowired
	private DeviceService deviceService;
	//@Autowired
	//CommonRedisTemplate redisTemplate;
	@Autowired
	SysOrganizationService sysOrganizationService;
	
	public static void main(String[] args) {
		System.err.println(System.getProperty("os.name"));
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list.jspx")
	@RequiresPermissions("medical:device:list")
	public BaseResp list(DeviceExtendDto deviceDto,
			@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			PageInfo pageInfo = deviceService.getDeviceDtoList(deviceDto,pageNo, pageSize);
			List<DeviceExtendDto> list = pageInfo.getList();
			if (null != list && list.size() > 0) {
				List retList = new ArrayList();
				for (DeviceDto dto : list) {
					Map map = BeanUtils.describe(dto);
					if (null != dto.getDepartId()) {// 查询科室名称
						SysOrganizationDto organizationDto = sysOrganizationService
								.selectByPrimaryKey(dto.getDepartId());
						if (null != organizationDto) {
							map.put("departName", organizationDto.getName());
						}
					}
					
//					if (null != dto.getServerId()) {
//						ServerDto serverDto = serverService.selectByPrimaryKey(dto.getServerId());
//						if (null != serverDto) {
//							map.put("serverName", serverDto.getServerName());
//						}
//					}
					map.put("deviceStatusLabel", DictUtil.getDictLabel("deviceStatus",ObjectUtils.toString(dto.getDeviceStatus())));
					map.put("deviceTypeLabel",DictUtil.getDictLabel("deviceType",ObjectUtils.toString(dto.getDeviceType())));
					map.put("netStatusLabel",DictUtil.getDictLabel("netStatus",ObjectUtils.toString(dto.getNetStatus())));
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
	 * 信息
	 */
	@RequestMapping("/info.jspx")
	@RequiresPermissions("medical:device:info")
	public BaseResp info(String deviceCode) {
		try {
			DeviceExtendDto dto = deviceService.getDeviceExtendDto(deviceCode);
			if (null != dto.getDepartId()) {// 查询科室名称
				SysOrganizationDto organizationDto = sysOrganizationService
						.selectByPrimaryKey(dto.getDepartId());
				if (null != organizationDto) {
					dto.setDepartName(organizationDto.getName());
				}
			}
			return BaseResp.success(dto);
		} catch (Exception ex) {
			logger.error("", ex);
			return BaseResp.error(ex);
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save.jspx")
	@RequiresPermissions("medical:device:save")
	public BaseResp save(DeviceDto deviceDto) {
		try {
			deviceDto.setCreateTime(new Date());
			deviceDto.setNetStatus("2");//默认离线
			//deviceDto.setZoneCode("430100");// 测试用，要 改成页面传入
			int rows = deviceService.insertDevice(deviceDto);
			if (rows > 0 && rows != 1102) {
				/*if(deviceDto.getDeviceType().equals(DeviceTypeConstants.HKXJ.getCode())||deviceDto.getDeviceType().equals(DeviceTypeConstants.FPGA.getCode())){
					this.issue(deviceDto);
				}*/
				return BaseResp.success(deviceDto);
			}if (rows == 1102) {
				return BaseResp.error(1102, "设备编码已存在");
			} else {
				return BaseResp.error(500, "记录保存失败");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			return BaseResp.error(ex);
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update.jspx")
	@RequiresPermissions("medical:device:update")
	public BaseResp update(DeviceDto deviceDto) {
		try {
			int rows = deviceService.update(deviceDto);
			if (rows > 0) {
				/*if(deviceDto.getDeviceType().equals(DeviceTypeConstants.HKXJ.getCode())||deviceDto.getDeviceType().equals(DeviceTypeConstants.FPGA.getCode())){
					this.issue(deviceDto);
				}*/
				return BaseResp.success(deviceDto);
			} else {
				return BaseResp.error(500, "记录修改失败");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			return BaseResp.error(ex);
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete.jspx")
	@RequiresPermissions("medical:device:delete")
	public BaseResp delete(String pk) {
		try {
//			if(StringUtils.isNotBlank(pk)){
//				WorksheedetailDto worksheedetailDto = new WorksheedetailDto();
//				worksheedetailDto.setDeviceCode(pk);
//				worksheedetailService.delete(worksheedetailDto);//删除工单
//			}
			int rows = deviceService.deleteByPrimaryKey(pk);
			if (rows > 0) {
				return BaseResp.success(true);
			} else {
				return BaseResp.error(500, "记录删除失败");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			return BaseResp.error(ex);
		}
	}

	
	public static String getPercentFormat(double d,int IntegerDigits,int FractionDigits){
		  NumberFormat nf = java.text.NumberFormat.getPercentInstance(); 
		  nf.setMaximumIntegerDigits(IntegerDigits);//小数点前保留几位
		  nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
		  String str = nf.format(d);
		  return str;
	}
	

	/**
	 * 根据orgId查询设备列表
	 * 
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/listByOrgId.jspx")
	// @RequiresPermissions("medical:device:listByOrgId")
	public BaseResp listByOrgId(Long orgId) {
		try {
			DeviceDto dto = new DeviceDto();
			dto.setOrgId(orgId);
			List<DeviceDto> list = deviceService.selectList(dto);
			return BaseResp.success(list);
		} catch (Exception ex) {
			logger.error("", ex);
			return BaseResp.error(ex);
		}
	}

	/**
	 * 调用地市接口下发数据
	 * 
	 * @param deviceDto
	 * @return
	 * @throws Exception
	 */
	public int issue(DeviceDto deviceDto) throws Exception {
//		Map<String, String> header = new HashMap<>(1);
//		header.put("auth", JwtUtil.getToken("super", 10000));
//		Map mm = new HashMap();
//		mm.put("deviceCode", deviceDto.getDeviceCode());
//		deviceDto.setZoneCode("430100");// 测试用，要 改成页面传入
//		String receiveDataUrl = zoneService.selectByPrimaryKey(deviceDto.getZoneCode()).getReceiveDataUrl();
//		HttpResponse response = HttpUtils.postWithHeader(receiveDataUrl+ "/api/camera/del", JSONUtils.toJSONString(mm), header);
//		int httpCode = response.getStatusLine().getStatusCode();
//
//		// 添加设备
//		int p = deviceDto.getIpAddress().indexOf(":");
//		String ip = deviceDto.getIpAddress().substring(0, p);
//		String port = deviceDto.getIpAddress().substring(p+1, deviceDto.getIpAddress().length()).trim();
//		mm = new HashMap();
//		mm.put("ip", ip);
//		mm.put("port", port);
//		mm.put("username", deviceDto.getUserName());
//		mm.put("password", deviceDto.getUserPwd());
//		mm.put("deviceType", deviceDto.getDeviceType());
//		mm.put("deviceCode", deviceDto.getDeviceCode());
//		response = HttpUtils.postWithHeader(receiveDataUrl + "/api/camera/add",JSONUtils.toJSONString(mm), header);
//		httpCode = response.getStatusLine().getStatusCode();
//		return httpCode;
		
		return 1;
	}
	
	/**
	 * 分配
	 * @param checkData
	 * @param serverId
	 * @return
	 */
	@RequestMapping("/allocation.jspx")
	@RequiresPermissions("medical:device:allocation")
	public BaseResp allocation(String checkData,Long serverId) {
		try {
			JSONArray jsonArray = JSONArray.parseArray(checkData);
			int rows = 0;
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String deviceCode = (String) jsonObject.get("deviceCode");
				DeviceDto deviceDto = deviceService.selectByPrimaryKey(deviceCode);
				deviceDto.setServerId(serverId);
				rows = deviceService.updateByPrimaryKeySelective(deviceDto);
			}
			if (rows > 0) {
				return BaseResp.success(true);
			} else {
				return BaseResp.error(500, "分配失败");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			return BaseResp.error(ex);
		}
	}

	/**
	 * 取消分配
	 * @param checkData
	 * @param serverId
	 * @return
	 */
	@RequestMapping("/cancelAllocation.jspx")
	@RequiresPermissions("medical:device:cancelAllocation")
	public BaseResp cancelAllocation(String checkData,Long serverId) {
		try {
			JSONArray jsonArray = JSONArray.parseArray(checkData);
			int rows = 0;
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String deviceCode = (String) jsonObject.get("deviceCode");
				DeviceDto deviceDto = deviceService.selectByPrimaryKey(deviceCode);
				deviceDto.setServerId(null);
				rows = deviceService.updateByPrimaryKey(deviceDto);
			}
			if (rows > 0) {
				return BaseResp.success(true);
			} else {
				return BaseResp.error(500, "分配失败");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			return BaseResp.error(ex);
		}
	}

	
//	private JSONObject getDeviceSDKAddress(String deviceCode) {
//		String str = (String) redisTemplate.getRedisTemplate().opsForHash().get(ONLINE_DEVICE,deviceCode);
//		if (str != null) {
//			JSONObject jsonObject = new JSONObject();
//			String[] t = str.split(";");
//			if(t.length > 2){
//				String encodedHostName = t[0];
//				String ip = t[1];
//				String host = t[1];
//				if("0.0.0.0".equals(ip)){
//					host = Base64.decodeToString(encodedHostName);
//				}
//				jsonObject.put("host",host);
//				jsonObject.put("port",Integer.parseInt(t[2]));
//				return jsonObject ;
//			}
//		}
//		return null;
//	}
}
