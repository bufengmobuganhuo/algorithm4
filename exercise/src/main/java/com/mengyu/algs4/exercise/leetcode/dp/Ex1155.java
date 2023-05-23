package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/3/11 上午9:58
 * TODO
 */
public class Ex1155 {
    public static void main(String[] args) {
        Ex1155 ex1155 = new Ex1155();
        System.out.println(ex1155.numRollsToTarget(30, 30, 500));
    }

    public int numRollsToTarget(int d, int f, int target) {
        int[][] dp = new int[d + 1][target + 1];
        int mod = 1000000007;
        for (int i = 1; i < f + 1 && i < target + 1; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i < d + 1; i++) {
            for (int j = i; j < target + 1; j++) {
                for (int k = 1; k < j && k < f + 1; k++) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j - k]) % mod;
                }
            }
        }
        return dp[d][target];
    }
}
