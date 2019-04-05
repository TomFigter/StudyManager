package com.thtf.leanpackage.thread_pool_mode.thread_pool_mode_demo;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by LiShiChuang on 2018/11/22.
 */
public class BasePresenter {
    ThreadExecutor threadExecutor;
    MainThreadExecutor mainThreadExecutor;

    public BasePresenter() {
        this.threadExecutor = new ThreadExecutor();
        this.mainThreadExecutor = new MainThreadExecutor();
    }

    public ThreadExecutor getThreadExecutor() {
        return threadExecutor;
    }

    public MainThreadExecutor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    public void closeThreadExecutor() {
        threadExecutor.closeThreadExecutor();
    }

    public void closeThread() {
        threadExecutor.closeThread();
    }
    public void resetThreadExecutor() {
        threadExecutor.resetThreadExecutor();
    }


    public ThreadPoolExecutor getExecutor() {
        return threadExecutor.getExecutor();
    }

    public Future<?> getExecutorRunnable() {
        return threadExecutor.getExecutorRunnable();
    }

    public Future getExecutorCallable() {
        return threadExecutor.getExecutorCallable();
    }

}
