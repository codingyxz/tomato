package com.tomato.java8.fork;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {


    private static final long serialVersionUID = 1426116537177618365L;

    private Long start;
    private Long end;

    public static final long THRESHOLD = 10000L; // 临界值

    public ForkJoinCalculate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork(); // 拆分，并将该子任务压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
