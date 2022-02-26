package com.fengx.mytest.base.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Test {

    private static final Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(() -> test(), "线程A").start();
        new Thread(() -> test(), "线程B").start();
    }

    private static void test() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取了锁");
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }

    }
}
