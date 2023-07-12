package com.tomoto.ebank.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @Author zhxy
 * @Date 2023/7/10 2:41 下午
 * @Version V1.0
 **/
public class Base64Utils {
    private static final Logger logger = LoggerFactory.getLogger(Base64Utils.class);

    /**
     * @Description 文件转base64字符串
     */
    public static String fileToBase64(String path) {
        String base64 = null;
        FileInputStream in = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            File file = new File(path);
            in = new FileInputStream(file);
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = in.read(b, 0, b.length)) != -1) {
                outputStream.write(b, 0, len);
            }
            byte[] buffer = outputStream.toByteArray();
            base64 = Base64.getEncoder().encodeToString(buffer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return base64;
    }
}
