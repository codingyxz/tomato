package com.tomato.spring.controller;

import com.alibaba.fastjson.JSON;
import com.tomato.spring.annotation.EagleEye;
import com.tomato.spring.param.TimeParam;
import com.tomato.spring.service.HelloService;
import com.tomato.spring.vo.TimeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author zhxy
 * @Date 2021/6/11 10:27 上午
 */

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @EagleEye(desc = "调用hello接口")
    @RequestMapping("/hello")
    public String hello(String name) {

        String hello = helloService.sayHello(name);
        return "I get ----" + hello;
    }


    @PostMapping("/show")
    public TimeVo showTime(@RequestBody TimeParam timeParam) {
        log.info(JSON.toJSONString(timeParam));
        TimeVo timeVo = new TimeVo();
        BeanUtils.copyProperties(timeParam,timeVo);
        timeVo.setName(UUID.randomUUID().toString());
        return timeVo;
    }

}
