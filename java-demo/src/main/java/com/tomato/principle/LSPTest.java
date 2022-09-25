package com.tomato.principle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc 里氏替换
 * @Author zhxy
 * @Date 2022/9/25 1:16 下午
 * @Version V1.0
 **/
public class LSPTest {
    public static void main(String[] args) {
        Son son = new Son();
        Map map = new HashMap();
//        son.doSomething(new HashMap());
//        son.doSomething(map);

        son.testSomething(new HashMap());
        son.testSomething(map);
    }
}


class Father {
    public Collection doSomething(HashMap map) {
        System.out.println("父类被执行了");
        return map.values();
    }

    public Collection testSomething(Map map) {
        System.out.println("父类被执行了");
        return map.values();
    }

}

class Son extends Father {
//    public Collection doSomething(Map map) {
//        System.out.println("子类被执行了");
//        return map.values();
//    }

    public List testSomething(Map map) {
        System.out.println("子类被执行了");
        return new ArrayList();
    }


}
