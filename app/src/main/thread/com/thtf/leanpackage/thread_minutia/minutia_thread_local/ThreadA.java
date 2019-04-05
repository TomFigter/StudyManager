package com.thtf.leanpackage.thread_minutia.minutia_thread_local;

import java.util.Date;

public class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int index = 0; index < 20; index++) {
                if (Tools.threadLocal.get() == null) {
                    Tools.threadLocal.set(new Date());
                    Thread.sleep(100);
                }
                System.out.println("A "+Tools.threadLocal.get().getTime());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
