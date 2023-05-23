package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/5/18 上午7:45
 * TODO
 */
public class Ex64 {
    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1},
        };
        Ex64 ex64 = new Ex64();
        System.out.println(ex64.minPathSum(grid));
    }
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0){
                dp[i] = grid[0][i];
            }else {
                dp[i] = grid[0][i] + dp[i-1];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[j] += grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        return dp[n-1];
    }
}
