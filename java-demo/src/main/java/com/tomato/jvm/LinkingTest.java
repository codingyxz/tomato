package com.tomato.jvm;

/**
 * @author zhxy
 * @Date 2021/5/30 12:05 下午
 */
public class LinkingTest {

    private static String name;

    private static final String address = "haah";

    public final String desc = "yyy";

    public String age = "aaa";

    public final static int num1 = Integer.valueOf(100);

    public final static int num2 = 100;


    static {
        name = "cc";
    }

    public static void main(String[] args) {

        System.out.println();


    }

}
