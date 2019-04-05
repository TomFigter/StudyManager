package com.thtf.leanpackage.thread_execute;


import com.thtf.leanpackage.thread_pool_mode.FutureCallableThreadPool;
import com.thtf.leanpackage.thread_pool_mode.FutureRunnableThreadPool;
import com.thtf.leanpackage.thread_pool_mode.FutureTaskThreadPool;

/**
 * Created by LiShiChuang on 2018/11/8.
 * {@link Runnable}
 * <p/>
 * {@link java.util.concurrent.Callable}
 * {@link java.util.concurrent.Future}
 * {@link java.util.concurrent.FutureTask}
 * <p/>
 * 以上四种为线程接口,我们常用的是{@Interface Runnable}与另外三者的区别
 *
 * <1>另外三者只能运用到线程池中,而{@Interface Runnable}既能运用到Thread又能用于线程池中</1>
 * <2>{@code Callable}与{@code Runnable}功能大致相同,而{@code Callable}具有返回值{@code call()},{@code Runnable}无返回值</2>
 * <3>{@code Callable}与{@code Runnable}运行状态下不可控.而{@code Future}为线程池制定了一个可管理的任务标准</3>
 * <4>{@code FutureTask}既是Future,Runnable又包装了Callable.它是两者的结合体</4>
 */
public class FutureThreadPoolExecute {
    public static void main(String[] args) {
        try {
            FutureRunnableThreadPool.futureWithRunnable();
            FutureCallableThreadPool.FutureWithCallable();
            FutureTaskThreadPool.FutureTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
