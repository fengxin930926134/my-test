package com.fengx.mytest.base.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Test {

    @Annotation1(value = "test1", number = 2)
    public static void main(String[] args) {
        Method[] methods = Test.class.getMethods();
        System.out.println(methods[0].getName());
        System.out.println(Arrays.toString(methods[0].getParameters()));
        Annotation1 annotation = methods[0].getAnnotation(Annotation1.class);
        System.out.println(annotation.value());
        System.out.println(annotation.number());
    }
}
