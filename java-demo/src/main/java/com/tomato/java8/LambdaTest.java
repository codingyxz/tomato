package com.tomato.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhxy
 * @Date 2021/7/22 2:37 下午
 */
public class LambdaTest {

    @FunctionalInterface
    public interface LambdaDemo1{
        public void runLambda();
    }

    @FunctionalInterface
    public interface LambdaDemo2{
        public void runLambda();
    }


    public static void doSomething1(LambdaDemo1 demo){
        demo.runLambda();
    }

    public static void doSomething2(LambdaDemo2 demo){
        demo.runLambda();
    }

    public static void main(String[] args) {
//        doSomething1(()->System.out.println("hello world!"));
//        doSomething2(()->System.out.println("hello world!"));


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        list.forEach(item -> {
//            if(item == 2){
//                return;
//            }
//            System.out.println(item);
//        });

        Optional<Integer> first = list.stream().filter(item -> item > 2).findFirst();
        if(first.isEmpty()){
            System.out.println(first.get());
        }else {
            System.out.println("---");
        }

    }
}
