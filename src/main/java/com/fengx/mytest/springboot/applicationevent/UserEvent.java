package com.fengx.mytest.springboot.applicationevent;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent
{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEvent(Object source, int age, String name) {
        super(source);
        this.age = age;
        this.name = name;
    }
}