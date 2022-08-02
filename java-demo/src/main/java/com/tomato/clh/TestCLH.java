package com.tomato.clh;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

/**
 * @author zhxy
 * @Date 2021/6/2 3:46 下午
 */
public class TestCLH {

    public static void main(String[] args) throws InterruptedException {
        final KFC kfc = new KFC();
        Executor executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 35; i++) {
            executor.execute(kfc::takeout);
        }
    }

}

class KFC {
    private final Lock lock = new CLHLock();
    private int i = 0;

    public void takeout() {
        try {
            lock.lock();
//            System.out.println(Thread.currentThread().getName() + ": 拿了第" + ++i + "份外卖");
            System.out.println(Thread.currentThread().getName() + ": " + ++i );
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
