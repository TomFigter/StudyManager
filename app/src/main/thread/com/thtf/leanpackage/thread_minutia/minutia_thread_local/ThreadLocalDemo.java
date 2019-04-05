package com.thtf.leanpackage.thread_minutia.minutia_thread_local;

import com.thtf.leanpackage.thread_minutia.minutia_join.CustomJoinThread;

public class ThreadLocalDemo {
    //基础分析Demo
 /*   public static ThreadLocal threadLocal=new ThreadLocal();
    public static void main(String[] args){
        if (threadLocal.get()==null){
            System.out.println("从未放过值...");
            threadLocal.set("我的值...");
        }
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
    }*/

//此实例是异步线程的变量与主线程的变量之间隔离
    /*public static void main(String[] args) {
        try {
            CustomJoinThread customJoinThreadA = new CustomJoinThread("A");
            CustomJoinThread customJoinThreadB = new CustomJoinThread("B");
            customJoinThreadA.start();
            customJoinThreadB.start();
            for (int index = 0; index < 100; index++) {
                Tools.threadLocal.set("Main" + (index + 1));
                System.out.println("Main Get Value=" + Tools.threadLocal.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        try{
            ThreadA threadA=new ThreadA();
            threadA.start();
            Thread.sleep(100);
            ThreadB threadB=new ThreadB();
            threadB.start();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
