//package com.tomato.aqs;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
///**
// * @author zhxy
// * @Date 2021/6/1 2:06 下午
// */
//public class UnsafeTest {
//
//
//    private int i = 0;
//
//    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        //获取Unsafe实例
//        Field f = Unsafe.class.getDeclaredField("theUnsafe"); // Internal reference
//        f.setAccessible(true);//设置为true，通过反射获取私有变量的时候，会忽略访问修饰符的检查
//        Unsafe unsafe = (Unsafe) f.get(null);
//
//        //获取字段i在内存中偏移量
//        long offset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("i"));
//
//        //创建对象实例，设置字段的值
//        UnsafeTest unsafeTest = new UnsafeTest();
//        unsafe.putInt(unsafeTest, offset, 100);
//
//        UnsafeTest unsafeDemo = new UnsafeTest();
//        unsafe.putInt(unsafeDemo,offset,200);
//
//
//        //打印结果
//        System.out.println(unsafeDemo.i);
//        System.out.println(unsafeTest.i);
//
//    }
//}
