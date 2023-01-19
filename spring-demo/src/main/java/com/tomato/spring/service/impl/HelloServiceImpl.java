package com.tomato.spring.service.impl;

import com.tomato.spring.annotation.EagleEye;
import com.tomato.spring.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhxy
 * @Date 2021/6/11 10:33 上午
 */

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @EagleEye(desc = "helloService中的sayHello")
    @Override
    public String sayHello(String name) {
        log.info("-----");
        return name;
    }
}
