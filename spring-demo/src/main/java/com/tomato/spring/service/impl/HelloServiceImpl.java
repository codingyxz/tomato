package com.tomato.spring.service.impl;

import com.tomato.spring.annotation.EagleEye;
import com.tomato.spring.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author zhxy
 * @Date 2021/6/11 10:33 上午
 */
@Service
public class HelloServiceImpl implements HelloService {

    @EagleEye(desc = "helloService中的sayHello")
    @Override
    public String sayHello(String name) {
        return name;
    }
}
