package com.tomato.service.impl;

/**
 * @author zhxy
 * @Date 2021/5/25 5:03 下午
 */
public class ByeServiceImpl {

    public void sayBye(String bye){
        System.out.println(bye);
    }


    public static void sayWithStatic(String bye){
        System.out.println(bye);
    }

    public static final void testStaticFinal(String bye){
        System.out.println(bye);
    }

    public  final void testNoStatic(String bye){
        System.out.println(bye);
    }


}
