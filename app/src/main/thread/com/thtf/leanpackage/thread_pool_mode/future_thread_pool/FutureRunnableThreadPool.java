package com.thtf.leanpackage.thread_pool_mode.future_thread_pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by LiShiChuang on 2018/11/8.
 */
public class FutureRunnableThreadPool extends FutureBaseThreadPool {


    public static void futureWithRunnable() throws ExecutionException, InterruptedException {
        Future<?> result = executorService.submit(new Runnable() {
            @Override
            public void run() {
                fibc(20);
            }
        });
        System.out.println("Future Result From Runnable -> " + result.get());
    }


}
