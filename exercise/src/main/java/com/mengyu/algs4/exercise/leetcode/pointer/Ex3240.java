package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex3240 {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int zeros1 = 0, ones1 = 0, cnt1 = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (grid[i][j] != grid[i][n - j]) {
                    cnt1++;
                    zeros1++;
                    ones1++;
                }
            }
        }
        int zeros2 = 0, ones2 = 0, cnt2 = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m / 2; i++) {
                if (grid[i][j] != grid[i][j]) {
                    zeros2++;
                    ones2++;
                    cnt2++;
                }
            }
        }
        if (zeros1 % 4 != 0 && ones1 % 4 != 0) {
            cnt1 = Integer.MAX_VALUE;
        }
        if (zeros2 % 4 != 0 && ones2 % 4 != 0) {
            cnt2 = Integer.MAX_VALUE;
        }
        return Math.min(cnt1, cnt2);
    }
}
