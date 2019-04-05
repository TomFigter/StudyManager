package com.thtf.leanpackage.thread_execute;


import com.thtf.leanpackage.thread_mode.LockThreadBlockingQueue;

/**
 * Created by LiShiChuang on 2018/11/12.
 */
public class LockThreadExecute {
    public static void main(String[] args) {
        final LockThreadBlockingQueue<Integer> lockThreadBlockingQueue = new LockThreadBlockingQueue<Integer>(3);


        new Thread(new Runnable() {
            @Override
            public void run() {
                //put值
                lockThreadBlockingQueue.put(11);
                lockThreadBlockingQueue.put(102);
                lockThreadBlockingQueue.put(1003);
                lockThreadBlockingQueue.put(10004);
                lockThreadBlockingQueue.put(100005);
                lockThreadBlockingQueue.put(1000006);
                lockThreadBlockingQueue.put(10000007);
                lockThreadBlockingQueue.put(100000008);
                lockThreadBlockingQueue.put(1000000009);
            }
        }).start();

        //get值
        for (int index = 0; index < 10; index++) {
            System.out.println(lockThreadBlockingQueue.take());
        }
    }
}
