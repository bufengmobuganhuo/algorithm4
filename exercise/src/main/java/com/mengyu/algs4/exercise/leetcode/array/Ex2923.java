package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex2923 {
    public int findChampion(int[][] grid) {
        int n = grid.length;
        int champion = 0;
        for (int i = 1; i < n; i++) {
            if (grid[i][champion] > 0) {
                champion = i;
            }
        }
        return champion;
    }
}
