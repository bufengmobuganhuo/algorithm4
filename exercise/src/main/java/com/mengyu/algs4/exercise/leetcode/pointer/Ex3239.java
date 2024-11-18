package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex3239 {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j < n / 2 && (grid[i][j] ^ grid[i][n - j - 1]) == 1) {
                    cnt1++;
                }
                if (i < m / 2 && (grid[i][j] ^ grid[m - i - 1][j]) == 1) {
                    cnt2++;
                }
            }
        }
        return Math.min(cnt1, cnt2);
    }
}
