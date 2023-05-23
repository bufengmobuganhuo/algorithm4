package com.mengyu.algs4.exercise.leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex526_1 {
    private int count;

    public int countArrangement(int n) {
        backtracking(1, n, new boolean[n + 1]);
        return count;
    }

    private void backtracking(int idx, int n, boolean[] used) {
        if (idx > n) {
            count++;
            return;
        }
        for (int i = 1; i < n + 1; i++) {
            if (used[i]) {
                continue;
            }
            if (i % idx == 0 || idx % i == 0) {
                used[i] = true;
                backtracking(idx + 1, n, used);
                used[i] = false;
            }
        }
    }
}
