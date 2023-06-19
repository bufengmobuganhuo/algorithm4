package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex406_2 {
    public static void main(String[] args) {
        int[][] people = {{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}};
        System.out.println(Arrays.deepToString(new Ex406_2().reconstructQueue(people)));
    }
    public int[][] reconstructQueue(int[][] people) {
        sort2(people);
        List<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(people);
    }

    private void sort2(int[][] arr) {
        int len = arr.length;
        for (int k = (len - 1) / 2; k > -1; k--) {
            sink(arr, k, len);
        }
        while (len > 0) {
            exch(arr, 0, --len);
            sink(arr, 0, len);
        }
    }

    private void sink(int[][] arr, int k, int len) {
        while (2 * k + 1 < len) {
            int j = 2 * k + 1;
            if (j + 1 < len && less(arr[j + 1], arr[j])) {
                j++;
            }
            if (less(arr[k], arr[j])) {
                break;
            }
            exch(arr, k, j);
            k = j;
        }
    }

    private void sort(int[][] arr, int startPtr, int endPtr) {
        if (startPtr >= endPtr) {
            return;
        }
        int partitionIdx = partition(arr, startPtr, endPtr);
        sort(arr, startPtr, partitionIdx - 1);
        sort(arr, partitionIdx + 1, endPtr);
    }

    private int partition(int[][]arr, int startPtr, int endPtr) {
        int leftPtr = startPtr, rightPtr = endPtr + 1;
        int[] partitionEle = arr[startPtr];
        while (true) {
            while (less(partitionEle, arr[++leftPtr])) {
                if (leftPtr == endPtr) {
                    break;
                }
            }
            while (less(arr[--rightPtr], partitionEle)) {
                if (rightPtr == startPtr) {
                    break;
                }
            }
            if (leftPtr >= rightPtr) {
                break;
            }
            exch(arr, leftPtr, rightPtr);
        }
        exch(arr, startPtr, rightPtr);
        return rightPtr;
    }

    private void exch(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private boolean less(int[] people1, int[] people2) {
        if (people1[0] != people2[0]) {
            return people1[0] - people2[0] < 0;
        }
        return people2[1] - people1[1] <= 0;
    }
}
