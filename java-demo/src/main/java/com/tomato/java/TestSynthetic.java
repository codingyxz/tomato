package com.tomato.java;

/**
 * @author zhxy
 * @Date 2021/6/9 8:25 下午
 */
public class TestSynthetic {

    public void test(){
        new ServiceInner();
    }

    public static class ServiceInner{
        private ServiceInner(){}
    }
}
