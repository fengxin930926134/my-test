package com.fengx.mytest.base.thread.asyn;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程异步操作实验
 */
public class Test {

    private static Map<Integer, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        //启动时间操作类，每隔一段时间取值
        new Timer().schedule(new MyTask(), 200, 500);
        int i = 1;
        while(i <= 1000){
            System.out.println(Thread.currentThread().getName());
            map.put(i, "content msg" + i);
            i++;
            Thread.sleep(50);
        }
    }

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            if (map.size() > 0) {
                Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
                // Hashtable是给每个public方法加上同步锁当执行到table.entrySet()时它获得了锁，执行到foreach遍历时已经不是调用table的方法了，已经释放了锁，所以也是不行的。
                for (Map.Entry<Integer, String> entry : entrySet) {
                    System.out.println(entry.getValue());
                }
                map.clear();
            }

        }
    }
}
