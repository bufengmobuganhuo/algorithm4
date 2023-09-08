package com.mengyu.algs4.exercise.chapter6_dp.bag;

/**
 * @author yu zhang
 */
public class No2 {
    public int solution(int[] weights, int[] vals, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 1; i < weights.length + 1; i++) {
            for (int j = weights[i - 1]; j < capacity + 1; j++) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + vals[i - 1]);
            }
        }
        return dp[capacity];
    }
}
