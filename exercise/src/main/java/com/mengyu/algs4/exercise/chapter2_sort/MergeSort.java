package com.mengyu.algs4.exercise.chapter2_sort;

import com.mengyu.algs4.utils.SortTemplate;

/**
 * @author yu zhang
 */
public class MergeSort implements SortTemplate {

    private Comparable[] temp;

    @Override
    public void sort(Comparable[] arr) {
        int len = arr.length;
        temp = new Comparable[len];
        for (int size = 1; size < len; size += size) {
            for (int leftPtr = 0; leftPtr < len - size; leftPtr += size + size) {
                merge(arr, leftPtr, leftPtr + size - 1, Math.min(leftPtr + 2 * size - 1, len - 1));
            }
        }
    }

    private void merge(Comparable[] arr, int leftPtr, int midPtr, int rightPtr) {
        int i = leftPtr, j = midPtr + 1;
        System.arraycopy(arr, leftPtr, temp, leftPtr, rightPtr - leftPtr + 1);
        for (int k = leftPtr; k < rightPtr + 1; k++) {
            if (i > midPtr) {
                arr[k] = temp[j++];
            } else if (j > rightPtr) {
                arr[k] = temp[i++];
            } else if (less(temp[i], temp[j])) {
                arr[k] = temp[i++];
            } else {
                arr[k] = temp[j++];
            }
        }
    }
}
