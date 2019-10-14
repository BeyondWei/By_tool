package com.sztech.szcloud.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <li>日期工具类, 继承org.apache.commons.lang.time.DateUtils类</li>
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {


    /**
     * <li>参数数组</li><br/>
     * 0:yyyyMMdd<br/>1:yyyy-MM-dd<br/>2:yyyy-MM-dd HH:mm<br/>3:yyyy-MM-dd HH:mm:ss<br/>4:yyyy/MM/dd<br/>
     * 5:yyyy/MM/dd HH:mm<br/>6:yyyy/MM/dd HH:mm:ss<br/>7:HH:mm:ss[时间]<br/>8:yyyy[年份]<br/>9:MM[月份]<br/>
     * 10.dd[天]<br/>11.E[星期几]<br/>
     */
    public static String[] parsePatterns = {"yyyyMMdd", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "HH:mm:ss", "yyyy", "MM", "dd", "E"};

    /**
     * <li>得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"</li>
     *
     * @param pattern 可参考参数组parsePatterns
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String dateToStr(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * <li>得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"</li>
     *
     * @param date    日期类
     * @param pattern 可参考参数组parsePatterns
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, parsePatterns[1]);
        }
        return formatDate;
    }

    /**
     * <li>日期型字符串转化为日期 格式</li>
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * <li>获取过去的天数</li>
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * <li>获取输入日期开始时间</li>
     *
     * @param date
     * @return
     */
    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * <li>获取输入日期结束时间</li>
     *
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date addHours(Date recordTime, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(recordTime);
        c.add(Calendar.HOUR_OF_DAY, hours);
        return c.getTime();
    }

    /**
     * <li>按输入日期加分钟</li>
     *
     * @param recordPeriodMinute 分钟数
     * @return
     */
    public static Date addMinutes(Date recordTime, int recordPeriodMinute) {
        Calendar c = Calendar.getInstance();
        c.setTime(recordTime);
        c.add(Calendar.MINUTE, recordPeriodMinute);
        return c.getTime();
    }

    /**
     * <li>按输入日期加毫秒</li>
     *
     * @param recordPeriodSecond 毫秒数
     * @return
     */
    public static Date addSeconds(Date recordTime, int recordPeriodSecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(recordTime);
        c.add(Calendar.SECOND, recordPeriodSecond);
        return c.getTime();
    }

    /**
     * <li>校验日期是否合法</li>
     *
     * @param date 日期
     * @return
     */
    public static boolean isValidDate(String date) {
        DateFormat fmt = new SimpleDateFormat(parsePatterns[1]);
        try {
            fmt.parse(date);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * <li>时间相减得到相差年数</li>
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat(parsePatterns[1]);
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数</li>
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static long getDaySub(String startTime, String endTime) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(parsePatterns[1]);
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(startTime);
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    public static long getDaySub(Date startTime, Date endTime) {
        long day = 0;
        day = (endTime.getTime() - startTime.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * <li>得到n天之后的日期</li>
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(int days, String pattern) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdfd = new SimpleDateFormat(pattern);
        String dateStr = sdfd.format(date);
        return dateStr;
    }

    public static Date getAfterDayDate(Date time, int days) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(time);
        canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        return date;
    }

    /**
     * <li>得到n天之后是周几</li>
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns[11]);
        String dateStr = sdf.format(date);
        return dateStr;
    }


    public static Integer getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    /**
     * 把时间根据时、分、秒转换为时间段
     *
     * @author String ？日前或？小时前或？分钟前？秒前
     */
    public static String getTimes(Object str) {
        String resultTimes = "";
        Date now;
        Date date = parseDate(str);
        now = new Date();
        long times = now.getTime() - date.getTime();
        long day = times / (24 * 60 * 60 * 1000);
        long hour = (times / (60 * 60 * 1000) - day * 24);
        long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "日前");
        } else if (hour > 0) {
            sb.append(hour + "小时前");
        } else if (min > 0) {
            sb.append(min + "分钟前");
        } else if (sec > 0) {
            sb.append(sec + "秒前");
        } else if (times >= 0) {
            sb.append(times + "毫秒前");
        } else {
            sb.append("超前毫秒数:" + times);
        }
        resultTimes = sb.toString();
        return resultTimes;
    }

    /**
     * 将微信消息中的CreateTime转换成标准格式的时间Date
     *
     * @param createTime 消息创建时间
     * @return
     */
    public static Date formatTime(long createTime) {
        long msgCreateTime = createTime * 1000L;
        return new Date(msgCreateTime);
    }

//	public static Date formatTimeToDate(long createTime) {
//		return new Date(createTime);
//	}

    /**
     * 获取本周的区间
     *
     * @param date
     * @return
     */
    public static Map<String, Date> getTimeInterval(Date date) {
        Map<String, Date> dateMap = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天  
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        dateMap.put("imptimeBegin", cal.getTime());
        cal.add(Calendar.DATE, 6);
        dateMap.put("imptimeEnd", cal.getTime());
        return dateMap;
    }


    /**
     * 在日期上增加数个时间
     */
    public static Date addDate(Date date, int calendar, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendar, n);
        return cal.getTime();
    }


}
