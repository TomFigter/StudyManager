package com.thtf.leanpackage.handler_detail;


import android.os.Binder;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;

import static android.os.Looper.myLooper;

/**
 * TODO 此处为Looper的基础源码分析,仅供查看分析,不可运行
 */
public final class Looper {
  /*  // 每个线程中的Looper对象其实是一个ThreadLocal，即线程本地存储(TLS)对象
    private static final ThreadLocal sThreadLocal = new ThreadLocal();
    // Looper内的消息队列
    final MessageQueue mQueue;

    // 当前线程
    Thread mThread;
    // 。。。其他属性

    // 每个Looper对象中有它的消息队列，和它所属的线程
    private Looper(boolean quitAllowed) {
        mQueue = new MessageQueue(quitAllowed);
        mThread = Thread.currentThread();
    }

    // 我们调用该方法会在调用线程的TLS中创建Looper对象
    public static void prepare() {
        prepare(true);
    }
    private static void prepare(boolean quitAllowed) {
        if (sThreadLocal.get() != null) {
            // 试图在有Looper的线程中再次创建Looper将抛出异常
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper(quitAllowed));
    }
    // 其他方法

    // TODO: 2019/1/29 调用loop方法后，Looper线程就开始真正工作了，它不断从自己的MQ中取出队头的消息(也叫任务)执行
    public static final void loop() {
        Looper me = myLooper();  //得到当前线程Looper
        MessageQueue queue = me.mQueue;  //得到当前looper的MQ

        // 这两行没看懂= = 不过不影响理解
        Binder.clearCallingIdentity();
        final long ident = Binder.clearCallingIdentity();
        // 开始循环
        while (true) {
            Message msg = queue.next(); // 取出message
            if (msg != null) {
                if (msg.target == null) {
                    // message没有target为结束信号，退出循环
                    return;
                }
                // 日志。。。
                if (me.mLogging!= null) me.mLogging.println(
                        ">>>>> Dispatching to " + msg.target + " "
                                + msg.callback + ": " + msg.what
                );
                // 非常重要！将真正的处理工作交给message的target，即后面要讲的handler
                msg.target.dispatchMessage(msg);
                // 还是日志。。。
                if (me.mLogging!= null) me.mLogging.println(
                        "<<<<< Finished to    " + msg.target + " "
                                + msg.callback);

                // 下面没看懂，同样不影响理解
                final long newIdent = Binder.clearCallingIdentity();
                if (ident != newIdent) {
                    Log.wtf("Looper", "Thread identity changed from 0x"
                            + Long.toHexString(ident) + " to 0x"
                            + Long.toHexString(newIdent) + " while dispatching to "
                            + msg.target.getClass().getName() + " "
                            + msg.callback + " what=" + msg.what);
                }
                // 回收message资源
                msg.recycle();
            }
        }
    }

    // TODO: 2019/1/29 Looper.myLooper()得到当前线程looper对象：
    public static final Looper myLooper() {
        // 在任意线程调用Looper.myLooper()返回的都是那个线程的looper
        return (Looper)sThreadLocal.get();
    }

    // TODO: 2019/1/29 getThread()得到looper对象所属线程
    public Thread getThread() {
        return mThread;
    }

    // TODO: 2019/1/29 quit()方法结束looper循环
    public void quit() {
        // 创建一个空的message，它的target为NULL，表示结束循环消息
        Message msg = Message.obtain();
        // 发出消息
        mQueue.enqueueMessage(msg, 0);
    }*/
}
