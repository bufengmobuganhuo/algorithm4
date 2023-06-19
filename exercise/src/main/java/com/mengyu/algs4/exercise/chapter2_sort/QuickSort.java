package com.mengyu.algs4.exercise.chapter2_sort;

import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author yu zhang
 */
public class QuickSort implements SortTemplate {
    @Override
    public void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(Comparable[] arr, int startPtr, int endPtr) {
        if (startPtr >= endPtr) {
            return;
        }
        int partitionIdx = partition(arr, startPtr, endPtr);
        sort(arr, startPtr, partitionIdx - 1);
        sort(arr, partitionIdx + 1, endPtr);
    }

    private int partition(Comparable[] arr, int leftPtr, int rightPtr) {
        int i = leftPtr, j = rightPtr + 1;
        Comparable partitionEle = arr[i];
        while (true) {
            while (less(arr[i++], partitionEle)) {
                if (i == rightPtr) {
                    break;
                }
            }
            while (less(partitionEle, arr[--j])) {
                if (j == leftPtr) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(arr, i, j);
        }
        exchange(arr, i, j);
        return j;
    }
}
