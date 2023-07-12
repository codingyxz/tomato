package com.tomato.tika;

import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.File;
import java.io.IOException;

/**
 * @Author zhxy
 * @Date 2023/7/11 2:50 下午
 * @Version V1.0
 **/
public class TikaTest {


    public static void main(String[] args) throws IOException, MimeTypeException {

//        String filePath = "/Users/heronsbill/Downloads/gaeghrhhrhthth";
        String filePath = "/Users/heronsbill/Downloads/tika";

        File rootFile = new File(filePath);

        //用tika工具类检测，下载网络文件临时文件的文件类型
        Tika tika = new Tika();
        for (File file : rootFile.listFiles()) {

            String detectType = null;
            String type = null;
            try {
                detectType = tika.detect(file);
                MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
                MimeType mimeType = allTypes.forName(detectType);
                type = mimeType.getExtension().substring(1);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(file.getName() + "----------" + detectType + "-----------" + type);

        }


    }
}
