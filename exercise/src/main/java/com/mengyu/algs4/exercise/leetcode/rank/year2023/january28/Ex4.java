package com.mengyu.algs4.exercise.leetcode.rank.year2023.january28;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex4 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 1, 3, 3};
        System.out.println(new Ex4().minCost(nums, 2));
    }

    /**
     * 1. dp[i]: 前i个元素切分后的cost
     * 2. dp[i] = min(dp[i], dp[j] + importance[j...i]) 0 <= j < i
     * 3. 对于importance[k],从右到左遍历:
     *  (1) 如果新加入的元素在nums[k+1...i]中不存在，那么importance[k] = importance[k+1]
     *  (2) 如果新加入的元素在nums[k+1...i]中存在1个，那么importance[k] = importance[k+1] + 2
     *  (3) 如果新加入的元素在nums[k+1...i]中存在多个，那么importance[k] = importance[k+1] + 1
     */
    public int minCost(int[] nums, int k) {
        int len = nums.length;
        // 多取1个
        int[] dp = new int[len + 1];
        int[] state = new int[len];
        for (int i = 1; i < len + 1; i++) {
            // 一开始只有一个元素，取0+k
            int importance = k;
            dp[i] = Integer.MAX_VALUE;
            // 记录nums[j..i]中元素的个数
            Arrays.fill(state, 0);
            for (int j = i - 1; j >= 0; j--) {
                int num = nums[j];
                if (state[num] == 1) {
                    importance += 2;
                } else if (state[num] > 1) {
                    importance += 1;
                }
                // 记录出现元素的个数
                state[num]++;
                dp[i] = Math.min(dp[i], dp[j] + importance);
            }
        }
        return dp[len];
    }
}
