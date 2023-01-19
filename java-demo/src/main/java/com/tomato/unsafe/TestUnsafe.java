//package com.tomato.unsafe;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//import java.util.Arrays;
//
///**
// * @author zhxy
// * @Date 2021/6/17 7:30 下午
// */
//public class TestUnsafe {
//
//    public static void main(String[] args) throws Exception {
//
//
//        Integer[] arr = {2, 5, 1, 8, 10};
//
//        Unsafe unSafe = getUnSafe();
//        int baseOffset = unSafe.arrayBaseOffset(Integer[].class);
//        int indexScale = unSafe.arrayIndexScale(Integer[].class);
//
//        // 获取
//        Object o = unSafe.getObjectVolatile(arr, (2 * indexScale) + baseOffset);
//        System.out.println(o);
//
//        // 设置
//        unSafe.putOrderedObject(arr, (2 * indexScale) + baseOffset, 100);
//        System.out.println(Arrays.toString(arr));
//
//        // cas
//        boolean b = unSafe.compareAndSwapObject(arr, (2 * indexScale) + baseOffset, 100, 101);
//        System.out.println(b);
//        System.out.println(Arrays.toString(arr));
//
//
//        int a = 31 - Integer.numberOfLeadingZeros(indexScale);
//        a <<= a;
//        System.out.println(a == 2 * indexScale);
//        // 获取
//        o = unSafe.getObjectVolatile(arr, a + baseOffset);
//        System.out.println(o);
//
//
//    }
//
//
//    public static Unsafe getUnSafe() throws Exception {
//        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//        theUnsafe.setAccessible(true);
//        return (Unsafe) theUnsafe.get(null);
//    }
//}
