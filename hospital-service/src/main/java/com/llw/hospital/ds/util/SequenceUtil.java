package com.llw.hospital.ds.util;

import com.llw.hospital.api.SysCommonQueryService;
import com.llw.hospital.common.util.DatabaseTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 自定义增长NO工具类
 * 
 * @author qinliang
 *
 */

public class SequenceUtil {
	private static SysCommonQueryService sequenceService;

	public static SysCommonQueryService getCommonQueryService() {
		return sequenceService;
	}

	public static void setCommonQueryService(SysCommonQueryService sequenceService) {
		SequenceUtil.sequenceService = sequenceService;
	}

	public static String getSeqNo(Long orgId,String startStr, Integer num) {
		String dataStr = getDataStr(null, DatabaseTimeUtil.getCurrentTimeService().getCurrentTime(), "yyyyMMdd");
		String key = startStr + dataStr;
		long flow = sequenceService.nextVal(key+"_"+orgId);
		String flowNo = String.valueOf(flow);
		while (flowNo.length() < num) {
			flowNo = "0" + flowNo;
		}
		return startStr + dataStr + flowNo;
	}

	public static String getDataStr(String zoneId, Date date, String format) {
		String result = null;
		if (date == null) {
			result = "";
		} else {
			SimpleDateFormat stdFormat = new SimpleDateFormat(format);
			if ((zoneId != null) && (!("".equals(zoneId)))) {
				stdFormat.setTimeZone(TimeZone.getTimeZone(zoneId));
			}

			result = stdFormat.format(date);
		}
		return result;
	}
}
