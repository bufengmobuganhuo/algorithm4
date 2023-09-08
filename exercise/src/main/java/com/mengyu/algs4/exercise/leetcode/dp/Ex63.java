package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex63 {

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println(new Ex63().uniquePathsWithObstacles(obstacleGrid));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = (obstacleGrid[0][i] == 1 || (i > 0 && dp[i - 1] == 0)) ? 0 : 1;
        }
        for (int i = 1; i < m; i++) {
            dp[0] = obstacleGrid[i][0] == 1 ? 0 : dp[0];
            for (int j = 1; j < n; j++) {
                dp[j] = obstacleGrid[i][j] == 1 ? 0 : (dp[j] + dp[j - 1]);
            }
        }
        return dp[n - 1];
    }
}
