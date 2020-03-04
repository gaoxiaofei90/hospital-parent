package com.llw.hospital.util;

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
    public static final SimpleDateFormat yyyyMMSdf =  new SimpleDateFormat("yyyyMM");
    public static final SimpleDateFormat yyyyMMSdf1 =  new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat yyyyMMSdf2 =  new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat yyyyMMddSdf =  new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat shortSdf =  new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
    public static  final SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat yyyyMMddHHmmssSdf =  new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    public static final SimpleDateFormat sshortSdf =  new SimpleDateFormat("MM月dd日");
    public static final SimpleDateFormat ssshortSdf =  new SimpleDateFormat("M月");
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    public static final char[] WEEK_DAY_ARR = new char[]{
        '日','一','二','三','四','五','六'
    };
    public static String formatShort(Date date){
        if(null != date){
            return shortSdf.format(date);
        }else{
            return "";
        }
    }

    public static String formatFull(Date date){
        if(null != date){
            return yyyyMMddHHmmssSdf.format(date);
        }else{
            return "";
        }
    }
    public static String getShortMonthorDay(String shortDate){
        try {
            if (null == shortDate){
                return "";
            }

            Date date = shortSdf.parse(shortDate);
            return sshortSdf.format(date);
        }
        catch (Exception e){

            return "";
        }
    }
    
    public static String getShortMonthor(String shortDate){
        try {
            if (null == shortDate){
                return "";
            }

            Date date = yyyyMMSdf2.parse(shortDate);
            return ssshortSdf.format(date);
        }
        catch (Exception e){

            return "";
        }
    }

    public static String getWeekDay(String shortDate){
        try {
            if (null == shortDate){
                return "";
            }

            Date date = shortSdf.parse(shortDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK)-1;
            return "星期"+WEEK_DAY_ARR[weekDay];
        }
        catch (Exception e){

            return "";
        }
    }

    /**
     * 返回日期是否在当前日期之后
     * @param date
     * @return 1 是 0 否
     */
    public static Integer afterNow(Date date){
        if(null != date){
            if(date.after(DatabaseTimeUtil.getCurrentTimeService().getCurrentTime()))
            {
                return 1;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

    /**
     * 返回日期是否在当前日期之前
     * @param date
     * @return 1 是 0 否
     */
    public static Integer beforeNow(Date date){
        if(null != date){
            if(date.before(DatabaseTimeUtil.getCurrentTimeService().getCurrentTime()))
            {
                return 1;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

    public static String formatDate(Date date){
        if(null != date){
            return longSdf.format(date);
        }else{
            return "";
        }
    }

    //根据生日获取年龄
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(DatabaseTimeUtil.getCurrentTimeService().getCurrentTime());// 当前时间
//            now.setTime(new Date());// 当前时间
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

    public static boolean isSameDay(Date BeginDate,Date CurDate){
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

    /**
     * 判断时间大小，忽略时分秒，只比较日期的前后
     * @param date1 日期1
     * @param date2 日期2
     * @return date1 - date2 的天数
     */
    public static int daysBetween(Date date1,Date date2){
        if (date1 != null && date2 != null) {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try {
                date2=sdf.parse(sdf.format(date2));
                date1=sdf.parse(sdf.format(date1));
                Calendar cal = Calendar.getInstance();
                cal.setTime(date2);
                long time1 = cal.getTimeInMillis();
                cal.setTime(date1);
                long time2 = cal.getTimeInMillis();
                long between_days=(time2-time1)/(1000*3600*24);

                return Integer.parseInt(String.valueOf(between_days));
            } catch (ParseException e) {
                throw new RuntimeException("日期解析失败");
            }
        }else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 判断时间大小，小时/24  ,向上取整
     * @param date1 日期1
     * @param date2 日期2
     * @return date1 - date2 的天数
     */
    public static int daysBetweenDaysByHour(Date date1,Date date2){
        double aday = 24*60*60*1000;
        return (int)Math.ceil((Math.abs(date1.getTime() - date2.getTime()))/aday);
    }

    public static int daysOfMonth(String yyyyMM){
        return daysBetween(getMonthEndTime(yyyyMM),getMonthStartTime(yyyyMM)) + 1;
    }

    public static boolean isAfterOneDay(Date endDate,Date CurDate)  {
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

    public static boolean isCurrentQuarterStartTime(Date curDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth ==1 || currentMonth ==4 || currentMonth ==7 || currentMonth ==10){
            return true;
        }else {
            return false;
        }
    }

    public static Date getMonthStartTime(String yyyyMM){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(yyyyMMSdf.parse(yyyyMM));
            //设置为1号,当前日期既为本月第一天
            c.set(Calendar.DAY_OF_MONTH, 1);
            //将小时至0
            c.set(Calendar.HOUR_OF_DAY, 0);
            //将分钟至0
            c.set(Calendar.MINUTE, 0);
            //将秒至0
            c.set(Calendar.SECOND,0);
            //将毫秒至0
            c.set(Calendar.MILLISECOND, 0);
            // 获取本月第一天的时间戳
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return c.getTime();
    }

    public static Date getMonthEndTime(String yyyyMM){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(yyyyMMSdf.parse(yyyyMM));
            //设置为当月最后一天
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            //将小时至23
            c.set(Calendar.HOUR_OF_DAY, 23);
            //将分钟至59
            c.set(Calendar.MINUTE, 59);
            //将秒至59
            c.set(Calendar.SECOND,59);
            //将毫秒至999
            c.set(Calendar.MILLISECOND, 999);
            // 获取本月第一天的时间戳
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    public static boolean isSeasonContainsCurrentTime(Date seasonStartTime,Date curDate) {
        Date start = DateUtils.getCurrentQuarterStartTime(seasonStartTime);
        Date end = DateUtils.getCurrentQuarterEndTime(seasonStartTime);
        if(null != start && null != end && null != curDate){
            return curDate.after(start) && curDate.before(end);
        }else{
            return false;
        }
    }


/**	   * 当前季度的开始时间，即2012-01-1 00:00:00	   *	   * @return	   */
public  static Date getCurrentQuarterStartTime(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int currentMonth = c.get(Calendar.MONTH) + 1;
    Date now = null;
    try {
        if (currentMonth >= 1 && currentMonth <= 3){
            c.set(Calendar.MONTH, 0);
        }
        else if (currentMonth >= 4 && currentMonth <= 6){
            c.set(Calendar.MONTH, 3);}
        else if (currentMonth >= 7 && currentMonth <= 9){
            c.set(Calendar.MONTH, 4);}
        else if (currentMonth >= 10 && currentMonth <= 12){
            c.set(Calendar.MONTH, 9);
        }
        else{
            c.set(Calendar.DATE, 1);
        }
        now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return now;
}

/**	   * 当前季度的结束时间，即2012-03-31 23:59:59	   *	   * @return
 *  */
public static  Date getCurrentQuarterEndTime(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int currentMonth = c.get(Calendar.MONTH) + 1;
    Date now = null;
    try {
        if (currentMonth >= 1 && currentMonth <= 3) {
            c.set(Calendar.MONTH, 2);
            c.set(Calendar.DATE, 31);
        } else if (currentMonth >= 4 && currentMonth <= 6) {
            c.set(Calendar.MONTH, 5);
            c.set(Calendar.DATE, 30);
        } else if (currentMonth >= 7 && currentMonth <= 9) {
            c.set(Calendar.MONTH, 8);
            c.set(Calendar.DATE, 30);
        } else if (currentMonth >= 10 && currentMonth <= 12) {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
        }
        now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return now;
}




    public static boolean isCurrentYearStartTime(Date curDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth ==1){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isHasMonthTime(Date curDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth ==2 || currentMonth ==3 || currentMonth ==5 || currentMonth ==6
           ||currentMonth ==8 || currentMonth ==9 || currentMonth ==11 || currentMonth ==12   ){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isHasYearTime(Date curDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth ==1 ){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isSameYear(Date curDate,Date hasDate) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(curDate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(hasDate);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
    }

    public static boolean isSameMonth(Date date1,Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
    }


    public static String getCurrentYYYYMM() {
        return getYYYYMM(DatabaseTimeUtil.getCurrentTimeService().getCurrentTime());
    }
    public static String getYYYYMM(Date date) {
        return yyyyMMSdf.format(date);
    }
    public static String getYYYY(Date date) {
        return yyyyMMSdf1.format(date);
    }
    public static String getYYYYMM2(Date date) {
        return yyyyMMSdf2.format(date);
    }

    public static String getYYYYMMDD(Date date) {
        return yyyyMMddSdf.format(date);
    }

    public static Date addDays(Date current, int i) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.DAY_OF_MONTH,i);
        return calendar.getTime();
    }

    public static Date addHours(Date current, int i) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.HOUR_OF_DAY,i);
        return calendar.getTime();
    }

    public static Date addMinutes(Date current, int i) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.MINUTE,i);
        return calendar.getTime();
    }

    private static Date getFeeStartDate(Date billingDate, String yyyyMM) {
        Date feeStartDate = DateUtils.getMonthStartTime(yyyyMM);
            if(billingDate.after(feeStartDate))
            {
                feeStartDate = billingDate;
            }else{
                //计费日期在之前 不做处理
            }
        return feeStartDate;
    }

    public static Date getNextDay(Date date,int n){
        String YYYYMMDD=getYYYYMMDD(date);
        try {
            Date current=yyyyMMddHHmmssSdf.parse(YYYYMMDD+" 00:00:00");
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(current);
            calendar.add(Calendar.DATE,n);
            return calendar.getTime();
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getDayBegin(Date now) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    public static Date getDayEnd(Date now) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        return calendar.getTime();
    }

    public static Date getMonthBegin(Date now) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        return calendar.getTime();
    }

    public static Date getMonthEnd(Date now) {
        Date date=getMonthBegin(now);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,1);
        calendar.add(Calendar.MILLISECOND,-1);
        return calendar.getTime();
    }

    public static String date2String(Date date) {
    	if(date==null)return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Date string2Date(String date) {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
    }
    public static Date string2Date(String date,String format) {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		return sdf.parse(date);
    	} catch (ParseException e) {
    		throw new RuntimeException(e);
    	}
    }

    public static String time2String(Date date) {
    	if(date==null)return null;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(date);
    }
    
    public static String time3String(Date date) {
    	if(date==null)return null;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	return sdf.format(date);
    }

    public static Date string2Time(String date) {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		return sdf.parse(date);
    	} catch (ParseException e) {
    		throw new RuntimeException(e);
    	}
    }
    /**
     * 当前年的开始时间
     * @return
     */
    public static Date getCurrentYearStartTime() {
    	Calendar c = Calendar.getInstance();
    	Date now = null;
    	try {
    	c.set(Calendar.MONTH, 0);
    	c.set(Calendar.DATE, 1);
    	now = shortSdf.parse(shortSdf.format(c.getTime()));
    	} catch (Exception e) {
    	e.printStackTrace();
    	}
    	return now;
    	}

    /**
     * 当前年的开始时间
     * @return
     */
    public static Date getYearStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date now = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
    	/**
    	* 当前年的结束时间
    	*
    	* @return
    	*/
    	public static Date getCurrentYearEndTime() {
    	Calendar c = Calendar.getInstance();
    	Date now = null;
    	try {
    	c.set(Calendar.MONTH, 11);
    	c.set(Calendar.DATE, 31);
    	now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
    	} catch (Exception e) {
    	e.printStackTrace();
    	}
    	return now;
    	}

    /**
     * 当前年的结束时间
     *
     * @return
     */
    public static Date getYearEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date now = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    	public static String getLastMonth(){
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    
    		Date date = new Date();
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
    		date = calendar.getTime();
    		String accDate = format.format(date);
    		return accDate;
    	}
    		
    	public static int caculateTotalTime(String startTime,String endTime) {
            SimpleDateFormat formatter =   new SimpleDateFormat( "yyyy-MM-dd");
            Date date1=null;
            Date date = null;
            Long l = 0L;
            try {
                date = formatter.parse(startTime);
                long ts = date.getTime();
                date1 =  formatter.parse(endTime);
                long ts1 = date1.getTime();
 
                l = (ts - ts1) / (1000 * 60 * 60 * 24);
 
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return l.intValue();
        }
    	
    	// 获取两个时间相差分钟数
        public static long getMinute(String time) throws ParseException {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long currentTime =System.currentTimeMillis();
            long oTime = df.parse(time).getTime();
            long diff=(currentTime-oTime)/1000/60;
    		return diff;
        }

    public static void main(String[] args) {
        System.out.println(getDayEnd(new Date()));
        ;
    }
}