package com.tomato.jvm;

/**
 * 标量替换说明
 * 命令： -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:-EliminateAllocation
 *
 *
 * @author zhxy
 * @Date 2021/5/27 1:57 下午
 */
public class ScalarReplace {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + "ms");
//        try {
//            Thread.sleep(1000000);
//        } catch (Exception e) {
//
//        }
    }

    private static void alloc() {
        Student student = new Student();
//        student.id = 1;
//        student.name="lisi";
    }
}


class Student{
//    public int id;
//    public String name;
}
