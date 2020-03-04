package com.llw.hospital.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jcl.orm.tkmapper.LLwExample;
import com.llw.common.redis.LlwRedisUtil;
import com.llw.common.util.JwtUtil;
import com.llw.hospital.api.AppUrlService;
import com.llw.hospital.api.AppVersionService;
import com.llw.hospital.api.BusinessService;
import com.llw.hospital.api.DeviceService;
import com.llw.hospital.api.MonitorService;
import com.llw.hospital.api.SysOrganizationService;
import com.llw.hospital.api.SysParamService;
import com.llw.hospital.api.SysUpmsApiService;
import com.llw.hospital.api.SysUserService;
import com.llw.hospital.api.shiro.JWTFilter;
import com.llw.hospital.api.vo.ResponseParam;
import com.llw.hospital.api.vo.login.HeartbeatRequest;
import com.llw.hospital.api.vo.login.HeartbeatResponse;
import com.llw.hospital.api.vo.login.LoginResponse;
import com.llw.hospital.api.vo.login.SysMenu;
import com.llw.hospital.api.vo.login.UserMessage;
import com.llw.hospital.common.base.constants.BaseConstants;
import com.llw.hospital.common.base.constants.CmdTypeConstants;
import com.llw.hospital.common.base.constants.DeviceTypeConstants;
import com.llw.hospital.common.util.MD5Util;
import com.llw.hospital.common.util.ParamUtil;
import com.llw.hospital.common.util.StringUtils;
import com.llw.hospital.ds.entity.Business;
import com.llw.hospital.ds.entity.LoginUser;
import com.llw.hospital.dto.AppUrlDto;
import com.llw.hospital.dto.AppVersionDto;
import com.llw.hospital.dto.BusinessDto;
import com.llw.hospital.dto.DeviceDto;
import com.llw.hospital.dto.MonitorDto;
import com.llw.hospital.dto.SysOrganizationDto;
import com.llw.hospital.dto.SysPermissionDto;
import com.llw.hospital.dto.SysUserDto;
import com.llw.hospital.util.DateUtils;

/**
 * @author wendellpeng
 * @Title: LoginController
 * @ProjectName gov-parent
 * @Description: 测试接口类
 * @date 2018/8/27 20:40
 */
@RestController
@RequestMapping("/api/public")
@Api(tags="登录相关接口列表")
public class PublicController {

    private int expireDay = 10000;
    
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysOrganizationService organizationService;

	@Autowired
	SysParamService sysParamService;
	
	@Autowired
	SysUpmsApiService upmsApiService;
	
	@Autowired
	BusinessService businessService;
	
	@Autowired
	DeviceService deviceService;
	
	@Autowired 
	MonitorService monitorService;
	@Autowired
	AppVersionService appVersionService;
	@Autowired
	AppUrlService appUrlService;
	

    public int getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(int expireDay) {
        this.expireDay = expireDay;
    }

	@PostMapping("/heartbeat")
	@ApiOperation(value = "心跳/版本检测/远程监控", notes = "心跳/版本检测/远程监控")
	@ApiResponses({ @ApiResponse(code = 200, message = "执行成功"),@ApiResponse(code = 400, message = "系统异常") })
	public ResponseParam<HeartbeatResponse> heartbeat(@RequestBody @ApiParam HeartbeatRequest request) {
		HeartbeatResponse response = new HeartbeatResponse();
		response.setCmdType(CmdTypeConstants.heartbeat.getCode());
		response.setMessage("心跳正常");
		//申请授权码
		if((DeviceTypeConstants.JXB309.getCode().equals(request.getDevType()) || DeviceTypeConstants.F120.getCode().equals(request.getDevType())) && CmdTypeConstants.authUpdate.getCode().equals(request.getCmdType())){
			JSONObject mm = new JSONObject();
			int expireDay = 15; //有效时间15天
			Calendar calendar = Calendar.getInstance();
			calendar.add(5, expireDay);
			mm.put("auth", JwtUtil.getToken("2", expireDay));
			mm.put("expireDay",calendar.getTime().getTime());
			response.setExtraParam(mm);
			response.setCmdType(CmdTypeConstants.authUpdate.getCode());
			response.setMessage("授权码更新");
			
		//心跳 
		}else if(CmdTypeConstants.heartbeat.getCode().equals(request.getCmdType())){
			
			//判断是否要升级
			JSONObject versonInfo = new JSONObject();
			response.setExtraParam(versonInfo);
			AppVersionDto ver = new AppVersionDto();
			ver.setDeviceType(request.getDevType());
			ver.setLastest(1);
			List<AppUrlDto> urlList = null;
			AppVersionDto lastVer = appVersionService.selectOne(ver);
			if(lastVer!=null && lastVer.getVersion().compareTo(request.getVersion())>0){
				AppUrlDto url = new AppUrlDto();
				url.setVersionId(lastVer.getVersionId());
				urlList = appUrlService.selectList(url); 
				if(urlList!=null && urlList.size()>0){
					response.setMessage("需要升级");
					response.setCmdType(CmdTypeConstants.upgrade.getCode());
				}
			}
			if(lastVer==null || urlList==null){
				lastVer = new AppVersionDto();
				lastVer.setVersion(request.getVersion());
				lastVer.setDeviceType(request.getDevType());
				urlList = new ArrayList<AppUrlDto>();
				response.setMessage("无需升级");
			}
			versonInfo.put("version", lastVer);
			versonInfo.put("versionUrl", urlList);
			

			//往t_monitor插入一条数据 ，每日1条
			LLwExample lLwExample = new LLwExample();
			String YYYYMMDD= DateUtils.getYYYYMMDD(new Date());
			Date beginDate = DateUtils.string2Date(YYYYMMDD+" 00:00:00","yyyyMMdd HH:mm:ss");
			Date endDate = DateUtils.string2Date(YYYYMMDD+" 23:59:59","yyyyMMdd HH:mm:ss");
			lLwExample.andBetween("createTime", beginDate, endDate);
			lLwExample.andEqualTo("deviceCode", request.getDevCode());
			List<MonitorDto> monitorList = monitorService.selectByExample(lLwExample);
			if(null == monitorList || monitorList.size() == 0){
				MonitorDto monitorDto = new MonitorDto();
				monitorDto.setCreateTime(new Date());
				monitorDto.setDeviceCode(request.getDevCode());
				monitorDto.setDeviceType(request.getDevType());
				monitorService.insertSelective(monitorDto);
			}
			
			//设备上线啦
			DeviceDto device = new DeviceDto();
			device.setDeviceCode(request.getDevCode());
			device.setSoftVersion(request.getVersion());
			device.setNetStatus("1");
			device.setOnlineTime(new Date());
			deviceService.updateByPrimaryKeySelective(device);
			
	    //升级		
		}else if(CmdTypeConstants.upgrade.getCode().equals(request.getCmdType())){
		    if(request.getRunStatus()==null){
		    	request.setRunStatus(0);
		    }

	    //重启、关机		
		}else if(CmdTypeConstants.restart.getCode().equals(request.getCmdType()) || CmdTypeConstants.shutdown.getCode().equals(request.getCmdType())){
			
		}
		
//		System.err.println("\n");
//		System.err.println("=========================================================================");
//		System.err.println("请求时间：" +DateUtils.time2String(new Date()));
//		System.err.println("心跳请求：" +JSONUtils.toJSONString(request));
//		System.err.println("心跳返回：" +JSONUtils.toJSONString(response));
//		System.err.println("=========================================================================\n");
		
		response.setSysTime(System.currentTimeMillis());
		return ResponseParam.ok().setData(response);
	}
	
	
	@PostMapping("/getSysParam")
	@ApiOperation(value = "取系统参数", notes = "取系统参数")
	@ApiResponses({ @ApiResponse(code = 200, message = "执行成功"),@ApiResponse(code = 400, message = "系统异常") })
	public ResponseParam<Map<String,String>> getSysParam() {
		return ResponseParam.ok().setData(ParamUtil.getParams());
	}
	
	@GetMapping("/getUserMessage")
    @ApiOperation(value="获取登录用户相关信息（菜单、业务类型等）")
    @ApiImplicitParams({@ApiImplicitParam(name = "auth",value = "access token，可以通过token接口获取，也可以用户名和密码加mac地址生成",required = true,dataType = "String",paramType = "header")})
    @ApiResponses({ @ApiResponse(code = 200, message = "执行成功"),@ApiResponse(code = 400, message = "系统异常") })
	 public @ApiParam ResponseParam<UserMessage> getUserMessage(){
    	//LoginUser loginUser = LoginUtil.getCurrentUser();
    	LoginUser loginUser =  JWTFilter.getCurrentUser();
		List<SysPermissionDto> upmsPermissions = upmsApiService.selectPermissionByUserId(loginUser.getUserId(), BaseConstants.MODULES_APP);//权限模块 app
		List<SysMenu> menu = changePermissionToMenu(upmsPermissions,"");
		UserMessage userMessage = new UserMessage();
		userMessage.setSysMenuList(menu);

    	//根据用户拥有的业务类型权限显示可选的业务类型
    	if(!CollectionUtils.isEmpty(loginUser.getAvaliableBusiTypes()) ){
    		BusinessDto businessDto = new BusinessDto();
    		LLwExample lLwExample;
			try {
				lLwExample = new LLwExample(businessDto);
				List<BusinessDto> busiList = new ArrayList<BusinessDto>();
	    		List<String> busiTypes = loginUser.getAvaliableBusiTypes();
	    		lLwExample.andIn("busiType", busiTypes);
	    		busiList = businessService.selectByExample(lLwExample);
	    		if(!CollectionUtils.isEmpty(busiList)) {
	    			List<Business> businessList = new ArrayList<Business>();
	    			for(BusinessDto bd : busiList) {
	    				  Business business = new Business();
	    				  BeanUtils.copyProperties(bd, business);
	    				  businessList.add(business);
	    			}

	    			userMessage.setBusiTypes(businessList);
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}

		return ResponseParam.ok().setData(userMessage);
    }
	
	@GetMapping("/login")
    @ApiOperation(value="登录",notes="登录，获取Access Token")
    @ApiImplicitParams({
						@ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String",paramType = "query"),
						@ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "String",paramType = "query"),
						@ApiImplicitParam(name = "mac",value = "mac地址",required = true,dataType = "String",paramType = "query"),
						@ApiImplicitParam(name = "deviceCode",value = "设备编码",required = false,dataType = "String",paramType = "query"),
						@ApiImplicitParam(name = "deviceType",value = "设备类型(JXB309:体温枪、M320:POS终端M320、M321:POS终端M321、M323:POS终端M323、M324:POS终端M324、M330:POS终端M330、F120:双目人证通、F330:桌面式一体机、P310:POS终端P310、 P101：安卓手机、 P102：苹果手机",required = false,dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 600,message = "账号不能为空"),
            @ApiResponse(code = 601,message = "密码不能为空"),
            @ApiResponse(code = 602,message = "用户不存在"),
            @ApiResponse(code = 603,message = "密码错误"),
            @ApiResponse(code = 604,message = "帐号已锁定"),
            @ApiResponse(code = 605,message = "mac不能为空"),
            @ApiResponse(code = 606,message = "设备不存在"),
            @ApiResponse(code = 607,message = "设备所属机构绑定机构类型有误"),
            @ApiResponse(code = 608,message = "用户所属机构绑定机构类型有误"),
            @ApiResponse(code = 609,message = "设备和登录用户不在同一机构"),
            @ApiResponse(code = 200,message = "success",response = LoginResponse.class),
    })
    public ResponseParam<LoginResponse> login(String username,String password,String mac,String deviceCode,String deviceType){
        if(StringUtils.isBlank(username)){
            return ResponseParam.error("600","账号不能为空");
        }
        if(StringUtils.isBlank(password)){
            return ResponseParam.error("601","密码不能为空");
        }

        if(StringUtils.isBlank(mac)){
            return ResponseParam.error("605","mac不能为空");
        }
       
        SysUserDto sysUserDtoReq = new SysUserDto();
        sysUserDtoReq.setEnabled(1);
        sysUserDtoReq.setUsername(username);
        SysUserDto user = sysUserService.selectOne(sysUserDtoReq);
      
        
        if(null == user){
            return ResponseParam.error("602","用户不存在");
        }else{
          SysOrganizationDto sysOrganizationDto = organizationService.selectByPrimaryKey(user.getOrgId());
          if(sysOrganizationDto.getOrgCategory() != BaseConstants.ORG_CATEGORY_INSTITUTION ) {
        	  Long userOrgId = user.getOrgId();
              Long userHospitalOrgId = getOrgId(userOrgId);//获取登录用户所属医院的orgId
              if(!"P101".equals(deviceType) && !"P102".equals(deviceType)) {
            	  DeviceDto dd = new DeviceDto();
                  dd.setDeviceCode(deviceCode);
                  dd.setDeviceType(deviceType);
                  dd = deviceService.selectOne(dd);
                  if(dd == null) {
                  	 return ResponseParam.error("606","设备不存在");
                  }
                  Long deviceOrgId = dd.getOrgId();//设备可能绑定在科室 也可能是医院
                  Long deviceHospitalOrgId = getOrgId(deviceOrgId);//获取设备所属医院的orgId
              	  if(deviceHospitalOrgId == null) {
          			return ResponseParam.error("607","设备所属机构绑定机构类型有误");
          		  }
              	
                  if(userHospitalOrgId == null) {
                  	return ResponseParam.error("608","用户所属机构绑定机构类型有误");
                  }
                  
                  if(!deviceHospitalOrgId.equals(userHospitalOrgId)) {
                  	return ResponseParam.error("609","设备和登录用户不在同一机构");
                  }
              }
          }
         
           password = MD5Util.MD5(password + user.getSalt());
            //密码校验
            try {
            	if(!password.equals(user.getPassword())) {
            		 return ResponseParam.error("603","密码错误");
            	}
            } catch (IncorrectCredentialsException e) {
                return ResponseParam.error("603","密码错误！");
            } catch (LockedAccountException e) {
                return ResponseParam.error("604","帐号已锁定！");
            }
            
            LoginUser loginUser = getLoginUser(user);
            //getExpireDay()
            //缓存当前登录的用户到redis
            LlwRedisUtil.set(("userCache:"+user.getUserId()).getBytes(), SerializationUtils.serialize(loginUser));
			JWTFilter.setCurrentUser(loginUser);
            LoginResponse response = new LoginResponse();
            
           
            response.setOrganizationName(sysOrganizationDto.getName());
            response.setToken(JwtUtil.getToken(String.valueOf(user.getUserId()) +":"+ mac,expireDay));
            response.setEffectiveTime(expireDay);
            response.setName(user.getName());
          //  response.setUserType(user.getUserType());
            response.setLatitude(28.2348);
            response.setLongitude(112.8866);
            return ResponseParam.ok().setData(response);
        }
    }


    
    @GetMapping("/changeUserPassword")
    @ApiOperation(value="修改密码")
    @ApiImplicitParams({@ApiImplicitParam(name = "auth",value = "access token，可以通过token接口获取，也可以用户名和密码加mac地址生成",required = true,dataType = "String",paramType = "header"),
    @ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String",paramType = "query"),
	@ApiImplicitParam(name = "oldPassword",value = "原始密码",required = true,dataType = "String",paramType = "query"),
	@ApiImplicitParam(name = "newPassword",value = "新密码",required = true,dataType = "String",paramType = "query")})
    @ApiResponses({
        @ApiResponse(code = 600,message = "账号不能为空"),
        @ApiResponse(code = 602,message = "用户不存在"),
        @ApiResponse(code = 604,message = "帐号已锁定"),
        @ApiResponse(code = 607,message = "原密码不能为空"),
        @ApiResponse(code = 608,message = "原密码错误"),
        @ApiResponse(code = 609,message = "新密码不能为空"),
        @ApiResponse(code = 610,message = "用户已退出登录"),
        @ApiResponse(code = 200,message = "success"),
    })
    public @ApiParam ResponseParam changePassword(String username,String oldPassword,String newPassword){
    	  if(StringUtils.isBlank(username)){
              return ResponseParam.error("600","账号不能为空");
          }
          if(StringUtils.isBlank(oldPassword)){
              return ResponseParam.error("607","原密码不能为空");
          }

          if(StringUtils.isBlank(newPassword)){
              return ResponseParam.error("609","新密码不能为空");
          }

          SysUserDto sysUserDtoReq = new SysUserDto();
          sysUserDtoReq.setUsername(username);
          SysUserDto sysUserDto = sysUserService.selectOne(sysUserDtoReq);
          if(sysUserDto==null){
              return ResponseParam.error("602","用户不存在");
          }else {
        	    oldPassword = MD5Util.MD5(oldPassword + sysUserDto.getSalt());
              	newPassword=MD5Util.MD5(newPassword + sysUserDto.getSalt());
                  //密码校验
                  try {
                  	if(!oldPassword.equals(sysUserDto.getPassword())) {
                  		 return ResponseParam.error("608","原密码错误");
                  	}else {
                  		 sysUserDto.setPassword(newPassword);
                         sysUserService.updateByPrimaryKey(sysUserDto);
                         return ResponseParam.ok("修改成功");
                  	}
                  } catch (IncorrectCredentialsException e) {
                      return ResponseParam.error("608","原密码错误！");
                  } catch (LockedAccountException e) {
                      return ResponseParam.error("604","帐号已锁定！");
                  }
          }
    }
    
/*    @PostMapping("/outLogin")
    @ApiOperation(value="退出登录")
    @ApiImplicitParams(@ApiImplicitParam(name = "auth",value = "access token，可以通过token接口获取，也可以用appKey和appSecret生成",required = true,dataType = "String",paramType = "header"))
    @ApiResponses({
        @ApiResponse(code = 400,message = "账号不能为空"),
        @ApiResponse(code = 401,message = "原密码不能为空"),
        @ApiResponse(code = 402,message = "用户不存在"),
        @ApiResponse(code = 403,message = "密码错误"),
        @ApiResponse(code = 404,message = "帐号已锁定"),
        @ApiResponse(code = 405,message = "新密码不能为空"),
        @ApiResponse(code = 200,message = "success",response = LoginResponse.class),
    })
    public ResponseParam<LoginResponse> outLogin(@RequestBody @ApiParam LoginRequest request){
    	return ResponseParam.ok();
    }*/

    private List<SysMenu> changePermissionToMenu(List<SysPermissionDto> upmsPermissions, String contextPath) {
		List<SysMenu> entityList = new ArrayList<>(upmsPermissions.size());
		for(SysPermissionDto permission:upmsPermissions){
			SysMenu entity = new SysMenu();
			entity.setIcon(permission.getIcon());
			//entity.setIconIos(permission.getIconIos());
			entity.setMenuId(permission.getPermissionId().longValue());
			entity.setName(permission.getName());
			entity.setParentId(permission.getPid().longValue());
			entity.setPermissionValue(permission.getPermissionValue());
			if(permission.getType() == 2 && StringUtils.isNotBlank(permission.getUri())){ //菜单类型
				/*if(permission.getUri().startsWith("/")){
					entity.setUrl(contextPath+permission.getUri());
				}else{
					entity.setUrl(contextPath+"/"+permission.getUri());
				}*/
				entity.setUrl(permission.getUri());
			}
			entity.setOrderNum(permission.getOrders().intValue());
			entityList.add(entity);
		}

		//获取所有的顶级菜单 暂时支持两级菜单
		List<SysMenu> topMenu =  getMenuListByParentId(entityList,0L);
		for(SysMenu entity:topMenu){
			List<SysMenu> children = getMenuListByParentId(entityList,entity.getMenuId());
			entity.setList(children);
		}

		return topMenu;
	}
    
    
    
    private List<SysMenu> getMenuListByParentId(List<SysMenu> entityList, Long parentId) {
		List<SysMenu> children = new ArrayList<>(2);
		for(SysMenu menu:entityList){
			if(menu.getParentId().equals(parentId)){
				children.add(menu);
			}
		}
		return children;
	}
    
    public Long getOrgId(Long orgId) {
		SysOrganizationDto so = organizationService.selectByPrimaryKey(orgId);
		if(so != null && so.getOrgCategory() != null) {
			if(so.getOrgCategory() == 2) {//科室
				Long id = so.getPid();
				return getOrgId(id);
			}else if(so.getOrgCategory() == 1) {//医院
				return so.getOrgId();
			}else {
				return null;
			}
		}else {
			return null;
		}
	
	}
    
    
    private LoginUser getLoginUser(SysUserDto user) {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user,loginUser);
        loginUser.setPassword(null);

        //把当前机构的数据放到缓存，后续操作的权限控制需要用到
       // OrgSysDto orgSysDto = new OrgSysDto();
        //orgSysDto.setOrgId(user.getOrgId());
        //List<OrgSysDto> list = orgSysService.selectList(orgSysDto);
//        List<String> busiTypes = new ArrayList<>();
//        List<Integer> sysCodes = new ArrayList<>();
//        if(!CollectionUtils.isEmpty(list)){
//            list.forEach(item->{
//                if(null != item.getSysCode()){
//                    sysCodes.add(item.getSysCode());
//                }
//                if(!StringUtils.isEmpty(item.getBusiTypes())){
//                    String[] busiTypeStr = item.getBusiTypes().split(",");
//                    for(String type:busiTypeStr){
//                        busiTypes.add(type);
//                    }
//                }
//            });
//        }

//        if(!StringUtils.isEmpty(loginUser.getBusiTypes())){
//            List<String> userBusiTypes = new ArrayList<>();
//            String[] busiTypeStr = loginUser.getBusiTypes().split(",");
//            for(String type:busiTypeStr){
//                userBusiTypes.add(type);
//            }
//            loginUser.setAvaliableBusiTypes(userBusiTypes);
//        }else{
//            loginUser.setAvaliableBusiTypes(busiTypes);
//        }

//        if(!StringUtils.isEmpty(loginUser.getSysCodes())){
//            String[] sysCodeStr = loginUser.getSysCodes().split(",");
//            List<Integer> userSysCodes = new ArrayList<>();
//            for(String code:sysCodeStr){
//                userSysCodes.add(Integer.parseInt(code));
//            }
//            loginUser.setAvaliableSysCodes(userSysCodes);
//        }else{
//            loginUser.setAvaliableSysCodes(sysCodes);
//        }

        SysOrganizationDto sysOrganizationDto = organizationService.selectByPrimaryKey(loginUser.getOrgId());
//        if(!StringUtils.isEmpty(loginUser.getZoneCodes())){
//            loginUser.setAvaliableZoneCode(loginUser.getZoneCodes());
//        }else{
//            loginUser.setAvaliableZoneCode(sysOrganizationDto.getZoneCode());
//        }

        loginUser.setOrgCategory(sysOrganizationDto.getOrgCategory());

        return loginUser;
    }
    
}
