package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex338_1 {
    /**
     * 0：0（0）
     * 1：1（1）
     * 2：1（10）
     * 3：2（11）
     * 4：1（100）
     * 5：2（101）
     * 可以看出来，对于一个奇数，他是前一个数（偶数）+1；对于偶数，他是上一个偶数向左移动一位（比如8是4向左移动一位，所以1的个数不变）
     */
    public int[] countBits(int n) {
        if (n == 0) {
            return new int[]{0};
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i/2];
            }else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp;
    }
}
