package com.mengyu.algs4.exercise.chapter6_dp.bag;

/**
 * @author yu zhang
 */
public class No6 {
    public int solution(int[][] weights, int[][] vals, int capacity) {
        int group = weights.length;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i < group + 1; i++) {
            int groupMembers = weights[i].length;
            for (int j = capacity; j >= 0; j--) {
                for (int k = 0; k < groupMembers; k++) {
                    int weight = weights[i - 1][k];
                    int val = vals[i - 1][k];
                    dp[j] = Math.max(dp[j], dp[j - weight] + val);
                }
            }
        }
        return dp[capacity];
    }
}
