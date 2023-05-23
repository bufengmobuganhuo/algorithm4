package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/11 9:09
 * 练习2.2.12:次线性的额外空间 实际上是另一种归并排序方式：
 * 1. 将数组分块，并在块内进行选择排序
 * 2. 将每块进行归并
 */
public class EX_2_2_12 {
    private static Comparable[] temp;

    public static void main(String[] args) {
        Comparable[] arr = ArrayUtil.createInt(20, 25);
        print(arr, 5, "排序前：");
        solution1(arr, 5);
        print(arr, 5, "排序后：");
        solution2(arr, 5);
        print(arr, 5, "归并后：");

    }

    /**
     * 第一问：将数组分成（）arr.length/blockSize）块，对块内元素进行选择排序
     */
    public static void solution1(Comparable[] arr, int blockSize) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i += blockSize) {
            selectionSort(arr, i, blockSize);
        }
    }

    /**
     * 第二问：遍历数组，将上述块内排序后的每个块归并
     */
    public static void solution2(Comparable[] arr, int blockSize) {
        temp = new Comparable[arr.length];
        for (int i = 0; i + blockSize + blockSize - 1 < arr.length; i += blockSize) {
            merge(arr, 0, i + blockSize - 1, i + blockSize + blockSize - 1);
        }
    }

    private static void merge(Comparable[] arr, int left, int mid, int right) {
        int i = left, j = mid + 1;
        System.arraycopy(arr, left, temp, left, right - left + 1);
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arr[k] = temp[j++];
            } else if (j > right) {
                arr[k] = temp[i++];
            } else if (less(temp[i], temp[j])) {
                arr[k] = temp[i++];
            } else {
                arr[k] = temp[j++];
            }
        }
    }


    private static void selectionSort(Comparable[] arr, int start, int blockSize) {
        for (int i = start; i < start + blockSize; i++) {
            int minIndex = i;
            for (int j = i + 1; j < start + blockSize; j++) {
                if (less(arr[j], arr[minIndex])) {
                    minIndex = j;
                }
            }
            exchange(arr, i, minIndex);
        }
    }


    private static boolean less(Comparable value1, Comparable value2) {
        return value1.compareTo(value2) < 0;
    }

    private static void exchange(Comparable[] arr, int index1, int index2) {
        Comparable temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private static void print(Comparable[] arr, int blockSize, String msg) {
        System.out.println(msg);
        for (int i = 0; i < arr.length; i++) {
            if (i % blockSize == 0) {
                System.out.print("| ");
            }
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
