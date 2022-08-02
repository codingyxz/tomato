package com.tomato.thread;

/**
 * @author zhxy
 * @Date 2021/6/2 12:01 下午
 */
public class ThreadSafeTest {
    public static void main(String[] args) throws InterruptedException {
        Thread add = new AddThread();
        Thread dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
}


class Counter {
    public static int count = 0;
}

class AddThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter.count += 1;
        }
    }
}

class DecThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter.count -= 1;
        }
    }
}

