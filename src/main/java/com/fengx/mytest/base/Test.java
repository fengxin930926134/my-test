package com.fengx.mytest.base;

import com.fengx.mytest.springboot.response.Response;
import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        System.out.println(add(1, 2));
//
        Point<String> stringPoint = new Point<>();
//        stringPoint.setVar("test");
//        Point<String> stringPoint2 = new Point<>();
//        System.out.println(stringPoint2.getVar());
//
//        System.out.println(stringPoint2.equals(stringPoint));
//
//        Test.Point.hhh();

        System.out.println(Test.class.getName());
        Class<?> aClass = Class.forName(Test.class.getName());
        Test test = (Test)aClass.newInstance();
        test.hhhh();

        System.out.println(max(Lists.newArrayList(1, 2, 3, 4)));
    }

    public void hhhh() {
        System.out.println("hdahwdhwa");
    }

    public static <T extends Number & Response> double add(T a, T b) {
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }

    private static <E extends Comparable<? super E>> E max(Collection<? extends E> e1) {
        if (e1 == null){
            return null;
        }
        //迭代器返回的元素属于 E 的某个子类型
        Iterator<? extends E> iterator = e1.iterator();
        E result = iterator.next();
        while (iterator.hasNext()){
            E next = iterator.next();
            if (next.compareTo(result) > 0){
                result = next;
            }
        }
        return result;
    }

    static class Point<T>{         // 此处可以随便写标识符号，T是type的简称
        private T var ;     // var的类型由T指定，即：由外部指定
        public T getVar(){  // 返回值的类型由外部决定
            return var ;
        }
        public void setVar(T var){  // 设置的类型也由外部决定
            this.var = var ;
        }

        public static void hhh() {
            System.out.println("hhhh");
        }
    }
}