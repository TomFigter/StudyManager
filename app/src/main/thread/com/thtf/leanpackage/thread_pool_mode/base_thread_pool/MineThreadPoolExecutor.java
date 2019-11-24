package com.thtf.leanpackage.thread_pool_mode.base_thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MineThreadPoolExecutor {
    public static int corePoolSize = 3;
    public static int maximumPoolSize = 5;
    public static long keepAliveTime = 120;
    public static TimeUnit unit = TimeUnit.SECONDS;
    public static BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();

    public static void main(String[] agrs) {
        ThreadPoolExecutor poolExecutors = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        poolExecutors.execute(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10; index++) {
                    System.out.println("我的 <threadPoolExectur> 号码 -> " + index);
                }
            }
        });
        poolExecutors.submit(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10; index++) {
                    System.out.println("我的 <singleThreadPool> submit -> Runnable 号码 -> " + index);
                }
            }
        });
        Future<String> kk = poolExecutors.submit(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10; index++) {
                    System.out.println("我的 <singleThreadPool> submit -> Runnable 号码 -> " + index);
                }
            }
        }, "我是有返回值的");

        try {
            System.out.println(kk.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
