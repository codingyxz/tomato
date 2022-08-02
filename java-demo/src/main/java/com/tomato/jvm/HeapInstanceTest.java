package com.tomato.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhxy
 * @Date 2021/5/27 10:38 上午
 */
public class HeapInstanceTest {


    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];

    public static void main(String[] args) {
        List<HeapInstanceTest> list = new ArrayList<>();

        while (true){
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            }catch (Exception e){

            }
        }
    }
}
