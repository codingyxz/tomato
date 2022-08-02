package com.tomato.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *  vmOption: -Xmx60m -Xms60 -XX:SurvivorRatio=8
 *
 *
 * @author zhxy
 * @Date 2021/5/27 12:21 下午
 */
public class GCTest {


    public static void main(String[] args)  {

        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            byte[] bytes = new byte[100 * 1024];
            list.add(bytes);
            try {
                Thread.sleep(120);
            } catch (Exception e) {

            }
        }
    }
}
