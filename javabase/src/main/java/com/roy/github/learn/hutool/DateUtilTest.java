package com.roy.github.learn.hutool;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/23 0023.
 */
public class DateUtilTest {

    public static void main(String[] args) {
        Date date = DateUtil.parse("2018-11-23");
        System.out.println(date.toString());
        System.out.println(DateUtil.formatDate(date));
        System.out.println(DateUtil.formatDateTime(date));
        System.out.println(DateUtil.formatTime(date));
        System.out.println(DateUtil.year(date));
        System.out.println(DateUtil.month(date));
        System.out.println(DateUtil.monthEnum(date));
        System.out.println(DateUtil.beginOfDay(date));
        System.out.println(DateUtil.endOfDay(date));
        System.out.println(DateUtil.offset(date, DateField.HOUR,2));
        System.out.println(DateUtil.offsetDay(date, 2));
        System.out.println(DateUtil.yesterday());
        System.out.println(DateUtil.tomorrow());
        System.out.println(DateUtil.lastMonth());
        System.out.println(DateUtil.nextMonth());

    }

}
