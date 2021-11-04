package cn.wintersun.baseweb.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间戳获取工具类
 *
 * @author WinterSun
 * @create 2020-06-02 16:16
 */
public class DateUtil {
    /**
     * 获取今日凌晨0点的时间戳
     *
     * @return java.lang.Long
     */
    public static Long getToday(Calendar calendar) {
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        return time.getTime();
    }

    /**
     * 获取昨日凌晨零点的时间戳
     *
     * @return java.lang.Long
     */
    public static Long getYesterday(Calendar calendar) {
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date time = calendar.getTime();
        return time.getTime();
    }

    /**
     * 获取一个星期前零点的时间戳
     *
     * @return java.lang.Long
     */
    public static Long getOldWeek(Calendar calendar) {
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        Date time = calendar.getTime();
        return time.getTime();
    }

    /**
     * 获取昨日23点59分59秒的时间戳
     *
     * @return java.lang.Long
     */
    public static Long getETM(Calendar calendar) {
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MILLISECOND, -1);
        Date time = calendar.getTime();
        return time.getTime();
    }

    /**
     * 获取一个月前零点的时间戳
     *
     * @return java.lang.Long
     */
    public static Long getMonth(Calendar calendar) {
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, -1);
        calendar.add(Calendar.MONTH, -1);
        Date time = calendar.getTime();
        return time.getTime();
    }

    /**
     * 获取近一个月
     *
     * @return java.lang.Long 现在到一个月前的时间戳
     */
    public static Long getOldMonth(Calendar calendar) {
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        Date time = calendar.getTime();
        return time.getTime();
    }

    /**
     * 获取本周在本年中是第几周
     *
     * @param date 现在的时间戳
     */
    public static String getSeqWeek(Long date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTimeInMillis(date);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1) {
            week = "0" + week;
        }
        return week;
    }

    /**
     * 根据年和月获取指定月的最后一天的日期
     *
     * @param yearMonth 2020-07
     * @return java.lang.Integer
     */
    public static Integer getLastDayOfMonth(String yearMonth) {
        int year = Integer.parseInt(yearMonth.split("-")[0]);
        int month = Integer.parseInt(yearMonth.split("-")[1]);
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        // 设置当前月的上一个月
        cal.set(Calendar.MONTH, month);
        // 获取某月最大天数
        // 获取月份中的最小值，即第一天
        int lastDay = cal.getMinimum(Calendar.DATE);
        // 设置日历中月份的最大天数
        // 上月的第一天减去1就是当月的最后一天
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] split = sdf.format(cal.getTime()).split("-");
        return Integer.valueOf(split[2]);
    }

    /**
     * 时间对象格式化为时间字符串
     *
     * @param date 时间对象
     * @return 格式化后的时间，包含年，月，日，时，分，秒
     */
    public static String parseFormatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 时间对象格式化为时间字符串
     *
     * @param date 时间对象
     * @return 格式化后的时间，包含年，月，日
     */
    public static String parseFormatDateOwe(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 时间对象格式化为时间字符串
     *
     * @param date 时间对象
     * @return 格式化后的时间，格式为：yyyy年MM月dd日
     */
    public static String parseFormatDateCh(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(date);
    }

    public static Date parseFormatDateByStr(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date parseFormatDateByStr2(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 根据时间字符串获取0点的时间戳
     *
     * @param dateStr 时间字符串，格式必须为：yyyy-MM-dd
     * @return java.lang.String 时间戳字符串
     */
    public static String getStartOfDay(String dateStr) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ft.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime() + "";
    }

    /**
     * 根据时间字符串获取23:59:59的时间戳
     *
     * @param dateStr 时间字符串，格式必须为：yyyy-MM-dd
     * @return java.lang.String 时间戳字符串
     */
    public static String getEndOfDay(String dateStr) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ft.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime().getTime() + "";
    }

    /**
     * 获取当前周的周一的日期
     *
     * @param date 指定日期
     * @return java.util.Date 周一的零点的日期
     */
    public static Date getThisWeekMonday(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dataTime = null;
        try {
            dataTime = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        assert dataTime != null;
        cal.setTime(dataTime);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取当前周的周日的日期
     *
     * @param date 指定日期
     * @return java.util.Date 周一的零点的日期
     */
    public static Date getThisWeekSunday(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dataTime = null;
        try {
            dataTime = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        assert dataTime != null;
        c.setTime(dataTime);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.MILLISECOND, 59);
        // 如果是周日直接返回
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return c.getTime();
        }
        c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }

    /**
     * 获取传入日期所在月的第一天
     *
     * @param date 日期
     * @return java.util.Date 1号的零点
     */
    public static Date getThisFirstDayOfMonth(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final Calendar cal = Calendar.getInstance();
        assert dateTime != null;
        cal.setTime(dateTime);
        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

    /**
     * 获取传入日期所在月的最后一天
     *
     * @param date 日期
     * @return java.util.Date 最后一天的 零点
     */
    public static Date getThisLastDayOfMonth(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final Calendar cal = Calendar.getInstance();
        assert dateTime != null;
        cal.setTime(dateTime);
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    /**
     * 根据现在的时间获取上周今天
     *
     * @return java.util.Date
     **/
    public static Date getLastWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        return calendar.getTime();
    }

    /**
     * 根据现在的时间获取上月今天
     *
     * @return java.util.Date
     **/
    public static Date getLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 根据现在的时间获取上季度的今天
     *
     * @return java.util.Date
     **/
    public static Date getLastQuarter() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -3);
        return calendar.getTime();
    }


    public static List<String> findDates(Date dBegin, Date dEnd) {
        List<String> lDate = new ArrayList<>();
        lDate.add(DateUtil.parseFormatDateOwe(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(DateUtil.parseFormatDateOwe(calBegin.getTime()));
        }
        lDate.remove(lDate.size()-1);
        return lDate;
    }

    public static void main(String[] args) throws ParseException {
        List<String> dates = findDates(DateUtil.parseFormatDateByStr("2021-05-25 00:00:00"), new Date());
        System.out.println(dates);
    }
}
