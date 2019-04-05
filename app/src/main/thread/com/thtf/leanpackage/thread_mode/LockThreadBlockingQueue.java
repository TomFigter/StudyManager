package com.thtf.leanpackage.thread_mode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LiShiChuang on 2018/11/12.
 */
public class LockThreadBlockingQueue<T> {
    //数据组
    private final T[] items;
    //锁
    private final Lock lock = new ReentrantLock();
    //队满条件
    private Condition notFull = lock.newCondition();
    //队空条件
    private Condition notEmpty = lock.newCondition();
    //头部索引
    private int head;
    //尾部索引
    private int tail;
    //数据的个数
    private int count;

    @SuppressWarnings("unchecked")
    public LockThreadBlockingQueue(int maxSize) {
        items = (T[]) new Object[maxSize];
    }

    public LockThreadBlockingQueue() {
        this(10);
    }

    public void put(T t) {
        lock.lock();
        try {
            while (count == getCapacity()) {
                System.out.println("数据已满 --> 等待");
                notFull.await();
            }
            items[tail] = t;
            if (++tail == getCapacity())
                tail = 0;
            ++count;
            notEmpty.signalAll(); //唤醒等待数据的线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println("未添加任何数据 --> 请等待");
                notEmpty.await();
            }
            T ret = items[head];
            items[head] = null;
            if (++head == getCapacity())
                head = 0;
            --count;
            notFull.signalAll();//唤醒添加数据的线程
            return ret;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.lock();
        }
        return null;
    }

    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 数据的长度
     *
     * @return
     */
    public int getCapacity() {
        return items.length;
    }
}
