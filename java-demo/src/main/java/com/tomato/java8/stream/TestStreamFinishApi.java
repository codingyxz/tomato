package com.tomato.java8.stream;

import com.tomato.java8.Employee;
import com.tomato.java8.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream API 的操作步骤3:终止操作(终端操作)
 */
public class TestStreamFinishApi {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );

    //3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */


    /**
     * allMatch、anyMatch、noneMatch
     */
    @Test
    public void test1() {
        boolean b1 = emps.stream().allMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b1);

        boolean b2 = emps.stream().anyMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b2);

        boolean b3 = emps.stream().noneMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b3);
    }

    /**
     * findFirst
     * findAny
     */
    @Test
    public void test2() {
        Optional<Employee> first = emps.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findFirst();
        System.out.println(first.get());

        System.out.println("------------------------------------------");

        Optional<Employee> any = emps.parallelStream()
                .filter(e -> e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(any.get());
    }

    /**
     * count
     * max
     * min
     */
    @Test
    public void test3() {
        long count = emps.stream()
                .filter(e -> e.getStatus().equals(Status.FREE))
                .count();
        System.out.println(count);

        System.out.println("------------------------------------------");

        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .max(Double::compare);
        System.out.println(max.get());

        System.out.println("------------------------------------------");

        Optional<Double> min = emps.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(min.get());

    }

    /**
     * 注意：流进行了终止操作后，不能再次使用
     */
    @Test
    public void test4() {
        Stream<Employee> stream = emps.stream().filter(e -> e.getStatus().equals(Status.FREE));
        System.out.println(stream.count());

        stream.map(Employee::getSalary).max(Double::compare);
    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) —— 可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test5() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 1、求和
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("------------------------------------------");

        // 2、累加
        Optional<Double> reduce = emps.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce.get());

        System.out.println("------------------------------------------");

        // 3、搜索名字中 "六" 出现的次数
        Optional<Integer> count = emps.stream()
                .map(Employee::getName)
                .flatMap(TestStreamMiddleOpApi::convertChar)
                .map(ch -> {
                    if (ch.equals('六')) {
                        return 1;
                    }
                    return 0;
                }).reduce(Integer::sum);
        System.out.println(count.get());

    }

    /**
     * collect——将流转换为其他形式。接收一个 Collector 接口的实现，用于给Stream中元素做汇总的方法
     */

    /**
     * 使用collect进行 集合转换操作
     */
    @Test
    public void test6() {
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("------------------------------------------");

        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("------------------------------------------");

        HashSet<String> hashSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }


    /**
     * 使用collect进行 聚合操作
     */
    @Test
    public void test7() {
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println("最高工资：" + max.get());

        System.out.println("------------------------------------------");

        Optional<Employee> min = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println("最低工资的员工：" + min.get());

        System.out.println("------------------------------------------");

        Double sumSalary = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("总工资：" + sumSalary);

        System.out.println("------------------------------------------");

        Double averageSalary = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("平均工资：" + averageSalary);

        System.out.println("------------------------------------------");

        Long count = emps.stream().collect(Collectors.counting());
        System.out.println("总员工数：" + count);

        System.out.println("------------------------------------------");

        DoubleSummaryStatistics ss = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(ss.getMax() + " - " + ss.getMin() + " - "
                + ss.getCount() + " - " + ss.getSum() + " - " + ss.getAverage());
    }

    /**
     * 使用collect进行 分组操作
     */
    @Test
    public void test8() {
        Map<Status, List<Employee>> statusMap = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(statusMap);
    }

    /**
     * 使用collect进行 多级分组操作
     */
    @Test
    public void test9() {
        Map<Status, Map<String, List<Employee>>> statusMapMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() >= 60) {
                        return "老年";
                    } else if (e.getAge() >= 35) {
                        return "中年";
                    } else {
                        return "成年";
                    }
                })));
        System.out.println(statusMapMap);
    }

    /**
     * 使用collect进行 分区操作
     */
    @Test
    public void test10() {
        Map<Boolean, List<Employee>> listMap = emps.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() >= 5000));
        System.out.println(listMap);
    }

    /**
     * 使用collect进行 其他操作
     */
    @Test
    public void test11() {

        String joinStr1 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining());
        System.out.println(joinStr1);

        String joinStr2 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "--", "---"));
        System.out.println(joinStr2);

        System.out.println("------------------------------------------");

        Optional<Double> sum = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));
        System.out.println(sum.get());

    }

}
