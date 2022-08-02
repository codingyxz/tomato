package com.tomato.proxy;

import com.tomato.service.impl.ByeServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author zhxy
 * @Date 2021/5/25 4:59 下午
 */
public class TestCGLibProxy implements MethodInterceptor {


    private Object target; // 目标对象
    public Object bind(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(this.target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类(代理对象)
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("说我是电-----" + method.getName());
        return methodProxy.invokeSuper(o,args);
    }


    public static void main(String[] args) throws IOException {
        TestCGLibProxy cgLibProxy = new TestCGLibProxy();
        ByeServiceImpl byeService = (ByeServiceImpl)cgLibProxy.bind(new ByeServiceImpl());
        byeService.sayBye("bye");
        byeService.testNoStatic("bye");
        System.in.read();

    }
}
