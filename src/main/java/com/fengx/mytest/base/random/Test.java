package com.fengx.mytest.base.random;

public class Test {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(RandomUtils.randomInts(100, 2));
        System.out.println(System.currentTimeMillis() - l);
    }
}
