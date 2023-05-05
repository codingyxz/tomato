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
//        lockTest.noFairTest();
//        lockTest.synKeyTest();
        lockTest.interruptedTest();
    }


    public void interruptedTest(){

        Thread currentTh = Thread.currentThread();
        currentTh.interrupt();

        System.out.println(currentTh.isInterrupted());

        System.out.println(Thread.interrupted());

        System.out.println(currentTh.isInterrupted());

    }


    public void synKeyTest() {

        Object lock = "abc";
        Object lock1 = "abd";

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(System.currentTimeMillis() + "----" + Thread.currentThread().getName() + "---- 获取到了锁-----");
                try {
                    Thread.sleep(5000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + "----" + Thread.currentThread().getName() + "---- 释放了锁-----");
        }, "a").start();


        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(System.currentTimeMillis() + "----" + Thread.currentThread().getName() + "---- 获取到了锁-----");
                try {
                    Thread.sleep(5000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + "----" + Thread.currentThread().getName() + "---- 释放了锁-----");
        }, "b").start();


    }

    public void noFairTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
//        lock.lock();
//        lock.unlock();
//        System.out.println(lock.getHoldCount());
//        lock.unlock();
//        System.out.println(lock.getHoldCount());

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "---- 获取到了锁-----");
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "---- 释放了锁-----");
                lock.unlock();
            }
        }, "a").start();


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
