package com.tomato.thread;

/**
 * @author zhxy
 * @Date 2021/6/3 1:41 下午
 */
public class DaemonTest {
    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(() -> {

            while (true){
                System.out.println("i am alive");
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("i am in finally");
                }
            }
        }, "a");
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(1000l);
        System.out.println("main end .......");

    }
}
