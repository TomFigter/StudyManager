package com.thtf.leanpackage.order_package;

/**
 * Created by LiShiChuang on 2018/9/5.
 * <p/>
 * java快速排序
 */
public class FastOrder {
    public static void main(String[] arg) {
        int[] array = new int[]{10, 50, 1, 2, 99, 45, 23, 3, 78, 100, 95, 4,9,80,0,5,2};
        orderMethod(array, 0, array.length - 1);
    }

    private static void orderMethod(int[] array, int start, int end) {
        int key = array[start];
        int rememberStart = start;
        int rememberEnd = end;
        while (end > start) {
            //从后往前,将比基值小的放在左边
            while (end > start && array[end] >= key) {
                end--;
            }
            if (array[end] <= key) {
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }

            //从前往后，将比基值大的放在右边
            while (end > start && array[start] <= key) {
                start++;
            }
            if (array[start] >= key) {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }

            printResult(array);
        }

        if (start > rememberStart) {
            orderMethod(array, rememberStart, start - 1);
        }
        if (end < rememberEnd) {
            orderMethod(array, end + 1, rememberEnd);
        }
    }

    private static void printResult(int[] array) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int index = 0; index < array.length; index++) {
            stringBuffer.append(array[index] + ",");
        }
        System.out.println(stringBuffer.toString());
        System.out.println("--------------------------------------------------");
    }

}
