package com.tomato.spring.config;

import com.tomato.spring.entity.PeopleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/9/13 9:12 上午
 * @Version V1.0
 **/

@Configuration
public class DemoConfig {

    @Bean
    public PeopleEntity peopleEntity() {
        PeopleEntity peopleEntity = new PeopleEntity();
        peopleEntity.setAddress("西溪银泰");
        peopleEntity.setAge(18);
        peopleEntity.setName("maycur");
        peopleEntity.setTel("131111111111");
        return peopleEntity;
    }

}
