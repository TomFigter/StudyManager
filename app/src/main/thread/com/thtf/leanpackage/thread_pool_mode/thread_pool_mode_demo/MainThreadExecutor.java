package com.thtf.leanpackage.thread_pool_mode.thread_pool_mode_demo;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by LiShiChuang on 2018/11/22.
 */
public class MainThreadExecutor {
    private Handler handler;

    public MainThreadExecutor() {
        handler = new Handler(Looper.getMainLooper());
    }

    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
