package com.llw.hospital.bs.controller;

import com.jcl.common.base.BaseController;
import com.llw.cache.CacheClient;
import com.llw.hospital.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 后台controller
 */
@Controller
@RequestMapping("/cache")
public class SysCacheController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(SysCacheController.class);

	@RequestMapping(value = "/refresh")
	public 	@ResponseBody String refresh(HttpServletRequest request, @RequestParam String cacheName,String orgId) {
		if(StringUtils.isEmpty(orgId)){
			CacheClient.refresh(cacheName);
		}else{
			CacheClient.refresh(cacheName+"?orgId="+orgId);
		}
		return "success";
	}

	@RequestMapping(value = "/get")
	public 	@ResponseBody Object get(HttpServletRequest request, @RequestParam String cacheName,String orgId) {
		if(!StringUtils.isEmpty(orgId)){
			return ((Map<String,Object>)CacheClient.getCachedObject(cacheName)).get(orgId);
		}else{
			return CacheClient.getCachedObject(cacheName);
		}
	}
}