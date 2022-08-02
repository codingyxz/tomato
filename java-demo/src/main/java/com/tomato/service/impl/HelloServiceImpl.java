package com.tomato.service.impl;

import com.tomato.service.HelloService;

/**
 * @author zhxy
 * @Date 2021/5/25 3:58 下午
 */
public class HelloServiceImpl  implements HelloService {
    @Override
    public void sayHello(String word) {
        System.out.println("Hello -- " + word);
    }


}
