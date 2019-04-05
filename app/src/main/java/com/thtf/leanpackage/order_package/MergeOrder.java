package com.thtf.leanpackage.order_package;

/**
 * Created by LiShiChuang on 2018/9/6.
 * 合并排序
 * 分治思想，将数组分至最小，再合并即可
 * 时间复杂度为 (n * logN)
 */
public class MergeOrder {
    public static void main(String[] args) {
        int array[] = {5, 1, 2, 3, 5, 9, 6, 44, 22, 53, 62, 10, 78, 88, 87, 98, 100};
        mergeSort(array, 0, array.length - 1);

        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+" | ");
        }
    }

    private static void mergeSort(int[] array, int start, int end) {
        while (start < end) {
            int q = (start + end) / 2;
            mergeSort(array, start, q);
            mergeSort(array, q + 1, end);
            merge(array, start, q, end);
        }
    }

    private static void merge(int[] array, int start, int q, int end) {
        int[] L = new int[q - start + 1];
        int[] R = new int[end - q];
        for (int i = start, j = 0; i <= q; i++, j++) {
            L[j] = array[i];
        }
        for (int i = q + 1, j = 0; i <= end; i++, j++) {
            R[j] = array[i];
        }
        int pos = start;
        int i = 0, j = 0;
        for (; i < L.length && j < R.length; ) {
            if (L[i] <= R[j])
                array[pos++] = L[i++];
            else
                array[pos++] = R[j++];
        }

        if (i < L.length) {
            for (; i > L.length; ) {
                array[pos++] = L[i++];
            }
        } else if (j < R.length) {
            for (; i < L.length; ) {
                array[pos++] = L[i++];
            }
        } else if (j < R.length) {
            for (; j < R.length; ) {
                array[pos++] = R[j++];
            }
        }
    }
}
