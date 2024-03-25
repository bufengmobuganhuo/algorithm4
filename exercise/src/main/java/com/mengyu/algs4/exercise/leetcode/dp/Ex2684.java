package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2684 {

    public static void main(String[] args) {
        int[][] grid = {{2, 4, 3, 5}, {5, 4, 9, 3}, {3, 4, 2, 11}, {10, 9, 13, 15}};
        System.out.println(new Ex2684().maxMoves(grid));
    }

    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<Integer> reachable = new HashSet<>();
        for (int i = 0; i < m; i++) {
            reachable.add(i);
        }
        for (int j = 1; j < n; j++) {
            Set<Integer> reachable2 = new HashSet<>();
            for (int i : reachable) {
                for (int i2 = i - 1; i2 <= i + 1; i2++) {
                    if (i2 > 0 && i2 < m && grid[i][j] < grid[i2][j]) {
                        reachable2.add(i2);
                    }
                }
            }
            reachable = reachable2;
            if (reachable.isEmpty()) {
                return j - 1;
            }
        }
        return n - 1;
    }
}
