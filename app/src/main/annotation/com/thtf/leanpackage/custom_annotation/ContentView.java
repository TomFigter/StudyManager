package com.thtf.leanpackage.custom_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LiShiChuang on 2018/12/5.
 * 申明注解用的就是 @interface
 */
@Retention(RetentionPolicy.RUNTIME)  //用来修饰这是一个什么样的注解 这是一个运行时注解
@Target({ElementType.TYPE}) //表示注解在什么地方使用 这里可以用来修饰: Class,interface or enum declaration
public @interface ContentView {
    /**
     * 返回值表示这个注解里可以存放什么类型值
     *
     * @return
     */
    int value();
}
