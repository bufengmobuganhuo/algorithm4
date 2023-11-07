package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex275 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int leftPtr = 0, rightPtr = n - 1;

        while (leftPtr <= rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            if (citations[midPtr] < n - midPtr) {
                leftPtr = midPtr + 1;
            } else {
                rightPtr = midPtr - 1;
            }
        }
        return n - leftPtr;
    }
}
