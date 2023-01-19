package com.tomato.java8;

import com.tomato.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TestLambda {


    @Test
    public void test1() {

        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);

    }

    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }


//    List<Employee> employeeList = Arrays.asList(
//            new Employee("zhangsan",18,9999.99),
//            new Employee("lisi",38,8888.88),
//            new Employee("wangwu",58,3333.33),
//            new Employee("zhaoliu",8,6666.66)
//            );


    @Test
    public void test3(){

        Consumer<String> consumer = (x) -> System.out.printf(x);
        consumer.accept("hhah");
    }


    @Test
    public void test4(){
        List<String> list = new ArrayList<>();
        String str = "adb";

        list.forEach(s -> {
            System.out.println(str);
        });


        Predicate<String> predicate = s -> s != null && s.trim().length() == 0;
    }


}
