package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3218 {

    private int[][][][] mem;

    private int[] horizontalCut;

    private int[] verticalCut;

    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        this.mem = new int[m][n][m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    Arrays.fill(mem[i][j][k], -1);
                }
            }
        }
        this.horizontalCut = horizontalCut;
        this.verticalCut = verticalCut;
        return dp(0, 0, m - 1, n - 1);
    }

    private int dp(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return 0;
        }
        if (mem[startRow][startCol][endRow][endCol] == -1) {
            int res = Integer.MAX_VALUE;
            for (int i = startRow; i < endRow; i++) {
                res = Math.min(res, horizontalCut[i] + dp(startRow, startCol, i, endCol) + dp(i + 1, startCol,
                                endRow, endCol));
            }
            for (int i = startCol; i < endCol; i++) {
                res = Math.min(res, verticalCut[i] + dp(startRow, startCol, endRow, i) + dp(startRow, i + 1,
                                endRow, endCol));
            }
            mem[startRow][startCol][endRow][endCol] = res;
        }
        return mem[startRow][startCol][endRow][endCol];
    }
}
