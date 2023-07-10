package com.tomato.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author zhxy
 * @Date 2023/6/2 3:08 下午
 * @Version V1.0
 **/
public class LockSupportTest {


    public static void main(String[] args) {

        System.out.println("------");

        LockSupport.park();

        System.out.println("==========");

    }
}
