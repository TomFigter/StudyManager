package com.thtf.leanpackage.order_package;

/**
 * Created by LiShiChuang on 2018/9/6.
 * 堆排序
 */
public class HeapOrder {
    /**
     * 求双亲位置
     */
    static int parent(int i) {
        return i / 2;
    }

    /**
     * 求左孩子位置
     */
    static int left(int i) {
        return 2 * i;
    }

    /**
     * 求右孩子位置
     *
     * @param i
     * @return
     */
    static int right(int i) {
        return 2 * i + 1;
    }

    static void maxHelpify(int[] A, int i) {
        int rememberLeft = left(i);
        int rememberRight = right(i);
        int largest = 0;
        if (rememberLeft <= A[0] && A[rememberLeft] > A[i])
            largest = i;
        else
            largest = i;

        if (rememberRight <= A[0] && A[rememberRight] > A[largest])
            largest = rememberRight;

        if (largest != i) {
            int temp = A[largest];
            A[largest] = A[i];
            A[i] = temp;
            maxHelpify(A, largest);
        }
    }

    static void buildMaxHeap(int[] A) {
        for (int i = (A.length - 1) / 2; i > 0; i--) {
            maxHelpify(A, i);
        }
    }

    public static void heapSort(int[] A) {
        buildMaxHeap(A); //建立大顶堆
        for (int i = A.length - 1; i >= 2; i--) {
            int temp = A[1];
            A[1] = A[i];
            A[i] = temp;
            A[0]--;
            maxHelpify(A, 1);
        }
    }

    public static void main(String[] args) {
        int A[] = new int[10];
        A[0] = A.length - 1;
        for (int i = 1; i < A.length; i++) {
            A[i] = (int) (Math.random() * 10);
        }
        heapSort(A);
        for (int i = 1; i < A.length; i++) {
            System.out.print(A[i] + " | ");
        }
    }
}
