package com.tomato.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
                // 队列长度达到最大时，主线程自旋等待
                while (e.getQueue().size() == queueSize) {
                    try {
                        Thread.sleep(500l);
                        System.out.println(Thread.currentThread().getName() + "---sleep---");
                    } catch (Exception ex) {

                    }
                }
                System.out.println(e.getQueue().size() + "---submit---");
                e.execute(r);
                System.out.println(e.getQueue().size() + "---submit---");
            }
        }
    }

    public static void main(String[] args) {
        // 依据可用的cpu计算线程池核心线程数
        int cpus = Runtime.getRuntime().availableProcessors();
        int coreThread = cpus < 2 ? 2 : cpus * 3 / 2;
        int blockSize = coreThread / 2 + 1;
        log.info("当前服务器可用cpu数量为：{}，初始化脚本线程池核心线程数为：{}，阻塞队列长度为：{}", cpus, coreThread, blockSize);
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(blockSize);
        // 自定义线程池拒绝策略
        SpinWaitPolicy spinWaitPolicy = new SpinWaitPolicy(blockSize);
        ThreadPoolExecutor runnerPoolExecutor = new ThreadPoolExecutor(coreThread, coreThread, 0l,
                TimeUnit.SECONDS, blockingQueue, Executors.defaultThreadFactory(), spinWaitPolicy);


        while (true) {
            runnerPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(200);
                } catch (Exception ex) {

                }
            });
        }
    }
}
