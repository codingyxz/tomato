package com.tomato.java;

/**
 * @author zhxy
 * @Date 2021/5/26 2:36 下午
 */
public class TestStack {


    public static void main(String[] args) {
        TestStack testStack = new TestStack();
        testStack.method1();
    }


    public void method1() {
        System.out.println("method1.........begin");
        method2();
        System.out.println("method1.........end");

    }

    public int method2() {
        System.out.println("method2.........begin");
        int i = 10;
        int v = (int) method3();
        System.out.println("method2.........end");
        return i + v;
    }

    public double method3() {
        System.out.println("method3.........begin");
        double m = 20.0;
        System.out.println("method3.........end");
        return m;
    }


}
