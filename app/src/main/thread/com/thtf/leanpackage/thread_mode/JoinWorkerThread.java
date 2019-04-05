package com.thtf.leanpackage.thread_mode;

/**
 * Created by LiShiChuang on 2018/11/8.
 */
public class JoinWorkerThread extends Thread {
    public JoinWorkerThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("work thread in" + getName());
    }
}
