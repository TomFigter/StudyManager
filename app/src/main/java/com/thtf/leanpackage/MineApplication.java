package com.thtf.leanpackage;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by LiShiChuang on 2018/11/21.
 */
public class MineApplication extends Application {
    private static RefWatcher leakCanary;

    @Override
    public void onCreate() {
        super.onCreate();
        leakCanary = LeakCanary.install(this);
    }

    public static RefWatcher getLeakCanary() {
        return leakCanary;
    }
}
