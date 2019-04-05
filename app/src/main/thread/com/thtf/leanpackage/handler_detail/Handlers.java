package com.thtf.leanpackage.handler_detail;

import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;

import java.lang.reflect.Modifier;

// TODO: 2019/1/29 Handler源码分析.仅供查看分析，不可运行
public class Handlers {
/*
    final MessageQueue mQueue;  // 关联的MQ
    final Looper mLooper;  // 关联的looper
    final android.os.Handlers.Callback mCallback;
    // 其他属性

    public Handlers() {
        // 没看懂，直接略过，，，
        if (FIND_POTENTIAL_LEAKS) {
            final Class<? extends android.os.Handlers> klass = getClass();
            if ((klass.isAnonymousClass() || klass.isMemberClass() || klass.isLocalClass()) &&
                    (klass.getModifiers() & Modifier.STATIC) == 0) {
                Log.w(TAG, "The following Handlers class should be static or leaks might occur: " +
                        klass.getCanonicalName());
            }
        }
        // 默认将关联当前线程的looper
        mLooper = Looper.myLooper();
        // looper不能为空，即该默认的构造方法只能在looper线程中使用
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create Handlers inside thread that has not called Looper.prepare()");
        }
        // 重要！！！直接把关联looper的MQ作为自己的MQ，因此它的消息将发送到关联looper的MQ上
        mQueue = mLooper.mQueue;
        mCallback = null;
    }

    // 其他方法

    // 此方法用于向关联的MQ上发送Runnable对象，它的run方法将在handler关联的looper线程中执行
    public final boolean post(Runnable r)
    {
        // 注意getPostMessage(r)将runnable封装成message
        return  sendMessageDelayed(getPostMessage(r), 0);
    }

    private final Message getPostMessage(Runnable r) {
        Message m = Message.obtain();  //得到空的message
        m.callback = r;  //将runnable设为message的callback，
        return m;
    }

    public boolean sendMessageAtTime(Message msg, long uptimeMillis)
    {
        boolean sent = false;
        MessageQueue queue = mQueue;
        if (queue != null) {
            msg.target = this;  // message的target必须设为该handler！
            sent = queue.enqueueMessage(msg, uptimeMillis);
        }
        else {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            Log.w("Looper", e.getMessage(), e);
        }
        return sent;
    }

    // 处理消息，该方法由looper调用
    public void dispatchMessage(Message msg) {
        if (msg.callback != null) {
            // 如果message设置了callback，即runnable消息，处理callback！
            handleCallback(msg);
        } else {
            // 如果handler本身设置了callback，则执行callback
            if (mCallback != null) {
                *//* 这种方法允许让activity等来实现Handler.Callback接口，避免了自己编写handler重写handleMessage方法。见http://alex-yang-xiansoftware-com.iteye.com/blog/850865 *//*
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            // 如果message没有callback，则调用handler的钩子方法handleMessage
            handleMessage(msg);
        }
    }

    // 处理runnable消息
    private final void handleCallback(Message message) {
        message.callback.run();  //直接调用run方法！
    }
    // 由子类实现的钩子方法
    public void handleMessage(Message msg) {
    }*/
}