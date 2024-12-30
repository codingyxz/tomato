package com.tomato.java8.optional;

import com.tomato.java8.Employee;
import org.junit.Test;

import java.util.Optional;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {


    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        System.out.println(op.get());
    }

    @Test
    public void test2() {
//        Optional<Employee> op = Optional.ofNullable(null);
//        System.out.println(op.get());

//        Optional<Employee> op = Optional.empty();
//        System.out.println(op.get());
    }

    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(new Employee());
        if (op.isPresent()) {
            System.out.println(op.get());
        }

        Employee emp = op.orElse(new Employee("zhangsan"));
        System.out.println(emp);

        Employee emp2 = op.orElseGet(() -> new Employee());
        System.out.println(emp2);
    }

    @Test
    public void test4() {
        Optional<Employee> op = Optional.of(new Employee(101, null, 18, 9999));

        Optional<String> opName1 = op.map(Employee::getName);
        System.out.println(opName1.get());

        Optional<String> opName2 = op.flatMap(e -> Optional.of(e.getName()));
        System.out.println(opName2.get());
    }

}
