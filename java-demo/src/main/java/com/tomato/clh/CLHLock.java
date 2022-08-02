package com.tomato.clh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhxy
 * @Date 2021/6/2 3:39 下午
 */
public class CLHLock implements Lock {

    // 尾巴，是所有线程共有的一个。所有线程进来后，把自己设置为tail
    private final AtomicReference<QNode> tail;
    // 前驱节点，每个线程独有一个。
    private final ThreadLocal<QNode> myPred;
    // 当前节点，表示自己，每个线程独有一个。
    private final ThreadLocal<QNode> myNode;

    public CLHLock() {
        this.tail = new AtomicReference<>(new QNode());
        this.myNode = ThreadLocal.withInitial(QNode::new);
        this.myPred = new ThreadLocal<>();
    }


    @Override
    public void lock() {
        // 获取当前线程的代表节点
        QNode node = myNode.get();
        // 将自己的状态设置为true表示获取锁。
        node.locked = true;
        // 将自己放在队列的尾巴，并且返回以前的值。第一次进将获取构造函数中的那个new QNode
        QNode pred = tail.getAndSet(node);
        // 把旧的节点放入前驱节点。
        myPred.set(pred);
        // 在等待前驱节点的locked域变为false，这是一个自旋等待的过程
        int i= 0;
        while (pred.locked) {
            if(i == 0 ){
                i++;
                peekNodeInfo(i+"等待获取锁");
            }
        }
        // 打印myNode、myPred的信息
//        peekNodeInfo("acquire lock success ");
    }

    //  thread-1 acquire lock success. myNode(QNode_2_locked:true), myPred(QNode_1_locked:false)
    private void peekNodeInfo(String text) {
        System.out.println(Thread.currentThread().getName()
                + " " + text + ". " +
                "tail=" + tail.get() + ", " +
                "myNode" + myNode.get() + ", " +
                "myPred" + myPred.get());
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // unlock. 获取自己的node。把自己的locked设置为false。

        QNode node = myNode.get();
        node.locked = false;
//        peekNodeInfo("释放锁");
//        myNode.set(myPred.get());
//        System.out.println(Thread.currentThread().getName() + "--" + myNode.get() + "--" + myPred.get());
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
