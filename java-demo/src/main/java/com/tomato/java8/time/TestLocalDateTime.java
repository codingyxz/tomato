package com.tomato.java8.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class TestLocalDateTime {

    /**
     * 1. LocalDate、LocalTime、LocalDateTime
     */
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2024, 12, 30, 14, 05, 05);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt2.plusYears(20);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt2.minusMonths(2);
        System.out.println(ldt4);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

    /**
     * 2. Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
     */
    @Test
    public void test2() {
        Instant now = Instant.now();
        System.out.println(now);

        OffsetDateTime odt = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(now.getNano());

        Instant ins = Instant.ofEpochSecond(5);
        System.out.println(ins);
    }

    /**
     * 3.
     * Duration：用于计算两个"时间"的间隔
     * Period：用于计算两个"日期"的间隔
     */
    @Test
    public void test3() {
        Instant ins1 = Instant.now();
        System.out.println("----------------------------------");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Instant ins2 = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(ins1, ins2));

        System.out.println("----------------------------------");

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2011, 1, 1);
        Period pe = Period.between(ld1, ld2);
        System.out.println(pe.getYears());
        System.out.println(pe.getMonths());
        System.out.println(pe.getDays());
    }

    /**
     * 4. TemporalAdjuster 时间校正器
     */
    @Test
    public void test4() {
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        LocalDateTime ldt2 = ldt1.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        // 自定义：下一个工作日
        LocalDateTime ldt5 = ldt3.with(l -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dow = ldt1.getDayOfWeek();

            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }


    /**
     * 5. DateTimeFormatter 解析和格式化日期或时间
     */
    @Test
    public void test5() {
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(dtf1));
        System.out.println(now.format(dtf2));

        String formatTime = now.format(dtf2);
        LocalDateTime parse = LocalDateTime.parse(formatTime, dtf2);
        System.out.println(parse);
    }

    /**
     * 6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
     */
    @Test
    public void test6() {
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zdt);

        System.out.println("----------------------------------");

        ZoneId.getAvailableZoneIds().forEach(System.out::println);
    }

}
