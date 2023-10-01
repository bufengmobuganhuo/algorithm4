package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex221_1 {

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(new Ex221_1().maximalSquare(matrix));
    }

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxSize = 0;
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '0' ? 0 : 1;
            maxSize = Math.max(maxSize, dp[i][0]);
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] == '0' ? 0 : 1;
            maxSize = Math.max(maxSize, dp[0][i]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }
        return maxSize * maxSize;
    }
}
