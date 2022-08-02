package com.tomato.thread;

/**
 * @author zhxy
 * @Date 2021/6/3 2:47 下午
 */
public class MixedOrder {
    int a = 0;
    boolean flag = false;
    public void writer(){
        flag = true;
        a = 1;

    }

    public void read(){
        if(flag){
            int i = a + 1;
            System.out.println(i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MixedOrder order = new MixedOrder();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                order.read();
            },"b").start();

            new Thread(() -> {
                order.writer();
            },"a").start();
        }

        Thread.sleep(1000l);

//        System.out.println("a:" + order.a + "-----flag:" + order.flag);
    }
}
