package com.tomato.reference;

import com.tomato.entity.User;

import java.lang.ref.WeakReference;

/**
 * @author zhxy
 * @Date 2021/6/1 5:05 下午
 */
public class WeakReferenceTest {
    public static void main(String[] args) {

        User user = new User();
        WeakReference<User> weak = new WeakReference<>(user);
        user = null;

        System.out.println(weak.get());

        System.gc();

        System.out.println(weak);
        System.out.println(weak.get());
    }
}
