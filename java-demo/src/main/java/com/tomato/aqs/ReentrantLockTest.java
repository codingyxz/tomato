package com.tomato.aqs;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhxy
 * @Date 2021/6/1 12:26 下午
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest lockTest = new ReentrantLockTest();
        lockTest.noFairTest();
    }

    public void noFairTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.unlock();

//        new Thread(() -> {
//            try {
//                lock.lock();
//                System.out.println(Thread.currentThread().getName() + "---- 获取到了锁-----");
//                Thread.sleep(5000l);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println(Thread.currentThread().getName() + "---- 释放了锁-----");
//                lock.unlock();
//            }
//        }, "a").start();


//        new Thread(() -> {
//            try {
//                lock.lock();
//                System.out.println(Thread.currentThread().getName() + "---- 获取到了锁-----");
//                Thread.sleep(5000l);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }, "b").start();

//        Thread.sleep(1000l);
//        lock.unlock();
//        System.out.println(Thread.currentThread().getName() + ":结束......");
//        lock.lock();
    }

}
