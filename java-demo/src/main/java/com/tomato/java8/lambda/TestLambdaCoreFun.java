package com.tomato.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestLambdaCoreFun {

    /*
     * Java8 内置的四大核心函数式接口
     *
     * Consumer<T> : 消费型接口
     * 		void accept(T t);
     *
     * Supplier<T> : 供给型接口
     * 		T get();
     *
     * Function<T, R> : 函数型接口
     * 		R apply(T t);
     *
     * Predicate<T> : 断言型接口
     * 		boolean test(T t);
     *
     */

    /**
     * Consumer<T> 消费型接口
     */
    @Test
    public void test1() {
        happy(100, x -> System.out.println(x));
    }

    public void happy(double money, Consumer<Double> fun) {
        fun.accept(money);
    }

    /**
     * Supplier<T> 供应型接口
     */
    @Test
    public void test2() {
        List<Integer> numList = getNumList(8, () -> (int) (Math.random() * 100));
        numList.forEach(x -> System.out.println(x));
    }

    public List<Integer> getNumList(int num, Supplier<Integer> fun) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(fun.get());
        }
        return list;
    }

    /**
     * Function<T,R> 函数型接口
     */
    @Test
    public void test3() {
        System.out.println(handlerStr("HandlerStr", x -> x.toUpperCase(Locale.ROOT)));
        System.out.println("----------------------------------------------------------");
        System.out.println(handlerStr("今天是个好天气", x -> x.substring(4, 7)));
    }

    public String handlerStr(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    /**
     * Predicate<T> 断言型接口
     */
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "today", "Lambda", "nature");
        List<String> filterList = filterStr(list, x -> x.length() > 5);
        for (String str : filterList) {
            System.out.println(str);
        }
    }

    public List<String> filterStr(List<String> list, Predicate<String> fun) {
        List<String> resList = new ArrayList<>();

        for (String item : list) {
            if (fun.test(item)) {
                resList.add(item);
            }
        }
        return resList;
    }

}
