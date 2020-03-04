package com.llw.hospital.api;

import java.util.Date;

/**
 * @author wendellpeng
 * @Title: CurrentTimeService
 * @ProjectName gov-parent
 * @Description: 提供从数据库获取时间的机制
 * @date 2018/8/29 14:54
 */
public interface SysCurrentTimeService {
    Date getCurrentTime();
}
