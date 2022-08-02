package com.tomato.jvm;

/**
 * -XX:SurvivorRatio=8
 *
 * @author zhxy
 * @Date 2021/5/26 8:35 下午
 */
public class TestEden {

    public static void main(String[] args) {


        int mun = 0;

        while (true) {
            System.out.println(mun++);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000000);
                    } catch (Exception e) {

                    }

                }
            }).start();
        }

    }


    public int minus() {
        int c = 3;
        int d = 4;
        return c - d;
    }
}
