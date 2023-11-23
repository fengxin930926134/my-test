package com.fengx.mytest.other;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);

        System.out.println(4/(double)65534);
        System.out.println((int)Math.ceil(4/(double)65534));

        List<List<Integer>> partition = Lists.partition(integers, 65534);
        System.out.println(partition);
    }

}
