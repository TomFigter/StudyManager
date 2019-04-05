package com.thtf.leanpackage.thread_pool_mode.thread_pool_mode_demo;

/**
 * Created by LiShiChuang on 2018/11/22.
 */
public class ThreadTaskPresenter extends BasePresenter implements ThreadContract.Presenter {
    private ThreadContract.View view;

    public ThreadTaskPresenter(ThreadContract.View view) {
        this.view = view;
    }

    @Override
    public void doTaskPresenter(final int index) {
        getThreadExecutor().run(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getMainThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        view.doTaskView("执行次数" + index + "\n");
                    }
                });
            }
        });
    }
}
