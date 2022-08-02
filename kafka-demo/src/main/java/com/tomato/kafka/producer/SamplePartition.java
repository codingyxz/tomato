package com.tomato.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 自定义分区策略
 *
 * @author zhxy
 * @Date 2021/6/8 8:52 下午
 */
public class SamplePartition implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        /**
         * key-1
         * key-2
         * key-3
         */
        String keyInt = key.toString().substring(4);
        System.out.println("keyInt : " + keyInt);

        int res = Integer.parseInt(keyInt)%2;
        return res;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
