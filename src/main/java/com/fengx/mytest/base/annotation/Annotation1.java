package com.fengx.mytest.base.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation1 {

    String value() default "default";
    int number() default 1;
}
