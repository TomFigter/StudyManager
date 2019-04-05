package com.thtf.leanpackage.thread_execute;


import com.thtf.leanpackage.thread_mode.WaitThread;

/**
 * Created by LiShiChuang on 2018/11/8.
 *
 * @wait、notify等待机制 wait、notify机制通常用于等待机制的实现,当条件外满足时调用wait进入等待状态,
 * 一旦条件满足,调用notify或notifyAll唤醒等待的线程继续执行.
 */
public class WaitThreadExecute {
    public static void main(String[] args) {
        //创建线程实例
        Thread waitThread = new WaitThread();
        waitThread.start();
        long startTime = System.currentTimeMillis();
        try {
            synchronized (WaitThread.lockObject) {
                System.out.println("主线程等待...");
                WaitThread.lockObject.wait(); //主线程进入等待状态,此时将不再执行.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long timeMs = (System.currentTimeMillis() - startTime);
        System.out.println("主线程继续—>等待耗时: " + timeMs + "ms");
    }
}
