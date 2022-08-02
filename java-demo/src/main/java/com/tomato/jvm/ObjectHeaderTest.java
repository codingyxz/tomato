package com.tomato.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author zhxy
 * @Date 2021/5/28 8:13 上午
 */
public class ObjectHeaderTest {

    public static void main(String[] args) throws Exception{
        Object o = new Object();

        TimeUnit.SECONDS.sleep(5l);

//        System.out.println(Integer.toHexString(o.hashCode()));

//        new Thread(() -> {
//            synchronized (o){
//                try{
//                    Thread.sleep(1000l);
//                }catch (Exception e){
//
//                }
//            }
//        }).start();


        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());


    }
}
