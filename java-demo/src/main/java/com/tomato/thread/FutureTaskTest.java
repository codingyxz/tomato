package com.tomato.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhxy
 * @Date 2021/6/16 10:43 上午
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//
//        FutureTask<String> task = new FutureTask<>(new MyThread());
//
//        new Thread(task,"aa").start();
//
//        System.out.println(task.get());

        StringBuilder builder = new StringBuilder();


        FutureTask<StringBuilder> task = new FutureTask<StringBuilder>(() -> {
            System.out.println("come in runnable");
            builder.append("come in urnnable------------------");
        }, builder);

        new Thread(task,"bb").start();
        System.out.println(task.get().toString());


    }
}


class MyThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("come in ...");
        return "888";
    }
}
