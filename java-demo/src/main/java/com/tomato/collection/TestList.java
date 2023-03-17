package com.tomato.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/8/28 11:06 上午
 * @Version V1.0
 **/
public class TestList {


    public static void main(String[] args) {


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
//        arrayList.add("5");
//        List<String> strings = arrayList.subList(0, 2);
//        System.out.println(strings);
//        System.out.println(arrayList);
//        arrayList.remove(0);
//        strings.set(0,"100");
//        System.out.println(strings);
//        System.out.println(arrayList);

        List<List<String>> partition = Lists.partition(arrayList, 2);

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if("3".equals(next)){
                arrayList.remove(next);
            }
            System.out.println(next);
        }


//
//        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
//
//        list.remove("1");
//        System.out.println(list);
//
//        System.out.println(list.subList(0, 2));
//        System.out.println(list.subList(0, 2));
//        System.out.println(list.subList(2, 4));
//        System.out.println(list.subList(0, 10));
//        System.out.println(list.subList(10, 10));


    }
}
