package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex1901 {
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int leftPtr = 0, rightPtr = n - 1;
        while (leftPtr <= rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            int maxInCol = Integer.MIN_VALUE;
            int maxInColIdx = -1;
            for (int i = 0; i < m; i++) {
                if (maxInCol < mat[i][midPtr]) {
                    maxInCol = mat[i][midPtr];
                    maxInColIdx = i;
                }
            }
            int left = midPtr > 0 ? mat[maxInColIdx][midPtr - 1] : -1;
            int right = midPtr < n - 1 ? mat[maxInColIdx][midPtr + 1] : -1;
            if (maxInCol > left && maxInCol > right) {
                return new int[]{maxInColIdx, midPtr};
            } else if (right > maxInCol) {
                leftPtr = midPtr + 1;
            } else if (right < maxInCol) {
                rightPtr = midPtr - 1;
            }
        }
        return new int[]{-1, -1};
    }
}
