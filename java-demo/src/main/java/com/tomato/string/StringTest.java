package com.tomato.string;

import org.springframework.util.StringUtils;

import java.io.File;


/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/8/17 4:17 下午
 * @Version V1.0
 **/
public class StringTest {

    public static void main(String[] args) {

        System.out.println(String.format("temp%s%s.pdf", File.separator, "123"));


        Object str = null;
        System.out.println(String.valueOf(str));


        System.out.println(StringUtils.isEmpty(String.valueOf(str)));


        System.out.println((String) str);


    }


}
