package com.tomato.thread;

/**
 * @author zhxy
 * @Date 2021/6/2 11:05 上午
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new DemoThread1();
        thread1.start();

        DemoThread2 thread2 = new DemoThread2();
        thread2.start();

        Thread.sleep(1000l);

        thread1.interrupt(); // 中断t线程
        thread2.interrupt();

        Thread.currentThread().interrupt();
        System.out.println("thread1 : " + thread1.isInterrupted());
        System.out.println("thread2 : " + thread2.isInterrupted());

    }
}

class DemoThread1 extends Thread {
    public void run() {
        int n = 0;
        while (!Thread.interrupted()) {
            n++;
            System.out.println(n + " hello!");
        }
        System.out.println("thread1内部：" + Thread.interrupted());
    }
}

class DemoThread2 extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            System.out.println("被中断了 ：" + e.getMessage());
        }
    }
}
