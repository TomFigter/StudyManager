package com.thtf.leanpackage.thread_execute;


import com.thtf.leanpackage.thread_mode.CyclicBarrierThread;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by LiShiChuang on 2018/11/12.
 * 循环栅栏--CyclicBarrier
 */
public class CyclicBarrierThreadExecute {
    private static final int SIZE = 10;
    private static CyclicBarrier cyclicBarrier;

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(SIZE, new Runnable() {
            @Override
            public void run() {
                System.out.println("--->满足条件,执行特定操作.参与者数量为 -> " + cyclicBarrier.getParties());
            }
        });
        //新建任务集
        for (int index = 0; index < SIZE; index++) {
            new CyclicBarrierThread(cyclicBarrier).start();
        }
    }
}
