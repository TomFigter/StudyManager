package com.thtf.leanpackage.handler_detail;

import android.os.Looper;


public class LooperThread extends Thread {
    private Handlers handler1;
    private Handlers handler2;

    @Override
    public void run() {
        // 将当前线程初始化为Looper线程
        Looper.prepare();
        // ...其他处理，如实例化handler
        // 实例化两个handler
        handler1 = new Handlers();
        handler2 = new Handlers();
        // 开始循环处理消息队列
        Looper.loop();
    }
}
