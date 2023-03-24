package com.tomato.path;

import java.nio.file.Paths;
import java.util.UUID;

/**
 * @Author zhxy
 * @Date 2023/3/22 2:23 下午
 * @Version V1.0
 **/
public class TestPath {

    public static void main(String[] args) {

        String prefix = "r";

        System.out.println(Paths.get(prefix, "123", UUID.randomUUID().toString()).toString());

    }
}
