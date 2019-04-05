package com.thtf.leanpackage.order_package;

/**
 * Created by LiShiChuang on 2018/9/6.
 */
public class BinarySort {
    public static void binarySort(int[] source) {
        int i, j;
        int start, end, mid;
        int temp;
        for (i = 1; i < source.length; i++) {
            end = 0;// 查找区上界

            start = i - 1;// 查找区下界
            temp = source[i];//将当前待插入记录保存在临时变量中
            while (end <= start) {
                // mid = (end + start) / 2;
                mid = (end + start) >> 1;// 找出中间值
                if (temp < source[mid]) //如果待插入记录比中间记录小
                    start = mid - 1;// 插入点在低半区
                else
                    end = mid + 1;// 插入点在高半区
            }

            for (j = i - 1; j >= end; j--) {//将前面所有大于当前待插入记录的记录后移
                source[j + 1] = source[j];
            }
            source[end] = temp;//将待插入记录回填到正确位置.
            System.out.print("第" + i + "趟排序：");
            printArray(source);
        }
    }

    private static void printArray(int[] source) {
        for (int i = 0; i < source.length; i++) {
            System.out.print("\t" + source[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int source[] = new int[]{12, 15, 9, 14, 4, 18, 23, 6};
        System.out.print("初始关键字：");
        printArray(source);
        System.out.println("");
        binarySort(source);
        System.out.print("\n\n排序后结果：");
        printArray(source);
    }
}
