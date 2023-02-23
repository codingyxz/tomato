package com.tomato.java;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhxy
 * @Date 2023/1/13 1:37 下午
 * @Version V1.0
 **/
public class TestMap {
    public static void main(String[] args) {


        Map<String, String> map = new HashMap<>(2);

        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");

        Map<String, String> map2 = new HashMap<>(2);
        map2.put("2","10");
        map2.put("5","5");

        map.putAll(map2);


        System.out.println(map);


    }
}
