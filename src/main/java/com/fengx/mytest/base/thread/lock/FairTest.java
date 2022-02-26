package com.fengx.mytest.base.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 */
public class FairTest {

    private static final Lock lock = new ReentrantLock(true);
    public static void main(String[] args) {
        new Thread(() -> test(), "线程A").start();
        new Thread(() -> test(), "线程B").start();
        new Thread(() -> test(), "线程C").start();
    }

    private static void test() {
        for (int i = 0; i < 2; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                lock.unlock();
            }
        }

    }
}
