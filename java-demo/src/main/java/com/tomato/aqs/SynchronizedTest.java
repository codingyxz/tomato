package com.tomato.aqs;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * synchronized同步队列为先进后出队列，最后抢到锁资源的线程总会最先被唤醒，如：orderTest()
 * Thread.interrupt()会不停唤醒阻塞的线程（首先设置中断标识true，然后依次执行SleepEvent.unpark()、Parker.unpark()、ParkEvent.unpark()）
 * 对jvm层面有异常处理的抛出中断异常，否则应用程序自行去判断。
 *
 * 总结（Thread.sleep、LockSupport.park、synchronized线程阻塞方式的区别）
 * 1、系统级别：三种方式没有区别，最终都是调用系统的pthread_cond_wait方法
 * 2、c++级别：Thread.sleep使用的是线程的SleepEvent对象，LockSupport.park使用的是线程的Parker对象，synchronized和Object.wait使用的是线程的ParkEvent对象
 * 3、java级别：Thread.sleep可以打断并抛出异常；LockSupport可打断，但不会抛出异常，需要应用线程自行判断处理；
 *    synchronized不可打断，写死的for循环，被打断唤醒后继续抢锁，抢不到重新被阻塞；Object.wait可打断并抛出异常
 * 4、InterruptException其实仅仅是jvm逻辑上对打断标记的判断而已
 * 5、Thread.interrupt的本质在于修改打断标记，并调用三个unpark方法唤醒线程
 * 6、更概括来说，无论哪种线程阻塞的方式，在系统级别和c++线程级别来说都是可打断的。而JVM通过代码逻辑使得3种线程阻塞的方式在java级别上面对同一个打断方法时会有不同的表现形式。
 * 可参考文章：https://blog.csdn.net/weixin_44898959/article/details/114765748
 *
 * @author zhxy
 * @Date 2021/6/1 1:06 下午
 */
public class SynchronizedTest {

    @Test
    public void interruptTest() throws InterruptedException {
        int size = 3;
        Object o = new Object();
        // 第一个线程获取锁后阻塞1秒
        new Thread(() -> {
            synchronized (o) {
                System.out.println("Thread Lock ");
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lock Over");
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(10);

        // 启动一个单独的线程，用来测试synchronized的打断
        Thread interruptThread = new Thread(() -> {
            synchronized (o) {
                System.out.println("interruptThread !!");
            }
        });
        interruptThread.start();

        TimeUnit.MILLISECONDS.sleep(10);

        // 启动三个线程，并等待第一个线程释放锁，每个线程间隔10秒钟，保证入队的顺序性
        int count = 1;
        for (int i = 0; i < size; i++) {
            int m = count++;
            TimeUnit.MILLISECONDS.sleep(10);
            new Thread(() -> {
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName());
                }
                ;
            }, "thread--" + m).start();
        }

        for (; ; ) {
            interruptThread.interrupt();
        }
    }


    @Test
    public void orderTest() throws InterruptedException {
        int size = 3;
        Object o = new Object();
        // 第一个线程获取锁后阻塞1秒
        new Thread(() -> {
            synchronized (o) {
                System.out.println("Thread Lock ");
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lock Over");
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(10);

        // 启动三个线程，并等待第一个线程释放锁，每个线程间隔10秒钟，保证入队的顺序性
        int count = 1;
        for (int i = 0; i < size; i++) {
            int m = count++;
            TimeUnit.MILLISECONDS.sleep(10);
            new Thread(() -> {
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName());
                }
                ;
            }, "thread--" + m).start();
        }
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
