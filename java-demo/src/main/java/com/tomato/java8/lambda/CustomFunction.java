package com.tomato.java8.lambda;

@FunctionalInterface
public interface CustomFunction {

    /**
     * 对入参进行运算
     *
     * @param num
     * @return
     */
    Integer getValue(Integer num);
}
