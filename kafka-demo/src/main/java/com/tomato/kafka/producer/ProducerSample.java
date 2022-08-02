package com.tomato.kafka.producer;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author zhxy
 * @Date 2021/6/8 7:58 下午
 */
public class ProducerSample {
    // topic
    private final static String TOPIC_NAME = "zhxy-topic";


    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger root = loggerContext.getLogger("root");
        root.setLevel(Level.INFO);
    }

    public static void main(String[] args) throws Exception {

        // 异步发送
//        producerSend();
        // 异步阻塞发送
//        producerSyncSend();
        // 异步回调发送
        producerSendWithCallback();
    }


    /**
     * 异步回调
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void producerSendWithCallback() throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProperties());

        for (int i = 0; i < 100; i++) {
            // 组织消息对象
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(TOPIC_NAME, "key-" + i, "value-" + i);

            Future<RecordMetadata> send = producer.send(producerRecord, (metadata, exception) -> {
                System.out.println("partition : " + metadata.partition() + ", offset : " + metadata.offset());
            });

        }

        producer.close();
    }


    public static void producerSyncSend() throws ExecutionException, InterruptedException {

        KafkaProducer<String, String> producer = new KafkaProducer<>(getProperties());

        for (int i = 0; i < 10; i++) {
            // 组织消息对象
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(TOPIC_NAME, "key-" + i, "value-" + i);

            Future<RecordMetadata> send = producer.send(producerRecord);
            RecordMetadata recordMetadata = send.get();

            System.out.println("partition : " + recordMetadata.partition() + ", offset : " + recordMetadata.offset());
        }

        producer.close();
    }


    /**
     * 发送消息
     */
    public static void producerSend() {

        KafkaProducer<String, String> producer = new KafkaProducer<>(getProperties());

        for (int i = 0; i < 10; i++) {
            // 组织消息对象
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(TOPIC_NAME, "key-" + i, "value-" + i);

            producer.send(producerRecord);
        }

        producer.close();

    }


    /**
     * 配置信息
     *
     * @return
     */
    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "0");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16348");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "1");
        properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");

        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.tomato.kafka.producer.SamplePartition");

        return properties;
    }
}
