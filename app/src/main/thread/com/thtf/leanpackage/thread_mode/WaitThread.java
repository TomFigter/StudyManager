package com.thtf.leanpackage.thread_mode;

/**
 * Created by LiShiChuang on 2018/11/8.
 */
public class WaitThread extends Thread {
    public static Object lockObject = new Object();


    @Override
    public void run() {
        try {
            synchronized (lockObject) {
                Thread.sleep(3000);
                lockObject.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.run();
    }
}
