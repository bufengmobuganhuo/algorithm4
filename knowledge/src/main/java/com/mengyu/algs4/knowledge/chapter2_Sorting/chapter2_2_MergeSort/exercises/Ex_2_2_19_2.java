package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.exercises;

import edu.princeton.cs.algs4.In;

/**
 * @author yuzhang
 * @date 2020/10/29 11:31 上午
 * TODO
 */
public class Ex_2_2_19_2 {
    public static void main(String[] args) {

    }

    private static Integer[] tmp;
    private static int count;

    private static int solution(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return count;
        }
        tmp = new Integer[arr.length];
        count = 0;
        sort(arr, 0, arr.length - 1);
        return count;
    }

    private static void sort(Integer[] arr, int lo, int hi) {
        if (lo > hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);

    }

    private static void merge(Integer[] arr, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, tmp, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k < hi + 1; k++) {
            if (j > hi) {
                arr[k] = tmp[i++];
            } else if (i > mid) {
                arr[k] = tmp[j++];
            } else if (tmp[i] > tmp[j]) {
                count += j - k;
                arr[k] = tmp[j++];
            } else {
                arr[k] = tmp[i++];
            }
        }
    }
}
