package com.tomato.java;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author zhxy
 * @Date 2021/5/25 9:30 上午
 */
public class TestSystem {


    @Test
    public void testProperty(){
        Properties properties = System.getProperties();

        Set<Object> objects = properties.keySet();
        for (Object o : objects) {

            System.out.println(o.toString() + "-----" + properties.getProperty(o.toString()));
        }
    }


    @Test
    public void testEnvironment(){
        Map<String, String> env = System.getenv();
        Set<String> set = env.keySet();
        for (String s : set) {
            System.out.println(s + "--------" + env.get(s));
        }

    }

}
