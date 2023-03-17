package com.tomato.file;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;

/**
 * @Author zhxy
 * @Date 2023/3/14 3:52 下午
 * @Version V1.0
 **/
public class TypeTest {

    public static void main(String[] args) {

        MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
        try {
            MimeType mimeType = allTypes.forName("application/xlsx");
            System.out.println(mimeType.getExtension().substring(1));
        } catch (Exception ex) {

        }
    }
}
