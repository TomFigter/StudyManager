package com.thtf.leanpackage.thread_pool_mode.future_thread_pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

/**
 * Created by LiShiChuang on 2018/11/8.
 */
public class FutureTaskThreadPool extends FutureBaseThreadPool {
    public static void FutureTask() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return fibc(20);
            }
        });
        //提交FutureTask
        executorService.submit(futureTask);
        System.out.println("Future Result From FutureTask -> " + futureTask.get());
        new Semaphore(3).acquire();
    }
}
