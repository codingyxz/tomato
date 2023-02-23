package com.tomato.java;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author zhxy
 * @Date 2023/2/15 1:44 下午
 * @Version V1.0
 **/
public class TestSet {


    public static void main(String[] args) {


        Set<String> set = new HashSet<>();
//        set.add("abc");
        set.add("123");

        set.remove("abc");
        set.remove("dfd");

        System.out.println(set.size());
        set.forEach(item -> System.out.println(item));

    }
}
