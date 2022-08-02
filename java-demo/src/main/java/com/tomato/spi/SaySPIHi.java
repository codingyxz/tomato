package com.tomato.spi;

/**
 * @author zhxy
 * @Date 2021/6/15 9:15 上午
 */
public class SaySPIHi implements SaySPI {


    @Override
    public void say(String word) {
        System.out.println("Hi " + word);
    }
}
