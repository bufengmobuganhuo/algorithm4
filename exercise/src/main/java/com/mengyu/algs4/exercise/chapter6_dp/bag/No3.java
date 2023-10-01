package com.mengyu.algs4.exercise.chapter6_dp.bag;

/**
 * @author yu zhang
 */
public class No3 {
    public int solution(int[] weights, int[] vals, int[] picks, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 1; i < weights.length + 1; i++) {
            int weight = weights[i - 1];
            int val = vals[i - 1];
            int pickNum = Math.min(picks[i - 1], capacity / weight);
            for (int k = 1; pickNum > 0; k <<= 2) {
                k = Math.min(k, pickNum);
                pickNum -= k;
                for (int j = capacity; j - k * weight >= 0; j--) {
                    dp[j] = Math.max(dp[j], dp[j - k * weight] + k * val);
                }
            }
        }
        return dp[capacity];
    }
}
