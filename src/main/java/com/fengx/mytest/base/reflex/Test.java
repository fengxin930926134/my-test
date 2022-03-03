package com.fengx.mytest.base.reflex;

import lombok.Data;

/**
 * Java获取反射的三种方法
 * 1.通过new对象实现反射机制
 * 2.通过路径实现反射机制
 * 3.通过类名实现反射机制
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
        Student student = new Student();
        Class<? extends Student> aClass = student.getClass();
        System.out.println(aClass.getName());

        Class<?> aClass1 = Class.forName("com.fengx.mytest.base.reflex.Test$Student");
        System.out.println(aClass1.getName());

        Class<Student> studentClass = Student.class;
        System.out.println(studentClass.getName());
    }

    @Data
    public static class Student {
        private Integer id;
        private String name;
    }
}
