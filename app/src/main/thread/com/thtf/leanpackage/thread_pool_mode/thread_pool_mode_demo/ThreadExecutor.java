package com.thtf.leanpackage.thread_pool_mode.thread_pool_mode_demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiShiChuang on 2018/11/22.
 */
public  class ThreadExecutor {
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();
    private ThreadPoolExecutor executor;
    private Future<?> executorRunnable;
    private Future executorCallable;

    public ThreadExecutor() {
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, WORK_QUEUE);
    }

    public void run(Callable callable) {
        executorCallable = executor.submit(callable);
    }

    public void run(Runnable runnable) {
        executorRunnable = executor.submit(runnable);
    }

    /**
     * 暴力关闭线程池
     */
    public void closeThreadExecutor() {
        executor.shutdownNow();
        closeThread();
        Thread.interrupted();
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public Future<?> getExecutorRunnable() {
        return executorRunnable;
    }

    public Future getExecutorCallable() {
        return executorCallable;
    }

    /**
     * 中断线程操作
     */
    public void closeThread() {
        if (executorCallable != null) {
            executorCallable.cancel(true);
        }
        if (executorRunnable != null) {
            executorRunnable.cancel(true);
        }
    }

    public void resetThreadExecutor() {
        executor = null;
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, WORK_QUEUE);
    }
}
