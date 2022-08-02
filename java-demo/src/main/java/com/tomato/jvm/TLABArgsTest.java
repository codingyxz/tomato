package com.tomato.jvm;

/**
 *
 *
 * 执行： jinfo -flag UseTLAB 2649（pid），查看TLAB开启状态
 *
 * @author zhxy
 * @Date 2021/5/27 12:49 下午
 */
public class TLABArgsTest {

    public static void main(String[] args) {


        System.out.println("我就是来打个酱油");

        try {
            Thread.sleep(1000000);
        }catch (Exception e){

        }
    }
}
