package com.mengyu.algs4.exercise.leetcode.design;

/**
 * @author yu zhang
 */
public class Ex3242 {

    private int n;

    private int[][] grid;

    private int[] map;

    public Ex3242(int[][] grid) {
        this.n = grid.length;
        this.grid = grid;
        this.map = new int[n*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.map[grid[i][j]] = i * n + j;
            }
        }
    }

    public int adjacentSum(int value) {
        int i = map[value] / n, j = map[value] % n;
        int upper = i > 0 ? grid[i - 1][j] : 0, lower = i < n - 1 ? grid[i + 1][j] : 0;
        int left = j > 0 ? grid[i][j - 1] : 0, right = j < n - 1 ? grid[i][j + 1] : 0;
        return upper + lower + left + right;
    }

    public int diagonalSum(int value) {
        int i = map[value] / n, j = map[value] % n;
        int leftUpper = i > 0 && j > 0 ? grid[i - 1][j - 1] : 0, rightLower = i < n - 1 && j < n - 1 ?
                grid[i + 1][j + 1] : 0;
        int rightUpper = i > 0 && j < n - 1 ? grid[i - 1][j + 1] : 0, leftLower = i < n - 1 && j > 0 ?
                grid[i + 1][j - 1] : 0;
        return leftLower + leftUpper + rightUpper + rightLower;
    }
}
