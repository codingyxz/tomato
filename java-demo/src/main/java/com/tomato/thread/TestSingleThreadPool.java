package com.tomato.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/10/23 8:40 下午
 * @Version V1.0
 **/
public class TestSingleThreadPool {


    public static void main(String[] args) throws InterruptedException {


        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("11   -    " + System.currentTimeMillis());
                    int a = 10 / 0;
                    System.out.println("22   -    " + System.currentTimeMillis());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 0l, 3l, TimeUnit.SECONDS);
    }

}
