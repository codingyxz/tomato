package com.tomato.java8.lambda;

import com.tomato.java8.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

public class TestLambdaMethodRef {

    /*
     * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
     * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
     *
     * 1. 对象的引用 :: 实例方法名
     *
     * 2. 类名 :: 静态方法名
     *
     * 3. 类名 :: 实例方法名
     *
     * 注意：
     * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
     * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
     *
     * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
     *
     * 1. 类名 :: new
     *
     * 三、数组引用
     *
     * 	类型[] :: new;
     *
     *
     */


    /**
     * 对象引用::实例方法名
     */
    @Test
    public void test1() {
        PrintStream out = System.out;
        Consumer<String> con = x -> out.println(x);
        con.accept("hello lambda");

        System.out.println("----------------------------");

        Consumer<String> con1 = out::println;
        con1.accept("hello lambda method ref");

        System.out.println("----------------------------");

        Employee employee = new Employee(101, "zhangsan", 28, 8888);
        Supplier<String> sup = () -> employee.getName();
        System.out.println(sup.get());

        System.out.println("----------------------------");

        Supplier<String> sup1 = employee::getName;
        System.out.println(sup1.get());
    }

    /**
     * 类名::静态方法
     */
    @Test
    public void test2() {
        BiFunction<Double, Double, Double> biFun = (x, y) -> Math.max(x, y);
        System.out.println(biFun.apply(11.5, 22.5));

        System.out.println("----------------------------");

        BiFunction<Double, Double, Double> biFun2 = Math::max;
        System.out.println(biFun2.apply(33.3, 44.4));

        System.out.println("----------------------------");

        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println("----------------------------");
        Comparator<Integer> com1 = Integer::compare;
    }

    /**
     * 类名::实例方法名
     * <p>
     * 若Lambda的参数列表的第一个参数，是实例方法的调用者，第二个参数（或无参）是实例方法的参数时，格式为：ClassName::MethodMethod
     */
    @Test
    public void test3() {
        BiPredicate<String, String> biPre = (x, y) -> x.equals(y);
        System.out.println(biPre.test("abced", "adfgdf"));

        System.out.println("----------------------------");

        BiPredicate<String, String> biPre2 = String::equals;
        System.out.println(biPre2.test("123434", "3434344"));

    }

    /**
     * 构造器引用
     */
    @Test
    public void test4() {
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());

        System.out.println("----------------------------");

        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());

        System.out.println("----------------------------");

        Function<String, Employee> fun = Employee::new;
        System.out.println(fun.apply("zhangsan"));

        System.out.println("----------------------------");

        BiFunction<String, Integer, Employee> fun2 = Employee::new;
        System.out.println(fun2.apply("lisi", 28));
    }

    /**
     * 数组引用
     */
    @Test
    public void test5() {
        Function<Integer, String[]> fun = args -> new String[args];
        System.out.println(fun.apply(10).length);

        System.out.println("----------------------------");

        Function<Integer, Employee[]> fun2 = Employee[]::new;
        System.out.println(fun2.apply(20).length);
    }
}
