package com.llw.hospital.bs.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.llw.hospital.common.util.DatabaseTimeUtil;

/**
 * @program: gov-parent
 * @description:日期相关工具类
 * @author: HUHUA
 * @create: 2018-09-04 17:04
 **/
public class DateUtils {

    //根据生日获取年龄
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(DatabaseTimeUtil.getCurrentTimeService().getCurrentTime());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }

    public boolean isSameDay(Date BeginDate,Date CurDate){
        if (BeginDate != null && CurDate != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(BeginDate);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(CurDate);
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        }else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public boolean isAfterOneDay(Date endDate,Date CurDate) throws ParseException {
        if (endDate != null && CurDate != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(endDate);
            cal1.add(Calendar.DATE, 1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(CurDate);
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        }else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 定义日期字符串格式
     */
    private static class DateFromatDefine{
        private static final DateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    }

    /**
     * 获取当前日期格式为yyyyMMdd的字符串
     * @return
     */
    public static String getyyyyMMddStr(){
        return DateFromatDefine.yyyyMMdd.format(new Date());
    }
}
