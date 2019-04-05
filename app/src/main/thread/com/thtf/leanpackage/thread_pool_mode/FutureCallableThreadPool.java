package com.thtf.leanpackage.thread_pool_mode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by LiShiChuang on 2018/11/8.
 */
public class FutureCallableThreadPool extends FutureBaseThreadPool {
    public static void FutureWithCallable() throws ExecutionException, InterruptedException {
        Future<Integer> result = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return fibc(20);
            }
        });
        System.out.println("Future Result From Callable -> " + result.get());
    }
}
