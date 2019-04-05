package com.thtf.apt.demo_apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LiShiChuang on 2018/12/5.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface GetMsg {
    int id();

    String name() default "default";
}
