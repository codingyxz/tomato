package com.tomato.config;

import com.tomato.listener.ListenerMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;

/**
 * @Author zhxy
 * @Date 2023/5/30 11:28 上午
 * @Version V1.0
 **/

@Slf4j
@Configuration
public class RedisConfig {

    @Autowired
    private ListenerMessage streamListener;


    @Bean
    public Subscription subscription(RedisConnectionFactory factory) {
        return getInstance(factory, "g5", "c1", "stream-key");
    }

    private Subscription getInstance(RedisConnectionFactory factory, String group, String consumer, String key) {
        var options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .build();

        var listenerContainer = StreamMessageListenerContainer.create(factory, options);
        var subscription = listenerContainer.receiveAutoAck(
                Consumer.from(group, consumer),
                StreamOffset.create(key, ReadOffset.lastConsumed())
                , streamListener);

        listenerContainer.start();
        return subscription;
    }


}
