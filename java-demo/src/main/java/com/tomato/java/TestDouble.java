package com.tomato.java;

/**
 * @Author zhxy
 * @Date 2023/4/24 11:45 上午
 * @Version V1.0
 **/
public class TestDouble {

    private static int current = 0;
    private static int total = 5;


    public static void main(String[] args) {

//        double ceil = Math.ceil(6  / 5);
//        System.out.println(new Double(ceil).longValue());


        while (next()){
            System.out.println(current);
        }



    }

    public static boolean next(){
        return ++current > total ? false : true;
    }


}
