package com.tomato.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * @author zhxy
 * @Date 2021/6/8 12:46 下午
 */
public class FirstProducer {


    public static void main(String[] args) {


        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
//        properties.setProperty(ProducerConfig.)


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);


    }


}
