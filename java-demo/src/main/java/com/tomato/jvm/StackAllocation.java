package com.tomato.jvm;

import com.tomato.entity.User;

/**
 * 栈上分配测试
 * -Xmx1G -Xms1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 *
 *
 *
 * @author zhxy
 * @Date 2021/5/27 1:32 下午
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + "ms");
        try {
            Thread.sleep(1000000);
        } catch (Exception e) {

        }


    }

    public static void alloc() {
        User user = new User(); // 未发生逃逸
    }

}
