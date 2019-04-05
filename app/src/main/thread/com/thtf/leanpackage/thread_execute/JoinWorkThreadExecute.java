package com.thtf.leanpackage.thread_execute;


import com.thtf.leanpackage.thread_mode.JoinWorkerThread;

/**
 * Created by LiShiChuang on 2018/11/8.
 *
 * @join()函数阻塞线程 当工作线程A调用join函数时线程会发生阻塞
 * 其他线程将不会被允许执行,即其他线程会处于等待阶段.直到工作线程A执行完毕
 * 才允许其他线程继续执行
 */
public class JoinWorkThreadExecute {
    public static void main(String[] args) {
        JoinWorkerThread workerThreadOne = new JoinWorkerThread("work thread-1");
        JoinWorkerThread workerThreadTwo = new JoinWorkerThread("work thread-2");
        try {
            /**
             * 启动线程-1
             */
            long startTime = System.currentTimeMillis();
            System.out.println("启动线程 1");
            workerThreadOne.start();
            //workThreadOne调用join函数.此时主线程会发生阻塞,直到workThreadOne执行完成
            workerThreadOne.join();
            long joinTime = System.currentTimeMillis() - startTime;
            System.out.println("workThreadOne线程执行完毕.线程阻塞时间-> " + joinTime + "ms");
            /**
             * 启动线程-2
             */
            startTime = System.currentTimeMillis();
            System.out.println("启动线程 2");
            workerThreadTwo.start();
            //workThreadTwo调用join函数.此时主线程会发生阻塞,直到workThreadTwo执行完成
            workerThreadTwo.join();
            joinTime = System.currentTimeMillis() - startTime;
            System.out.println("workThreadTwo线程执行完毕.线程阻塞时间-> " + joinTime + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("以上线程执行完毕后主线程继续执行...");
    }
}
