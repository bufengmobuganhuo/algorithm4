package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex240_1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int row = m - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }
}
