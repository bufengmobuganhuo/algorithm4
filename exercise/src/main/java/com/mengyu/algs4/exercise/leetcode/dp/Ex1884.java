package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1884 {
    public int twoEggDrop(int n) {
        // 当楼层为i时，操作次数
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            // 枚举楼层，从第k层开始扔鸡蛋
            for (int k = 1; k < i + 1; k++) {
                // 当在k层扔第一颗鸡蛋碎了，则需要从1, 2, .., k - 1开始扔第二颗鸡蛋，次数为k - 1
                // 当在k层扔第一颗鸡蛋没有碎，则说明f在k + 1 ~ i，这等价于从一个高度为i - k的楼操作，则为dp[i - k]
                dp[i] = Math.min(dp[i], Math.max(k - 1, dp[i - k]) + 1);
            }
        }
        return dp[n];
    }
}
