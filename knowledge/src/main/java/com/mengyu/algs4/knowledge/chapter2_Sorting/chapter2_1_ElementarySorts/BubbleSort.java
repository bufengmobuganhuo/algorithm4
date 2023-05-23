package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_1_ElementarySorts;

import com.mengyu.algs4.utils.SortTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/10/30 10:23 上午
 * TODO
 */
public class BubbleSort implements SortTemplate {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>(3);

    }

    @Override
    public void sort(Comparable[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        // N个元素，需要N-1趟排序
        for (int i = 0; i < arr.length - 1; i++) {
            // 每趟需要N-i-1次排序
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (less(arr[j + 1], arr[j])) {
                    exchange(arr, j, j + 1);
                }
            }
        }
    }
}
