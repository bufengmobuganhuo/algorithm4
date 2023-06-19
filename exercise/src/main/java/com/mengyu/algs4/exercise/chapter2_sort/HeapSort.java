package com.mengyu.algs4.exercise.chapter2_sort;

import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author yu zhang
 */
public class HeapSort implements SortTemplate {

    @Override
    public void sort(Comparable[] arr) {
        int len = arr.length;
        for (int k = (len - 1) / 2; k >= 0; k--) {
            sink(arr, k, len);
        }
        while (len > 0) {
            exchange(arr, 0, --len);
            sink(arr, 0 , len);
        }
    }

    private void sink(Comparable[] arr, int k, int len) {
        while (2 * k + 1 < len) {
            int j = 2 * k + 1;
            if (j + 1 < len && less(arr[j], arr[j + 1])) {
                j++;
            }
            if (less(arr[j+1], arr[k])) {
                break;
            }
            exchange(arr, k, j);
            k = j;
        }
    }
}
