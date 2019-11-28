package com.thtf.leanpackage.thread_pool_mode.base_thread_pool;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 此处线程池声明方法 皆为 {@Class Executors}
 * 【强制】-> 线程池不允许使用 {@Class Executors}去创建，而是通过{@Class ThreadPoolExecutor}的方式
 * 这样的处理方式让写的同学更加明确线程池的运行规则，规避资源号耗尽的风险.
 * 【说明】-> {@Class Executors}返回的线程池对象的弊端如下:
 * 1> {@Class FixedThreadPool} 和 {@Class SingleThread}
 * 允许的{@Class LinkedBlockingQueue<Runnable>()}请求队列长度为{@code Integer.MAX_VALUE},即 (2^31)-1
 * 可能会堆积大量的请求，从而导致OOM
 * 2> {@Class CachedThreadPool} 和 {@Class ScheduledThreadPool}
 * 允许的{@code SynchronousQueue<Runnable>()}请求队列长度为{@code Integer.MAX_VALUE},即 (2^31)-1
 * 可能会堆积大量的请求，从而导致OOM
 * <p>
 * [线程描述]
 * SingleThreadExecutor
 * FixedThreadPool
 * cachedThreadPool
 * SingleThreadScheduledExecutor
 * ScheduledThreadPool
 */
public class BaseThreadPool {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] obj) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinTask<String> result = forkJoinPool.submit(new ForkJoinTask<String>() {
            @Override
            public String getRawResult() {
                try {
                    return get();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return "李赫晗";
            }

            @Override
            protected void setRawResult(String value) {
                value="LishiChuang";
            }

            @Override
            protected boolean exec() {
                System.out.println("开始执行");
                return true;
            }
        });
        try {
            System.out.println(result.get());
            forkJoinPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        singleThreadPool.submit(new Runnable() {
//            @Override
//            public void run() {
//                for (int index = 0; index < 10; index++) {
//                    System.out.println("我的 <singleThreadPool> 号码 -> " + index);
//                }
//            }
//        });
//        fixedThreadPool.submit(new Runnable() {
//            @Override
//            public void run() {
//                for (int index = 0; index < 10; index++) {
//                    System.out.println("我的 <fixedThreadPool> 号码 -> " + index);
//                }
//            }
//        });
//        cachedThreadPool.submit(new Runnable() {
//            @Override
//            public void run() {
//                for (int index = 0; index < 10; index++) {
//                    System.out.println("我的 <cacheThreadPool> 号码 -> " + index);
//                }
//            }
//        }, "完美结束");
//        cachedThreadPool.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                for (int index = 0; index < 10; index++) {
//                    System.out.println("我的 <cacheThreadPool> Callabe 号码 -> " + index);
//                }
//                return "cachedThreadPool 结束了Callable";
//            }
//        });
//        singleThreadScheduledPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                for (int index = 0; index < 10; index++) {
//                    System.out.println("我的 <singleThreadScheduledPool> 号码 -> " + index);
//                }
//            }
//        }, 1000, TimeUnit.SECONDS);
//        scheduledPool.schedule(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                for (int index = 0; index < 10; index++) {
//                    System.out.println("我的 <scheduledPool> 号码 -> " + index);
//                }
//                return "scheduledPool结束了Callbale";
//            }
//        }, 1000, TimeUnit.SECONDS);
    }
}
