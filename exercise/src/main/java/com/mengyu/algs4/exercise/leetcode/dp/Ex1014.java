package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1014 {
    public int maxScoreSightseeingPair(int[] values) {
        int ans = values[0] + values[1] - 1;
        int max = Math.max(values[0], values[1] + 1);
        for (int i = 2; i < values.length; i++) {
            ans = Math.max(ans, max + values[i] - i);
            max = Math.max(max, values[i] + i);
        }
        return ans;
    }
}
