package com.tomato.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhxy
 * @Date 2023/3/20 3:49 下午
 * @Version V1.0
 **/

@Slf4j
public class TestCustomThread {


    public static class SpinWaitPolicy implements RejectedExecutionHandler {
        private int queueSize;  // 线程池队列长度

        public SpinWaitPolicy(int queueSize) {
            this.queueSize = queueSize;
        }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                try {
                    e.getQueue().put(r);  // 阻塞插入队列
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 依据可用的cpu计算线程池核心线程数
        int cpus = Runtime.getRuntime().availableProcessors();
        int coreThread = cpus < 2 ? 2 : cpus * 3 / 2;
        int blockSize = 2 * coreThread;
        log.info("当前服务器可用cpu数量为：{}，初始化脚本线程池核心线程数为：{}，阻塞队列长度为：{}", cpus, coreThread, blockSize);
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(blockSize);
        ThreadFactory threadFactory = new CustomizableThreadFactory("versionRunner-pool-");

        // 自定义线程池拒绝策略
        SpinWaitPolicy spinWaitPolicy = new SpinWaitPolicy(blockSize);
        ThreadPoolExecutor runnerPoolExecutor = new ThreadPoolExecutor(coreThread, coreThread, 0l,
                TimeUnit.SECONDS, blockingQueue, threadFactory, spinWaitPolicy);


        int count = 20;
        while (count++ < 50) {
            runnerPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(5000);
//                    atomicInteger.decrementAndGet();
//                    System.out.println(Thread.currentThread().getName() + " ----- 执行一下 ----" + System.currentTimeMillis() / 1000 + " ------- " + atomicInteger.get());
                } catch (Exception ex) {

                }
            });
        }

        System.out.println("----");
        runnerPoolExecutor.shutdown();
        try {
            while (!runnerPoolExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("wait");
            }

        } catch (InterruptedException ex) {

        }

        runnerPoolExecutor.submit(() -> {
            try {
                System.out.println("----------");
                Thread.sleep(1000);
            } catch (Exception ex) {

            }
        });

    }
}
