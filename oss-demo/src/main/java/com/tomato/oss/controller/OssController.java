package com.tomato.oss.controller;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.tomato.oss.config.OssConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author zhxy
 * @Date 2023/3/16 11:35 上午
 * @Version V1.0
 **/

@Slf4j
@RestController
public class OssController {


    @Autowired
    private OssConfig ossConfig;
    @Autowired
    private OSS ossClient1;
    @Autowired
    private OSS ossClient2;

    @GetMapping("/oss/{dir}")
    public void getDir1File(HttpServletResponse response, @PathVariable(value = "dir") Integer dir, @RequestParam(value = "key") String key) throws IOException {
        OSS ossClient = 1 == dir ? ossClient1 : ossClient2;

        OSSObject object = ossClient.getObject(ossConfig.getBucket(), key);
        String contentType = object.getObjectMetadata().getContentType();
        log.info(JSONUtil.toJsonPrettyStr(object.getObjectMetadata()));
        response.setContentType("image/png");
        IOUtils.copy(object.getObjectContent(), response.getOutputStream());
    }


    @PostMapping("/oss/put/{dir}")
    public String putObject(@PathVariable(value = "dir") Integer dir, @RequestParam(value = "prefix") String prefix, @RequestParam("file") MultipartFile file) throws IOException {
        OSS ossClient = 1 == dir ? ossClient1 : ossClient2;
        String key = String.format("%s/%s", prefix, UUID.randomUUID());
        try (InputStream inputStream = file.getInputStream()) {

            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(bis.available());
            ossClient.putObject(ossConfig.getBucket(), key, bis, metadata);
        } catch (IOException e) {
            throw e;
        }
        System.out.println(dir + ":" + key);
        return key;
    }


}
