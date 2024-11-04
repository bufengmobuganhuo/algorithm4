package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3180 {
    /**
     * 假设rewardValues的最大值为m，则在取到m之前的总奖励一定 < m（因为取到m之前取到的数一定 < m，而又要求奖励要小于x，所以总奖励肯定小于m）
     * 因此当最大值为m时，能取到的最大值为2m-1
     * 令dp[k]为是否可以取到积分k，则当遍历到rewardValues[i]=x时，dp[k]取决于dp[k-x]
     */
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int m = rewardValues[rewardValues.length - 1];
        boolean[] dp = new boolean[2 * m];
        dp[0] = true;
        for (int x : rewardValues) {
            for (int k = 2 * x - 1; k - x >= 0; k--) {
                if (dp[k - x]) {
                    dp[k] = true;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 2 * m; i++) {
            if (dp[i]) {
                res = i;
            }
        }
        return res;
    }
}
