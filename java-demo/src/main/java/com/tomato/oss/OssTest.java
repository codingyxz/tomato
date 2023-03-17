package com.tomato.oss;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.GetBucketPolicyResult;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.SetBucketAclRequest;
import com.aliyun.oss.model.SetBucketPolicyRequest;

import java.io.InputStream;

/**
 * @Author zhxy
 * @Date 2023/3/16 10:09 上午
 * @Version V1.0
 **/
public class OssTest {

    public static void main(String[] args) {
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String bucket = "examplebucket-maycur";

        // dir1
//        String accessKey = "LTAI5tLbXjJdwhiw6hjF9w7W";
//        String accessSecret = "yGMiGDfBiS8nRmw03aCeFqxFsQMGpD";

        // dir2
        String accessKey = "LTAI5t7ATQ9Pd5qXFScUUaGv";
        String accessSecret = "dxmDCHLb2qqrQkcGCdeCMMc8KSJ0Xi";


        // 支持自定义域名
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSupportCname(true);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret, clientBuilderConfiguration);

        GetBucketPolicyResult bucketPolicy = ossClient.getBucketPolicy(bucket);

        ObjectListing objectListing = ossClient.listObjects(bucket);

        String prefix = objectListing.getPrefix();

//        // 查看bucket信息
//        BucketInfo bucketInfo = ossClient.getBucketInfo(bucket);
//
//        System.out.println(bucketInfo.getBucket().getLocation());
//        System.out.println(bucketInfo.getBucket().getCreationDate());
//        System.out.println(bucketInfo.getBucket().getOwner());
//        System.out.println(JSONUtil.toJsonPrettyStr(bucketInfo));


//        OSSObject object = ossClient.getObject(bucket, "dir1/增值税普通发票_16347710.png");
//        OSSObject object = ossClient.getObject(bucket, "dir2/增值税普通发票_20623615.png");


//        InputStream objectContent = object.getObjectContent();
//        try {
//
//        }catch (Exception ex){
//
//        }
//
//        System.out.println(object.getResponse().getUri());
//        System.out.println(object.getObjectMetadata().getContentType());

    }
}
