package com.llw.hospital.bs.common.util;

import com.llw.hospital.common.util.DatabaseTimeUtil;
import com.llw.hospital.common.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 时间处理工具类
 */
public class TimeUtils {

    public static String formatTime(Timestamp time, String formatter) {
        if (time != null) {
            return formatTime(time.getTime(), formatter);
        }
        return "";
    }


    /**
     * @param time
     * @param formatter <pre>
     *                                                        日期格式
     *                                                    </pre>
     * @return
     */
    public static String formatTime(long time, String formatter) {
        if (StringUtils.isNotBlank(formatter)) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatter);
            return sdf.format(new Date(time));
        }
        return "";
    }

    /**
     * 格式化时间
     *
     * @param time
     * @param formatter
     * @return
     */
    public static String formatTime(Date time, String formatter) {
        if (StringUtils.isNotBlank(formatter) && time != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatter);
            return sdf.format(time);
        }
        return "";
    }

    /**
     * 解析时间
     *
     * @param time
     * @param formatter
     * @return
     */
    public static Date parseTime(String time, String formatter) {
        if (StringUtils.isNotBlank(time) && StringUtils.isNotBlank(formatter)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(formatter);
                return sdf.parse(time);
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * 转Cron表达式
     *
     * @param date
     * @return
     */
    public static String convertCronExpression(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatTime(date.getTime(), dateFormat);
    }

    /**
     * 转Cron表达式
     *
     * @param time
     * @return
     */
    public static String convertCronExpression(long time) {
        return convertCronExpression(new Date(time));
    }


    /**
     * 计算偏移时间
     *
     * @param time
     * @param field
     * @param amount
     * @return
     */
    public static Date calcOffsetTime(Date time, int field, int amount) {
        if (time != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            calendar.add(field, amount);
            return calendar.getTime();
        }
        return time;
    }

    /**
     * 获取一天的起始时间
     *
     * @param time
     * @return
     */
    public static Date getDayStart(Date time) {
        if (time != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            return calendar.getTime();
        }
        return time;
    }


    /**
     * 获取一天结束时间
     *
     * @param time
     * @return
     */
    public static Date getDayEnd(Date time) {
        if (time != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            calendar.add(Calendar.SECOND, -1);
            return calendar.getTime();
        }
        return time;
    }

    /**
     * @param
     * @param //key为Calendar.field value为对应的值
     * @return
     */
    public static Date getDate(Date date, Map<Integer, Integer> params) {
        if (date == null) {
            date = DatabaseTimeUtil.getCurrentTimeService().getCurrentTime();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (params != null && !params.isEmpty()) {
            Integer key = null, value = null;
            for (Map.Entry<Integer, Integer> entry : params.entrySet()) {
                if (entry != null) {
                    key = entry.getKey();
                    value = entry.getValue();
                    if (key != null && value != null) {
                        calendar.set(key.intValue(), value.intValue());
                    }
                }
            }
        }
        return calendar.getTime();
    }


    /**
     * 获取某月开始的时间
     *
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date) {
        if (date == null) {
            date = DatabaseTimeUtil.getCurrentTimeService().getCurrentTime();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某月最后的时间
     *
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        date = getMonthStart(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }


    public static int getTimeRange(Date start, Date end) {
        if (start != null && end != null) {
            start = getDayStart(start);
            end = getDayStart(end);
            int range = (int) (end.getTime() / 1000 - start.getTime() / 1000) / (24 * 60 * 60);
            return range;
        }
        return 0;
    }

}
