package com.fengx.mytest.base.random;

public class Test {
    public static void main(String[] args) {
        String s = RandomUUID.generateUuid(64);
        System.out.println(s);
        System.out.println(s.length());
    }
}
