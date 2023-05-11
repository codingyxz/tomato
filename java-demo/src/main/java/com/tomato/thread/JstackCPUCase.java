package com.tomato.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * jstack分析CPU过高
 *
 * @Author zhxy
 * @Date 2023/5/11 4:43 下午
 * @Version V1.0
 **/
public class JstackCPUCase {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {

        Task task1 = new Task();
        Task task2 = new Task();
        executorService.execute(task1);
        executorService.execute(task2);
    }

    public static Object lock = new Object();

    static class Task implements Runnable {

        public void run() {
            synchronized (lock) {
                long sum = 0L;
                while (true) {
                    sum += 1;
                }
            }
        }
    }
}
