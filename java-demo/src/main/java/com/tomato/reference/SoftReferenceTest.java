package com.tomato.reference;

import com.tomato.entity.User;

import java.lang.ref.SoftReference;

/**
 * @author zhxy
 * @Date 2021/6/1 5:09 下午
 */
public class SoftReferenceTest {
    public static void main(String[] args) {

        User user = new User();
        SoftReference<User> soft = new SoftReference<>(user);
        user = null;

        System.out.println(soft.get());

        System.gc();

        System.out.println(soft.get());



    }
}
