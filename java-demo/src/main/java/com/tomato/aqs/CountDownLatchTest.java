package com.tomato.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhxy
 * @Date 2021/6/2 10:44 下午
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            sleep(TimeUnit.MILLISECONDS, 800);
            System.out.println(Thread.currentThread().getName() + " Finished");
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            sleep(TimeUnit.MILLISECONDS, 500);
            System.out.println(Thread.currentThread().getName() + " Finished");
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            sleep(TimeUnit.MILLISECONDS, 600);
            System.out.println(Thread.currentThread().getName() + " Finished");
            countDownLatch.countDown();
        }).start();


        countDownLatch.await();
        System.out.println("All Finished");




    }

    private static void sleep(TimeUnit milliseconds, int i) {
        try {
            milliseconds.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
