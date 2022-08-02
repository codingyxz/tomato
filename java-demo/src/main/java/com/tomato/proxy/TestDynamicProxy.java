package com.tomato.proxy;

import com.tomato.service.HelloService;
import com.tomato.service.impl.HelloServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhxy
 * @Date 2021/5/25 3:47 下午
 */
public class TestDynamicProxy {


    public static void main(String[] args) throws IOException {

        HelloService proxyInstance = (HelloService) Proxy.newProxyInstance(TestDynamicProxy.class.getClassLoader(), new Class[]{HelloService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("说我是光-----" + args.toString());
                return method.invoke(new HelloServiceImpl(), args);
            }
        });


        proxyInstance.sayHello("hello");

        System.in.read();

    }
}
