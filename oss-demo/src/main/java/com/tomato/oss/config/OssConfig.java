package com.tomato.oss.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhxy
 * @Date 2023/3/16 11:27 上午
 * @Version V1.0
 **/

@Slf4j
@Configuration
@Getter
public class OssConfig {

    @Value("${endpoint:oss-cn-hangzhou.aliyuncs.com}")
    private String endpoint;
    @Value("${bucket:examplebucket-maycur}")
    private String bucket;

    @Bean(value = "ossClient1")
    public OSS ossClient1() {
        // dir1
        String accessKey = "LTAI5tLbXjJdwhiw6hjF9w7W";
        String accessSecret = "yGMiGDfBiS8nRmw03aCeFqxFsQMGpD";

        // 支持自定义域名
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSupportCname(true);
        // 创建OSSClient实例。
        return new OSSClientBuilder().build(endpoint, accessKey, accessSecret, clientBuilderConfiguration);
    }

    @Bean(value = "ossClient2")
    public OSS ossClient2() {
        // dir2
        String accessKey = "LTAI5t7ATQ9Pd5qXFScUUaGv";
        String accessSecret = "dxmDCHLb2qqrQkcGCdeCMMc8KSJ0Xi";

        // 支持自定义域名
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSupportCname(true);
        // 创建OSSClient实例。
        return new OSSClientBuilder().build(endpoint, accessKey, accessSecret, clientBuilderConfiguration);
    }
}
