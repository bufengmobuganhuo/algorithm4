package com.mengyu.algs4.exercise.chapter2_sort;

import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author yu zhang
 */
public class InsertSort implements SortTemplate {
    @Override
    public void sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j - 1]) ; j--) {
                exchange(arr, j, j - 1);
            }
        }
    }
}
