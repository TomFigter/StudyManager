package com.thtf.apt.once_click_apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LiShiChuang on 2018/12/13.
 * 一定时间内该点击事件只能执行一次
 * ID:控件ID
 * Time:间隔时间 Time=-1:间隔时间为默认的时间
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface OnceClick {
    int value();
}
