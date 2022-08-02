package com.tomato.jvm;

/**
 * 同步省略说明
 *
 * @author zhxy
 * @Date 2021/5/27 1:50 下午
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        Object o = new Object();
        synchronized (o) {
            System.out.println(o);
        }
    }
}
