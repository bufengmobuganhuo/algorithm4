package com.mengyu.algs4.exercise.chapter6_dp.bag;

/**
 * @author yu zhang
 */
public class No4 {
    public int solution(int[] weights, int[] vals, int[] picks, int capacity) {
        int[] dp = new int[capacity + 1];
        int n = weights.length;
        for (int i = 1; i < n + 1; i++) {
            int pick = picks[i - 1], weight = weights[i - 1], val = vals[i - 1];
            if (pick == 0) {
                for (int j = weight; j <= capacity; j++) {
                    dp[j] = Math.max(dp[j], dp[j - weight] + val);
                }
            } else {
                int pickNum = Math.min(capacity / weight, pick);
                for (int k = 1; pickNum > 0; k <<= 1) {
                    k = Math.min(k, pickNum);
                    pickNum -= k;
                    for (int j = capacity; j >= k * weight; j--) {
                        dp[j] = Math.max(dp[j], dp[j - k * weight] + k * val);
                    }
                }
            }
        }
        return dp[capacity];
    }
}
