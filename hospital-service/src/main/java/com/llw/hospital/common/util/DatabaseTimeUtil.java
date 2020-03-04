package com.llw.hospital.common.util;

import com.llw.hospital.api.SysCurrentTimeService;

import java.util.Date;

public class DatabaseTimeUtil {

	private static SysCurrentTimeService currentTimeService;
	
	public static void setCurrentTimeService(SysCurrentTimeService currentTimeService) {
		DatabaseTimeUtil.currentTimeService = currentTimeService;
	}
	
	public static SysCurrentTimeService getCurrentTimeService() {
		return currentTimeService;
	}

	public static Date getCurrentTime(){
		return currentTimeService.getCurrentTime();
	}
}
