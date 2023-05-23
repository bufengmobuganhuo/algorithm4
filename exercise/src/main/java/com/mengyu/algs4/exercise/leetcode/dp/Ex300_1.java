package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex300_1 {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        Ex300_1 ex300_1 = new Ex300_1();
        System.out.println(ex300_1.lengthOfLIS2(nums));
    }

    public int lengthOfLIS2(int[] nums) {
        int len = 1;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > dp[len - 1]) {
                len++;
                dp[len - 1] = nums[i];
            } else {
                int leftPtr = -1, rightPtr = len - 1;
                while (leftPtr < rightPtr) {
                    int mid = leftPtr + (rightPtr - leftPtr + 1) / 2;
                    if (dp[mid] >= nums[i]) {
                        rightPtr = mid - 1;
                    } else {
                        leftPtr = mid;
                    }
                }
                if (leftPtr + 1 < len - 1 && dp[leftPtr + 1] == nums[i]) {
                } else {
                    dp[leftPtr + 1] = nums[i];
                }
            }
        }
        return len;
    }


    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int maxLen = 1;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }
        return maxLen;
    }
}
