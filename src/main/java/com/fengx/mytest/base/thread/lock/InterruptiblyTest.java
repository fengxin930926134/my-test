package com.fengx.mytest.base.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 响应中断就是一个线程获取不到锁，不会傻傻的一直等下去，ReentrantLock会给予一个中断回应。
 *
 * lock 优先考虑获取锁，待获取锁成功后，才响应中断。
 * lockInterruptibly 优先考虑响应中断，而不是响应锁的普通获取或重入获取。
 */
public class InterruptiblyTest {

    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Thread threadA = getThreadA("线程A");
        Thread threadB = getThreadB("线程B");
        threadA.start();
        threadB.start();
        while (true) {
            if (System.currentTimeMillis() - time > 1000) {
                // 等待超时则主动中断
                threadB.interrupt();
                break;
            }
        }
    }

    private static Thread getThreadA(String name) {
        return new Thread(() -> {
            System.out.println(name + "线程开始");
            try {
                System.out.println(name + "获取锁1");
                lock1.lockInterruptibly();
                System.out.println(name + "获取锁1完成");
                Thread.sleep(500);
                System.out.println(name + "获取锁2");
                lock2.lockInterruptibly();
                System.out.println(name + "获取锁2完成");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                    System.out.println(name + "释放锁1");
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                    System.out.println(name + "释放锁2");
                }
                System.out.println(name + "线程结束");
            }
        }, name);
    }

    private static Thread getThreadB(String name) {
        return new Thread(() -> {
            System.out.println(name + "线程开始");
            try {
                System.out.println(name + "获取锁2");
                lock2.lockInterruptibly();
                System.out.println(name + "获取锁2完成");
                Thread.sleep(500);
                System.out.println(name + "获取锁1");
                lock1.lockInterruptibly();
                System.out.println(name + "获取锁1完成");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                    System.out.println(name + "释放锁2");
                }
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                    System.out.println(name + "释放锁1");
                }
                System.out.println(name + "线程结束");
            }
        }, name);
    }
}
