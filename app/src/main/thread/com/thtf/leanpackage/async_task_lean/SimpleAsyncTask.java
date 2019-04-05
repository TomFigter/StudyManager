package com.thtf.leanpackage.async_task_lean;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;

/**
 * Created by LiShiChuang on 2018/11/21.
 */
public abstract class SimpleAsyncTask<Result> {
    //HandlerThread内部封装了自己的Handler和Thread,单独的Looper和消息队列
    private static final HandlerThread HT = new HandlerThread("SimpleAsyncTask", Process.THREAD_PRIORITY_BACKGROUND);

    static {
        HT.start();
    }

    //获取调用execute的线程Looper,构建Handler
    final Handler mUIHandler = new Handler(Looper.getMainLooper());
    //与异步线程队列关联的Handler
    final Handler mAsyncHandler = new Handler(HT.getLooper());

    /**
     * 功能描述 -> onPreExecute任务执行之前的初始化操作等
     */
    protected void onPreExecute() {
    }

    /**
     * doInBackground后台执行任务
     *
     * @return
     */
    protected abstract Result doInBackground();

    /**
     * doInBackground返回结果传递给执行在UI线程的onPostExecute
     *
     * @param result 执行结果
     */
    protected void onPostExecute(Result result) {
    }

    /**
     * execute方法调用doInBackground执行任务;
     * 并将结果投递给UI线程,调用onPostExecute处理结果.
     * @return
     */
    public final SimpleAsyncTask<Result> execute() {
        onPreExecute();
        //将任务投递到HandlerThread线程中执行
        mAsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                //后台执行任务,完成之后向UI线程post数据,用以更新UI等操作
                postResult(doInBackground());
            }
        });
        return this;
    }

    private void postResult(final Result result) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                onPostExecute(result);
            }
        });
    }
}
