package com.tomato.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @Author zhxy
 * @Date 2023/5/30 11:26 上午
 * @Version V1.0
 **/

@Slf4j
@Component
public class ListenerMessage implements StreamListener<String, MapRecord<String, String, String>> {


    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        log.info("接受到来自redis的消息，message id：[{}],stream：[{}]，body：[{}]", message.getId(), message.getStream(), message.getValue());
    }


}
