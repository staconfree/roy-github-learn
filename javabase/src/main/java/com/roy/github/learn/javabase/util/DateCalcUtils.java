package com.roy.github.learn.javabase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by roy on 2018/4/18.
 */
public class DateCalcUtils {

    public static int yearsBetween(Date startDate, Date endDate){
        int result = monthsBetween(startDate, endDate);
        return result/12;
    }


    private static int onlyYearsBetween(Date startDate, Date endDate){
        Calendar calBegin = Calendar.getInstance(); //获取日历实例
        Calendar calEnd = Calendar.getInstance();
        calBegin.setTime(startDate); //字符串按照指定格式转化为日期
        calEnd.setTime(endDate);
        int result = calEnd.get(Calendar.YEAR) - calBegin.get(Calendar.YEAR);
        return result;
    }

    public static int monthsBetween(Date startDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);
        int result =onlyYearsBetween(startDate, endDate) * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        int c1day = c1.get(Calendar.DAY_OF_MONTH);
        int c2day = c2.get(Calendar.DAY_OF_MONTH);
        if (c2day-c1day+1==c2.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            // 如果相差的天数等于当月的最大天数,则加一个月
            result+=1;
        }
        return result == 0 ? 1 : Math.abs(result);
    }

    public static int daysBetween(Date bgnTime, Date endTime) {
        int between_days = ((int)(endTime.getTime()/1000)-(int)(bgnTime.getTime()/1000))/3600/24+1;
        return between_days;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date startDate = sf.parse("20180621000000");
//        Date endDate = sf.parse("20190620235959");
        Date startDate = sf.parse("20181201000000");
        Date endDate = sf.parse("20211130235959");
        System.out.println(monthsBetween(startDate,endDate));
        System.out.println(yearsBetween(startDate,endDate));
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmss");
//        DateTime bgn = DateTime.parse("20180801000000",dateTimeFormatter);
//        DateTime end = DateTime.parse("20200731235959",dateTimeFormatter);
//        System.out.println(Months.monthsBetween(bgn,end).getMonths());
    }

}
