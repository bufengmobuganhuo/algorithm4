package com.mengyu.algs4.exercise.leetcode.rank.year2022.june12;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2022/6/12 10:22
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {

    }
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        System.arraycopy(grid[0], 0, dp[0], 0, n);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    min = Math.min(min, dp[i-1][k] + moveCost[grid[i-1][k]][j]);
                }
                dp[i][j] = grid[i][j] + min;
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }
}
