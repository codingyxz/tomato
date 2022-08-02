package com.tomato.spring.controller;
import com.tomato.spring.annotation.EagleEye;
import com.tomato.spring.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhxy
 * @Date 2021/6/11 10:27 上午
 */

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @EagleEye(desc = "调用hello接口")
    @RequestMapping("/hello")
    public String hello(String name){

        String hello = helloService.sayHello(name);
        return "I get ----" + hello;
    }
}
