package com.mengyu.algs4.exercise.chapter6_dp.bag;

import javafx.util.Pair;

/**
 * @author yu zhang
 */
public class No7 {
    public int solution(Pair<Integer, int[]>[] weights, Pair<Integer, int[]>[] vals, int capacity) {
        int n = weights.length;
        int[][] maxValInAttachPrd = new int[n][];
        for (int i = 0; i < n; i++) {
            maxValInAttachPrd[i] = solution(weights[i].getValue(), vals[i].getValue(), capacity);
        }
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < n; i++) {
            int weight = weights[i].getKey(), val = vals[i].getKey();
            for (int j = capacity; j >= weight; j--) {
                for (int k = weight; j >= k; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k] + maxValInAttachPrd[i][k - weight] + val);
                }
            }
        }
        return dp[capacity];
    }

    private int[] solution(int[] weights, int[] vals, int capacity) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < n; i++) {
            int weight = weights[i], val = vals[i];
            for (int j = capacity; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j-weight] + val);
            }
        }
        return dp;
    }
}
