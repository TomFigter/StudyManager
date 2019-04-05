package com.thtf.leanpackage.handler_detail.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SampleTask implements Runnable {
    private static final String TAG = SampleTask.class.getSimpleName();
    Handler handler;

    public SampleTask(Handler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public void run() {
        try { //模拟执行任务
            Thread.sleep(5000);
            Message message = prepareMessage("Task Completed!");
            //message将被添加到主线程的MessageQueue中
            handler.sendMessage(message);
        } catch (InterruptedException e) {
            Log.d(TAG, "interrupted!");
        }

    }

    private Message prepareMessage(String str) {
        Message result = handler.obtainMessage();
        Bundle data = new Bundle();
        data.putString("message", str);
        result.setData(data);
        return result;
    }

}
