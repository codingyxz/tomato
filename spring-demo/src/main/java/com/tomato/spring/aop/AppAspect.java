package com.tomato.spring.aop;

import com.alibaba.fastjson.JSON;
import com.tomato.spring.annotation.EagleEye;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author zhxy
 * @Date 2021/6/11 9:36 上午
 */

@Component
@Aspect
public class AppAspect {

    @Pointcut("@annotation(com.tomato.spring.annotation.EagleEye)")
    public void eagleEye() {
    }


    @Around("eagleEye()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 请求开始时间戳
        long begin = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Method method = methodSignature.getMethod();
        EagleEye eagleEye = method.getAnnotation(EagleEye.class);

        // 接口描述信息
        String desc = eagleEye.desc();

        System.out.println("========================请求开始===========================");
        // 请求连接
        System.out.println("请求链接 ： " + request.getRequestURL().toString());
        // 接口描述
        System.out.println("接口描述 ： " + desc);
        // 请求类型
        System.out.println("请求类型 : " + request.getMethod());
        // 请求方法
        System.out.println("请求方法 ： " + signature.getDeclaringTypeName() + "." + signature.getName());
        // 请求入参
        System.out.println("请求入参 : " + JSON.toJSONString(pjp.getArgs()));

        Object result = pjp.proceed();

        // 请求结束时间
        long end = System.currentTimeMillis();
        // 请求耗时
        System.out.println("请求耗时 ： " + (end - begin) + " ms");
        // 请求返回
        System.out.println("请求返回 ： " + JSON.toJSONString(result));
        System.out.println("=========================请求结束===================================");
        return result;
    }

}
