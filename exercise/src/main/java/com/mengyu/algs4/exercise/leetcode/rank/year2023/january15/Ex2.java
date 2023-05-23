package com.mengyu.algs4.exercise.leetcode.rank.year2023.january15;

/**
 * @author yuzhang
 * @date 2023/1/15 10:02
 * TODO
 */
public class Ex2 {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] matrix = new int[n][n];
        for (int[] query : queries) {
            int row1 = query[0];
            int col1 = query[1];
            int row2 = query[2];
            int col2 = query[3];
            for (int i = row1; i < row2 + 1; i++) {
                for (int j = col1; j < col2 + 1; j++) {
                    matrix[i][j]++;
                }
            }
        }
        return matrix;
    }
}
