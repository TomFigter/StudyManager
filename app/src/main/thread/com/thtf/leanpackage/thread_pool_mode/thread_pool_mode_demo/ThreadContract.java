package com.thtf.leanpackage.thread_pool_mode.thread_pool_mode_demo;

/**
 * Created by LiShiChuang on 2018/11/22.
 */
public class ThreadContract {
    public interface View {
        void doTaskView(String message);
    }

    public interface Presenter {
        void doTaskPresenter(int index);
    }
}
