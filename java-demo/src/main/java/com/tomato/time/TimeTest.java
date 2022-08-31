package com.tomato.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/8/15 11:33 上午
 * @Version V1.0
 **/
public class TimeTest {

    public static void main(String[] args) {

        LocalDateTime plusTIme = LocalDateTime.now().plusDays(1);

        LocalDateTime now = LocalDateTime.now();
        now = LocalDateTime.of(now.toLocalDate(), LocalTime.MAX);

        System.out.println(plusTIme);

        System.out.println(now);
        System.out.println(now.minusHours(2));
        System.out.println(now.plusHours(2));

        System.out.println(now + "---" + LocalDateTime.now() + "-----" + Duration.between(now, now.plusDays(1)).toDays());
        System.out.println(Duration.between(now.minusHours(2), plusTIme).toDays());
        System.out.println(Duration.between(now.plusHours(2), plusTIme).toDays());


    }
}
