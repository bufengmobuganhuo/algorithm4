package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex59 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Ex59().generateMatrix(3)));
    }

    private final int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int cnt = 0;
        int i = 0, j = 0;
        for (int num = 1; num <= n * n;) {
            for (int k = 0; k < 4; k++) {
                for (; i < n && j < n && i >= 0 && j >= 0 && matrix[i][j] == 0; i += directions[k][0], j += directions[k][1]) {
                    matrix[i][j] = num;
                    num++;
                }
                i = i - directions[k][0];
                j = j - directions[k][1];
                i += directions[(k + 1) % 4][0];
                j += directions[(k + 1) % 4][1];
            }
        }
        return matrix;
    }
}
