package com.tomato.java;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhxy
 * @Date 2023/2/15 1:44 下午
 * @Version V1.0
 **/

@Slf4j
public class TestSet {


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        String str = "111;";
        String[] split = str.split(";");
        int i = 0;
        while (i < 1000000) {
            try {
                System.out.println(split[1]);
            } catch (Exception ex) {
                log.error("---{}--{}", i, ex.getMessage(), ex);
                if(ex.getMessage() == null){
                    break;
                }
            }
            i++;
        }

        // 41984  41984  41984  41984  41984  41984
        // 44032
        // 48128
        // 43008

    }
}
