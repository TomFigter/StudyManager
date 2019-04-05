package com.thtf.leanpackage.data_structure_package;

import java.util.concurrent.CountDownLatch;

/**
 * Created by LiShiChuang on 2018/12/29.
 */
public class SimpleThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        int threads = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(threads);
        final InnerClass innerClass = new InnerClass();
        for (int index = 1; index <= threads; index++) {
            final int finalIndex = index;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 4; j++) {
                        innerClass.add(String.valueOf(j), j);
                        innerClass.print();
                    }
                    innerClass.set("hello world" + finalIndex, finalIndex);
                    countDownLatch.countDown();
                }
            }, "thread-" + index).start();
        }
        countDownLatch.await();
    }

    private static class InnerClass {
        public void add(String name, int age) {
            BUS_TYPE busType = new BUS_TYPE(name, age);
            Counter.busTypeThreadLocal.set(busType);
        }

        public void print() {
            BUS_TYPE busType = Counter.busTypeThreadLocal.get();
            System.out.printf("Thread Name:%s,ThreadLocal hashcode: %s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    busType.hashCode(),
                    Counter.busTypeThreadLocal.hashCode(),
                    busType.toString());
        }

        public void set(String name, int age) {
            BUS_TYPE busType = new BUS_TYPE(name, age);
            Counter.busTypeThreadLocal.set(busType);
            System.out.printf("Set,Thread Name:%s, ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.busTypeThreadLocal.hashCode(),
                    busType.hashCode(),
                    Counter.busTypeThreadLocal.get().toString());
            System.out.println("--------------------------------------------------------------");
        }
    }

    private static class Counter {
        private static ThreadLocal<BUS_TYPE> busTypeThreadLocal = new ThreadLocal<BUS_TYPE>();
    }
}
