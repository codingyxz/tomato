package com.tomato.thread;

import net.openhft.affinity.AffinityLock;

/**
 * 绑核测试
 *
 * @Author zhxy
 * @Date 2023/3/24 4:05 下午
 * @Version V1.0
 **/
public class CupTest {

    public static void main(String[] args) {
        try (AffinityLock affinityLock = AffinityLock.acquireLock(3)) {
            // do some work while locked to a CPU.
            while (true) {
            }
        }

    }
}
