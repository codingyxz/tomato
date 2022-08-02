package com.tomato.jvm;

/**
 * -XX:StringTableSize 可设置StringTable的长度
 *
 *
 * @author zhxy
 * @Date 2021/5/28 9:14 上午
 */
public class StringTest {

    public static void main(String[] args) {
//        String str = "a" + "b" + "c";
//        String str = "abc".intern();
//        System.out.println("我就是来打个酱油");
//
//        try{
//            Thread.sleep(1000000l);
//        }catch (Exception e){
//
//        }


        String s1 = new String("a") + new String("b");
//        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);
    }
}
