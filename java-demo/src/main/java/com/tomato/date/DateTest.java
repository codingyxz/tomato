package com.tomato.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/8/22 11:07 上午
 * @Version V1.0
 **/
public class DateTest {

    public static void main(String[] args) {


//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
//        System.out.println(simpleDateFormat.format(new Date()));
//        System.out.println(simpleDateFormat.format(new Date()).substring(0, 2));
        YearMonth yearMonth = YearMonth.parse("2012-11", DateTimeFormatter.ofPattern("yyyy-MM"));

        int year = yearMonth.getYear();

        System.out.println(yearMonth.getYear());
        System.out.println();
//        System.out.println(LocalTime.parse("2012-11", DateTimeFormatter.ofPattern("yyyy-MM")));

    }
}
