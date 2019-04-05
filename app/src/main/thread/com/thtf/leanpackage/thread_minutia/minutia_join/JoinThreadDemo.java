package com.thtf.leanpackage.thread_minutia.minutia_join;

import java.util.ArrayList;
import java.util.List;

public class JoinThreadDemo {
    public static void main(String[] args) {
        List<CustomJoinThread> customJoinThreadList = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            CustomJoinThread customJoinThread = new CustomJoinThread("thread-" + index);
            customJoinThread.start();
            customJoinThreadList.add(customJoinThread);
        }

        for (CustomJoinThread customJoinThread : customJoinThreadList) {
            try {
                customJoinThread.join();
                System.out.printf("线程%s,执行结束\n", customJoinThread.getThreadName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
