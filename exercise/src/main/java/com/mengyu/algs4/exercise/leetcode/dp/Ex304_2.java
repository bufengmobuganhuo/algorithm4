package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex304_2 {
    public static void main(String[] args) {
        int[][] matrix = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        Ex304_2 ex304_2 = new Ex304_2(matrix);
        System.out.println(ex304_2.sumRegion(2, 1, 4, 3));
    }

    private final int[][] dp;

    public Ex304_2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < col; i++) {
            dp[0][i] += dp[0][i - 1] + matrix[0][i];
        }
        for (int i = 1; i < row; i++) {
            int sum = 0;
            for (int j = 0; j < col; j++) {
                sum += matrix[i][j];
                dp[i][j] = dp[i - 1][j] + sum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = dp[row2][col2];
        if (row1 > 0) {
            res -= dp[row1 - 1][col2];
        }
        if (col1 > 0) {
            res -= dp[row2][col1 - 1];
        }
        if (row1 > 0 && col1 > 0) {
            res += dp[row1 - 1][col1 - 1];
        }
        return res;
    }
}
