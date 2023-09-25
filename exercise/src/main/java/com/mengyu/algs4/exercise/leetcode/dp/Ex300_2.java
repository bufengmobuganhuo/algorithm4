package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex300_2 {

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[1] = nums[0];
        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            if (dp[maxLen] < nums[i]) {
                dp[++maxLen] = nums[i];
            } else {
                int leftPtr = 0, rightPtr = maxLen;
                while (leftPtr < rightPtr) {
                    int midPtr = leftPtr + (rightPtr - leftPtr + 1) / 2;
                    if (dp[midPtr] >= nums[i]) {
                        rightPtr = midPtr - 1;
                    } else {
                        leftPtr = midPtr;
                    }
                }
                if (leftPtr + 1 < maxLen + 1 && dp[leftPtr + 1] == nums[i]) {

                } else {
                    dp[leftPtr + 1] = nums[i];
                }
            }
        }
        return maxLen;
    }
}
