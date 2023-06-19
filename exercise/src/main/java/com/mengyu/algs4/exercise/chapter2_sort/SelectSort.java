package com.mengyu.algs4.exercise.chapter2_sort;

import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author yu zhang
 */
public class SelectSort implements SortTemplate {
    @Override
    public void sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (less(arr[j], arr[minIdx])) {
                    minIdx = j;
                }
            }
            exchange(arr, i, minIdx);
        }
    }
}
