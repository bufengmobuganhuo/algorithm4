package com.mengyu.algs4.exercise.chapter2_sort;

import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author yu zhang
 */
public class BubbleSort implements SortTemplate {
    @Override
    public void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (less(arr[j + 1], arr[j])) {
                    exchange(arr, j, j + 1);
                }
            }
        }
    }
}
