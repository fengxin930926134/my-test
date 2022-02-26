package com.fengx.mytest.base.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 类可用于两个线程之间交换信息
 *
 *
 * JDK 1.5 开始 JUC 包下提供的 Exchanger 类可用于两个线程之间交换信息。Exchanger 对象可理解为一个包含2个格子的容器，通过调用 exchanger 方法向其中的格子填充信息，当两个格子中的均被填充信息时，自动交换两个格子中的信息，然后将交换的信息返回给调用线程，从而实现两个线程的信息交换。
 *
 * 功能看似简单，但这在某些场景下是很有用处的，例如游戏中两个玩家交换装备；交友软件男女心仪对象匹配。
 *
 * 下面简单模拟下两个玩家交换装备的场景。
 */
public class Test {

  public static void main(String[] args) throws InterruptedException {

    Exchanger<String> exchanger = new Exchanger<>();

    new Thread(() -> {
      String str = null;
      try {
        str = exchanger.exchange("屠龙刀");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("交易成功，" + Thread.currentThread().getName() + "获得" + str);
    }, "周芷若").start();

    new Thread(() -> {
      String str = null;
      try {
        str = exchanger.exchange("倚天剑");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("交易成功，" + Thread.currentThread().getName() + "获得" + str);
    }, "张无忌").start();
  }
}