package com.tomato.obj;

import com.tomato.entity.User;

import java.util.HashMap;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/11/2 1:58 下午
 * @Version V1.0
 **/
public class TestObj {

    public static void main(String[] args) {

        HashMap<Object, Object> map = new HashMap<>();

        Object obj = new Object();

        User user = new User();


        System.out.println(map.getClass().isAssignableFrom(Object.class));
        System.out.println(obj.getClass().isAssignableFrom(Object.class));

        System.out.println(user.getClass().isAssignableFrom(Object.class));
    }
}
