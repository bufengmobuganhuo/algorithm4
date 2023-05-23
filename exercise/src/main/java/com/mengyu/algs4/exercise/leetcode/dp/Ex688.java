package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex688 {

    private static final int[][] dirs = {
            {-2, -1},
            {-2, 1},
            {-1, -2},
            {-1, 2},
            {1, -2},
            {2, -1},
            {2, 1},
            {1, 2}
    };

    /**
     * 1. dp[step][i][j]：从(i, j)出发，走了step步后仍在棋盘上的概率
     * 2. 初始条件1: dp[step][i][j] = 0，走了step步，并且(i, j)不在棋盘上
     *    初始条件2: dp[0][i][j] = 1，还没有走，并且(i, j)在棋盘上
     * 3. 状态转移方程：
     *  dp[step][i][j] = sum(dp[step - 1][x][y] * (1/8))
     *  (x,y)是所有可以经过一步到达(i, j)的位置，因为是随机走，所以从八个地方都可以走，则每个的概率是1/8
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for (int step = 0; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 初始条件2
                    if (step == 0) {
                        dp[step][i][j] = 1;
                    } else {
                        for (int[] dir : dirs) {
                            int lastI = i + dir[0], lastJ = j + dir[1];
                            if (lastI < n && lastI >= 0 && lastJ < n && lastJ >= 0) {
                                dp[step][i][j] += dp[step - 1][lastI][lastJ] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }
}
