package com.thtf.leanpackage.thread_pool_mode.future_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LiShiChuang on 2018/11/8.
 */
public abstract class FutureBaseThreadPool {
    //连接线程池
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    protected static int fibc(int num) {
        if (num == 0)
            return 0;
        if (num == 1)
            return 1;
        return fibc(num - 1) + fibc(num - 2);
    }
}
