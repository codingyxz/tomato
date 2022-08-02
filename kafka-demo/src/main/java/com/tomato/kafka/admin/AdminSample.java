package com.tomato.kafka.admin;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * kafka的五类api
 * 1、AdminClient API:允许管理和检测Topic、broker以及其他kafka对象
 * 2、Producer API:发送消息到一个或多个topic
 * 3、Consumer API:订阅一个或多个topic，并处理产生的消息
 * 4、Stream API:高效的将输入流转换到输出流
 * 5、Connector API:从一些源系统或应用程序中拉取数据到kafka（常用于DB的交互）
 *
 * @author zhxy
 * @Date 2021/6/8 4:17 下午
 */
public class AdminSample {

    private final static String TOPIC_NAME = "zhxy-topic";


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println(adminClient());
//        createTopic();

//        deleteTopic();
//        topLists();

//        descConfig();

//        descTopic();

//        incrPartitions(2);

    }


    /**
     * 增加partition数量
     * @param partitions
     */
    public static void incrPartitions(int partitions) throws ExecutionException, InterruptedException {

        AdminClient client = adminClient();

        HashMap<String, NewPartitions> map = new HashMap<>();
        NewPartitions newPartitions = NewPartitions.increaseTo(partitions);
        map.put(TOPIC_NAME,newPartitions);

        CreatePartitionsResult createPartitionsResult = client.createPartitions(map);
        createPartitionsResult.all().get();

    }


    /**
     * 获取topic的描述信息
     */
    public static void descTopic() throws ExecutionException, InterruptedException {

        AdminClient client = adminClient();

        DescribeTopicsResult describeTopicsResult = client.describeTopics(Arrays.asList(TOPIC_NAME));
        Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
        stringTopicDescriptionMap.entrySet().stream().forEach((entry) -> {
            System.out.println("name : " + entry.getKey() + ", desc : " + entry.getValue());
        });


        /**
         * name : zhxy-topic,
         * desc : (name=zhxy-topic, internal=false,
         *         partitions=(partition=0,
         *                     leader=localhost:9092 (id: 0 rack: null),
         *                     replicas=localhost:9092 (id: 0 rack: null),
         *                     isr=localhost:9092 (id: 0 rack: null)),
         *                     authorizedOperations=null
         *                     )
         */

    }


    /**
     * 获取config的描述信息
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void descConfig() throws ExecutionException, InterruptedException {

        AdminClient client = adminClient();

        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, TOPIC_NAME);


        DescribeConfigsResult describeConfigsResult = client.describeConfigs(Arrays.asList(configResource));

        Map<ConfigResource, Config> resourceConfigMap = describeConfigsResult.all().get();

        resourceConfigMap.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey() + "-----" + entry.getValue());
        });

        /**
         * ConfigResource(type=TOPIC, name='zhxy-topic')-----
         * Config(entries=[
         *          ConfigEntry(name=compression.type, value=producer, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=leader.replication.throttled.replicas, value=, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=message.downconversion.enable, value=true, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=min.insync.replicas, value=1, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=segment.jitter.ms, value=0, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=cleanup.policy, value=delete, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=flush.ms, value=9223372036854775807, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=follower.replication.throttled.replicas, value=, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=segment.bytes, value=1073741824, source=STATIC_BROKER_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=retention.ms, value=604800000, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=flush.messages, value=9223372036854775807, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=message.format.version, value=2.7-IV2, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=max.compaction.lag.ms, value=9223372036854775807, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=file.delete.delay.ms, value=60000, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=max.message.bytes, value=1048588, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=min.compaction.lag.ms, value=0, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=message.timestamp.type, value=CreateTime, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=preallocate, value=false, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=min.cleanable.dirty.ratio, value=0.5, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=index.interval.bytes, value=4096, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=unclean.leader.election.enable, value=false, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=retention.bytes, value=-1, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=delete.retention.ms, value=86400000, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=segment.ms, value=604800000, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=message.timestamp.difference.max.ms, value=9223372036854775807, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
         *          ConfigEntry(name=segment.index.bytes, value=10485760, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[])
         *        ])
         */
    }


    /**
     * 删除topic
     */
    public static void deleteTopic() throws ExecutionException, InterruptedException {
        AdminClient client = adminClient();
        DeleteTopicsResult deleteTopics = client.deleteTopics(Arrays.asList(TOPIC_NAME));
        deleteTopics.all().get();
    }


    /**
     * 获取topic列表
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void topLists() throws ExecutionException, InterruptedException {

        AdminClient client = adminClient();

        ListTopicsResult topicsResult = client.listTopics();
        topicsResult.names().get().stream().forEach(System.out::println);

        System.out.println("**********************************************");

        ListTopicsOptions topicsOptions = new ListTopicsOptions();
        topicsOptions.listInternal(true);
        ListTopicsResult listTopicsResult = client.listTopics(topicsOptions);
        listTopicsResult.listings().get().stream().forEach(System.out::println);


    }


    /**
     * 创建topic
     */
    public static void createTopic() {
        AdminClient client = adminClient();

        NewTopic newTopic = new NewTopic(TOPIC_NAME, 1, (short) 1);
        CreateTopicsResult topics = client.createTopics(Arrays.asList(newTopic));
        System.out.println(topics.values());
        client.close();
    }


    /**
     * 获取adminClient
     *
     * @return
     */
    public static AdminClient adminClient() {

        Properties properties = new Properties();
        // 配置kafka集群连接
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }
}
