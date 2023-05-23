package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/12/14 上午9:53
 * TODO
 */
public class Ex5627 {
    public static void main(String[] args) {
        Ex5627 ex5627 = new Ex5627();
        int[] stones = {5, 3, 1, 4, 2};
        System.out.println(ex5627.stoneGameVII(stones));
    }

    /**
     * 1. 维护一个数组preSum，preSum[i]表示stones前i个数字的和
     * 2. dp[i][j]：当数组范围为[i,j]时，某人出手时能获得的净分值：
     *      1⃣️ 当选stones[i]时，另一个人的净得分是dp[i+1][j],那么当前人能获得的分值=preSum[j]-preSum[i]
     *          则净分值=preSum[j]-preSum[i]-dp[i+1][j]
     *      2⃣️ 当选stones[j]时，另一人净得分是dp[i][j-1]，当前人能获得分值=preSum[j-1]-preSum[i-1]
     *          则净分值=preSum[j-1]-preSum[i-1]-dp[i][j-1]
     * @param stones
     * @return
     */
    public int stoneGameVII(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int[] preSum = new int[stones.length + 1];
        for (int i = 1; i <= stones.length; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }
        int[][] dp = new int[stones.length + 1][stones.length + 1];
        // 按照数组的长度来选择
        for (int len = 2; len <= stones.length; len++) {
            for (int i = 1; i + len - 1 <= stones.length; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(preSum[j]-preSum[i]-dp[i+1][j] , preSum[j-1]-preSum[i-1]-dp[i][j-1]);
            }
        }
        return dp[1][stones.length];
    }
}
