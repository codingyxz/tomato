package com.tomato.thread;

/**
 * @author zhxy
 * @Date 2021/6/2 11:04 上午
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("hello");
        });
        System.out.println("start");
        t.start();
        t.join();
        System.out.println("end");

    }

}
