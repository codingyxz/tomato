package com.tomato.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zhxy
 * @Date 2021/6/16 10:43 上午
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 简单使用
        simpleFuture();

        // 最常用法：搭配线程池
        commonFuture();

    }


    /**
     * Future 简单使用
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void simpleFuture() throws ExecutionException, InterruptedException {
        StringBuilder builder = new StringBuilder();

        /**
         * 声明一个FutureTask对象，入参为 ①需要执行的任务，②执行成功后返回的结果
         *      FutureTask本身是一个Runnable，故可以通过创建线程的方式启动执行
         *      FutureTask本身是一个Future，故可以通过Future机制 get 方式阻塞等待结果返回
         */
        FutureTask<StringBuilder> task = new FutureTask<StringBuilder>(() -> {
            System.out.println("come in runnable");
            builder.append("come in runnable------------------");
        }, builder);
        // 启动任务运行
        new Thread(task, "bb").start();
        // get阻塞获取结果
        StringBuilder stringBuilder = task.get();
        System.out.println(stringBuilder.toString());
    }

    /**
     * Future 最长用法（搭配线程池）
     */
    public static void commonFuture() {

        // 创建任务集合
        List<Future<Integer>> taskList = new ArrayList<>();
        // 创建线程池
        ExecutorService exec = Executors.newFixedThreadPool(6);

        /**
         * 方式1：声明FutureTask对象，然后保持该对象引用，同时将其放入线程池执行
         */
        for (int i = 0; i < 20; i++) {
            // 传入Callable对象，创建Future
            FutureTask<Integer> fTask = new FutureTask<>(new ComputeTask(i, "task" + i));
            taskList.add(fTask);
            // 提交给线程池执行任务，也可以通过 invokeAll方法 一次性提交所有任务
            exec.submit(fTask);
        }

        /**
         * 方式2：声明Callable对象，将其放入线程池会返回包装好的 RunnableFuture（FutureTask的父类） 对象，同时保持其引用
         */
        for (int i = 0; i < 20; i++) {
            // 声明Callable任务
            ComputeTask computeTask = new ComputeTask(i, "task" + i);
            // 提交给线程池，异步执行前会先包装返回Future对象引用
            Future<Integer> future = exec.submit(computeTask);
            taskList.add(future);
        }

        System.out.println("所有计算任务提交完毕，主线程接着干其他事情...");

        // 开始统计各计算线程计算结果
        Integer totalResult = 0;
        for (Future<Integer> fTask : taskList) {
            try {
                // Future的 get方法 自动阻塞，直到获取计算结果，或者等待超时
                totalResult += fTask.get();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        }
        exec.shutdown();
        System.out.println("多任务计算后的总结果是：" + totalResult);
    }

}


class ComputeTask implements Callable<Integer> {

    private Integer result = 0;
    private String taskName = "";

    public ComputeTask(Integer result, String taskName) {
        this.result = result;
        this.taskName = taskName;
        System.out.println("生成子线程计算任务：" + taskName);
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 100; i++) {
            result += i;
        }
        /**
         * 休眠5秒，观察主线程行为，预期结果是主线程继续执行，直到要取到FutureTask的结果处阻塞等待
         */
        Thread.sleep(5000);
        System.out.println("子线程计算任务；" + taskName + " 执行完成！");
        return result;
    }
}
