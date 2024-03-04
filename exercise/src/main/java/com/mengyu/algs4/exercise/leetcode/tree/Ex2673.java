package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex2673 {
    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        for (int i = n - 2; i > 0; i -= 2) {
            ans += Math.abs(cost[i] - cost[i + 1]);
            cost[i / 2] += Math.max(cost[i], cost[i + 1]);
        }
        return ans;
    }
}
