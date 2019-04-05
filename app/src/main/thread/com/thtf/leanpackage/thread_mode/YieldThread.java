package com.thtf.leanpackage.thread_mode;

/**
 * Created by LiShiChuang on 2018/11/8.
 */
public class YieldThread extends Thread {
    public YieldThread(String name) {
        super(name);
    }

    public synchronized void run() {
        for (int index = 0; index < 6; index++) {
            System.out.println("| " + this.getName() + " |  | " + this.getPriority() + " |  | " + index + " |");
            if (index == 2) {
                Thread.yield();
                System.out.println("--------------------------");
            }
        }
    }
}
