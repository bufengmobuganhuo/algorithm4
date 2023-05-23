package com.mengyu.algs4.exercise.offer.dp;

/**
 * @author yu zhang
 */
public class Ex107 {
    public int[][] updateMatrix(int[][] mat) {
        int max = 10000;
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0) {
                    int left = i > 0 ? dp[i - 1][j] : max;
                    int upper = j > 0 ? dp[i][j - 1] : max;
                    dp[i][j] = Math.min(left, upper) + 1;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    int right = j < n - 1 ? dp[i][j + 1] : max;
                    int down = i < m - 1 ? dp[i + 1][j] : max;
                    dp[i][j] = Math.min(dp[i][j], Math.min(right, down) + 1);
                }
            }
        }
        return dp;
    }
}
