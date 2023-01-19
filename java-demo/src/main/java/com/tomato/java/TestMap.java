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


        System.out.println(map);


    }
}
