package com.tomato.java8.fork;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {

    /**
     * 并行任务
     */
    @Test
    public void test1() {
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0l, 100000000000l);

        Long sum = pool.invoke(task);
        System.out.println(sum);

        long end = System.currentTimeMillis();
        System.out.println("耗费的时间为：" + (end - start)); // 25964
    }

    /**
     * 串行任务
     */
    @Test
    public void test2() {
        long start = System.currentTimeMillis();

        long sum = 0l;
        for (long i = 0; i < 100000000000l; i++) {
            sum += i;
        }
        System.out.println(sum);

        long end = System.currentTimeMillis();
        System.out.println("耗费的时间为：" + (end - start)); //52768
    }

    @Test
    public void test3() {
        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(0l, 100000000000l).parallel().sum();
        System.out.println(sum);

        long end = System.currentTimeMillis();
        System.out.println("耗费的时间为：" + (end - start)); // 14163
    }

}
