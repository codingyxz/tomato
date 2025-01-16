package com.tomato.java8.stream;


import com.tomato.java8.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * Stream API 的操作步骤1: 创建 Stream
 */
public class TestStreamCreateApi {

    @Test
    public void test1() {
        // 1.Collection提供了两个方法，stream()与parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream(); // 获得一个顺序流
        Stream<String> parallelStream = list.parallelStream();  // 获得一个并行流

        // 2.通过 Arrays 中的 stream() 获得一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        // 3.通过 stream 类中的静态方法 of()
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);

        // 4.创建无限流
        // 迭代
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        // 生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);

        Map<String,Employee> map = new HashMap<>();

    }

}
