package com.mengyu.algs4.exercise.leetcode.rank.year2023.december17;

import java.util.HashSet;
import java.util.Set;

/**
 * @date 2023/12/17 10:28
 * TODO
 */
public class Ex1 {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int a = -1, b = -1;
        int n = grid.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = grid[i][j];
                if (set.contains(num)) {
                    a = num;
                } else {
                    set.add(num);
                }
            }
        }
        for (int i = 1; i < n * n + 1; i++) {
            if (!set.contains(i)) {
                b = i;
                break;
            }
        }
        return new int[]{a, b};
    }
}
