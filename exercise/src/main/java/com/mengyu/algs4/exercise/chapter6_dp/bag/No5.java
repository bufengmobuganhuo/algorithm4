package com.mengyu.algs4.exercise.chapter6_dp.bag;

/**
 * @author yu zhang
 */
public class No5 {
    public int solution(int[] w, int[] v, int[] vals, int W, int V) {
        int m = w.length, n = v.length;
        int[][] dp = new int[W + 1][V + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = W; j >=  w[i - 1]; j--) {
                for (int k = V; k >= w[i - 1] ; k--) {
                    dp[k][k] = Math.max(dp[k][k], dp[j - w[i - 1]][k - v[i - 1]] + vals[i - 1]);
                }
            }
        }
        return dp[W][V];
    }
}
