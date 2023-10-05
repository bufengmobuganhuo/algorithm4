package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @date 2023/10/4 11:30
 * TODO
 */
public class Ex518 {

    public static void main(String[] args) {
        int[] coins = {1, 2 ,5};
        System.out.println(new Ex518().change(5, coins));
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
