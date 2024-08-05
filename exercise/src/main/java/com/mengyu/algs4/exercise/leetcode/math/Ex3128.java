package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex3128 {

    public static void main(String[] args) {
    }

    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long cnt = 0;
        int[] rowPrefixSum = new int[m], colPrefixSum = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefixSum[i] += grid[i][j];
                colPrefixSum[j] += grid[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                cnt += (long) (rowPrefixSum[i] - 1) * (colPrefixSum[j] - 1);
            }
        }
        return cnt;
    }
}
