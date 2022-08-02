package com.tomato.reference;


import com.tomato.entity.User;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author zhxy
 * @Date 2021/6/1 3:17 下午
 */
public class PhantomReferenceTest {

    public static void main(String[] args) throws Exception {

        ReferenceQueue<Object> queue = new ReferenceQueue<>();

        User user = new User();
        PhantomReference<User> phantom = new PhantomReference<>(user,queue);
        user = null;


        System.out.println(phantom.get());
        System.out.println(queue.poll());
        System.gc();
        Thread.sleep(1000l);
        System.out.println(queue.poll());
        System.out.println(phantom.get());



    }

}
