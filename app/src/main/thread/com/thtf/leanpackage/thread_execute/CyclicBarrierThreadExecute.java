package com.thtf.leanpackage.thread_execute;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LiShiChuang on 2018/11/12.
 * 循环栅栏--CyclicBarrier
 * 循环屏障最好配合线程池使用，
 * 并且线程池中的线程数量最好与循环屏障的条件量相同
 * 否则容易出现线程执行量不符的情况
 */
public class CyclicBarrierThreadExecute implements Runnable {
    private static final int SIZE = 5;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(SIZE, this);
    private ExecutorService executor = Executors.newScheduledThreadPool(SIZE);
    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public void count() {
        for (int inedx = 0; inedx < 10; inedx++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    int score = (int) (Math.random() * 40 + 60);
                    map.put(Thread.currentThread().getName(), score);
                    System.out.println(Thread.currentThread().getName() + " --> " + score);
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        Set<String> key = map.keySet();
        for (String name : key) {
            result += map.get(name);
            System.out.println(name+ "同学的平均成绩 -> " +map.get(name));
        }
        System.out.println("同学的平均成绩 -> " + result / 5);
    }

    public static void main(String[] args) {
        CyclicBarrierThreadExecute cyclicBarrierThreadExecute = new CyclicBarrierThreadExecute();
        cyclicBarrierThreadExecute.count();
    }
}
