package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex54 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(new Ex54().spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int top = 0, bottom = rows - 1, left = 0, right = columns - 1;
        while (left <= right && top <= bottom) {
            for (int col = left; col <= right; col++) {
                ans.add(matrix[top][col]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                ans.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int col = right - 1; col > left; col--) {
                    ans.add(matrix[bottom][col]);
                }
                for (int row = bottom; row > top; row--) {
                    ans.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return ans;
    }
}
