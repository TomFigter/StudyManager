package com.thtf.leanpackage.thread_mode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by LiShiChuang on 2018/11/12.
 */
public class CyclicBarrierThread extends Thread {
    private final CyclicBarrier cyclicBarrier;

    public CyclicBarrierThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "等待CyclicBarrier.");
            //将cyclicBarrier的参与者数量 + 1
            cyclicBarrier.await();
            //cyclicBarrier的参与者数量 = 5 时,才继续执行.
            System.out.println(Thread.currentThread().getName() + " 继续执行.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
