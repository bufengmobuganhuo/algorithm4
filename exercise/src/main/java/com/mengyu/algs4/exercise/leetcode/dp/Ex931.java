package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex931 {

    public static void main(String[] args) {
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        System.out.println(new Ex931().minFallingPathSum2(matrix));
    }

    private int m = 0, n = 0;

    public int minFallingPathSum2(int[][] matrix) {
        n = matrix[0].length;
        for (int i = 1; i < n; i++) {
            int val = matrix[i][0];
            matrix[i][0] = Math.min(matrix[i - 1][0] + matrix[i][0], matrix[i - 1][1] + matrix[i][0]);
            for (int j = 1; j < n - 1; j++) {
                val = matrix[i][j];
                matrix[i][j] = matrix[i - 1][j] + val;
                matrix[i][j] = Math.min(matrix[i - 1][j - 1] + val, matrix[i][j]);
                matrix[i][j] = Math.min(matrix[i - 1][j + 1] + val, matrix[i][j]);
            }
            val = matrix[i][n - 1];
            matrix[i][n - 1] = Math.min(matrix[i - 1][n - 2] + val, matrix[i - 1][n - 1] + val);
        }
        int val = Integer.MAX_VALUE;
        for (int num : matrix[n - 1]) {
            val = Math.min(val, num);
        }
        return val;
    }

    public int minFallingPathSum(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        if (m == 1) {
            return Arrays.stream(matrix[0]).min().getAsInt();
        }
        long[][] dp = new long[m][n];
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[0], Integer.MAX_VALUE);
            dp[0][i] = matrix[0][i];
            min = Math.min(min, find(dp, matrix));
        }
        return (int) min;
    }

    private long find(long[][] dp, int[][] matrix) {
        long min = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + matrix[i][j];
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + matrix[i][j], dp[i][j]);
                }
                if (j < n - 1) {
                    dp[i][j] = Math.min(dp[i - 1][j + 1] + matrix[i][j], dp[i][j]);
                }
                if (i == m - 1) {
                    min = Math.min(min, dp[i][j]);
                }
            }
        }
        return min;
    }
}
