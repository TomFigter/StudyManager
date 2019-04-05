package com.thtf.leanpackage.thread_execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by LiShiChuang on 2018/11/12.
 * 限号量--Semaphore
 */
public class SemaphoreThreadPoolExecute {
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        //信号量许可集数量
        final Semaphore semaphore = new Semaphore(5);
        for (int index = 0; index < 10; index++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取信号量许可
                        semaphore.acquire();
                        //信号量许可集剩余数量
                        System.out.println("剩余许可:" + semaphore.availablePermits());
                        Thread.sleep(2000);
                        //释放所持有的信号量许可
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
