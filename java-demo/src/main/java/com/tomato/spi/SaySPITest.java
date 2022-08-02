package com.tomato.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author zhxy
 * @Date 2021/6/15 9:16 上午
 */
public class SaySPITest {

    public static void main(String[] args) {


        ServiceLoader<SaySPI> saySPIS = ServiceLoader.load(SaySPI.class);
        Iterator<SaySPI> iterator = saySPIS.iterator();
        while (iterator.hasNext()){
            SaySPI saySPI = iterator.next();
            saySPI.say("world");
        }

    }
}
