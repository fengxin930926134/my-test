package com.fengx.mytest.base.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * java8 steam流实验
 */
public class Test {

    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("com");
        lists.add("admin");
        lists.add("dawdwa");
        // steam collect最终会从Stream产生新值，拥有终止操作。
        // 过滤
        Set<String> test = lists.stream().filter(f -> f.equals("com")).collect(Collectors.toSet());
        System.out.println(test);

        // stream & parallelStream
        long t0 = System.nanoTime();

        //初始化一个范围100万整数流,求能被2整除的数字，toArray()是终点方法

        int a[]= IntStream.range(0, 1_000_000).filter(p -> p % 2==0).toArray();

        long t1 = System.nanoTime();

        //和上面功能一样，这里是用并行流来计算

        int b[]=IntStream.range(0, 1_000_000).parallel().filter(p -> p % 2==0).toArray();

        long t2 = System.nanoTime();

        // 当数据量庞大的时候并行流才比顺序流快
        System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);

        // 使用Predicate 断定式子 这是一个实用的接口—>其中的实用方法指的是test方法
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.length() > 4);


        // 可以用and()、or()和xor()逻辑函数来合并Predicate，
        // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        languages.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
    }

    public static <T> void filter(List<T> names, Predicate<T> condition) {
        names.stream().filter(condition).forEach((name) -> {
            System.out.println(name + " ");
        });
    }
}
