package com.tomato.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zhxy
 * @Date 2021/6/11 10:05 上午
 */

@Retention(RetentionPolicy.RUNTIME)  //注解生命周期
@Target(ElementType.METHOD)   //注解作用用于修饰方法
@Documented                   // 表明该注解可以被javadoc工具记录
public @interface EagleEye {

    /**
     * 接口描述
     */
    String desc() default "";
}
