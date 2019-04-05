package com.thtf.leanpackage.thread_minutia.minutia_join;

public class CustomJoinThread extends Thread {
    private String threadName;

    public CustomJoinThread(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public void run() {
        try {
            int secondValue = (int) (Math.random() * 10000);
            System.out.println("线程名 -> " + threadName);
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
