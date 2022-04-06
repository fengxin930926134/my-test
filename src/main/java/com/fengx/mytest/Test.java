package com.fengx.mytest;

import java.net.ConnectException;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        LocalDate parse = LocalDate.parse("1995-02-08");
        System.out.println(parse.toString());
        System.out.println("D30233475BE76016E0530100007F6C04".length());
    }
}
