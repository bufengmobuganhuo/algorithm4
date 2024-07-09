package com.mengyu.algs4.exercise.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex3033 {
    public int[][] modifiedMatrix(int[][] matrix) {
        List<Integer> max = new ArrayList<>();
        List<int[]> negative = new ArrayList<>();
        for (int j = 0; j < matrix[0].length; j++) {
            int m = Integer.MIN_VALUE;
            for (int i = 0; i < matrix.length; i++) {
                m = Math.max(m, matrix[i][j]);
                if (matrix[i][j] == -1) {
                    negative.add(new int[]{i, j});
                }
            }
            max.add(m);
        }
        for (int[] point : negative) {
            matrix[point[0]][point[1]] = max.get(point[1]);
        }
        return matrix;
    }
}
