package com.thtf.leanpackage.thread_execute;


import com.thtf.leanpackage.thread_mode.YieldThread;

/**
 * Created by LiShiChuang on 2018/11/8.
 * 线程的执行是有时间片的,每个线程轮流占用Cpu固定的时间,
 * 执行周期到了之后就让出执行权给其他线程.
 *
 * @yield()可主动让当前执行线程让出执行时间给其他已经准备就绪状态的线程
 */
public class YieldThreadExecute {
    public static void main(String[] args) {
        YieldThread yieldThreadOne = new YieldThread("thread-1");
        YieldThread yieldThreadTwo = new YieldThread("thread-2");
        yieldThreadOne.start();
        yieldThreadTwo.start();
    }
}
