package com.tomato.memeory;

/**
 * @author zhxy
 * @Date 2021/6/2 12:35 下午
 */
public class VolatileTest {
    public static void main(String[] args) {

        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":开始执行。。。");
            try {
                Thread.sleep(2000l);
                myData.setNum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"aa").start();

        while (myData.num == 0 ){

        }
        System.out.println(Thread.currentThread().getName() + ":结束。。。。");

    }
}


class MyData{
    int num = 0;

    public void add(){
        num++;
    }

    public void setNum(){
        this.num = 60;
    }
}

