package com.mengyu.algs4.exercise.bytedance.jan8th;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/1/8 上午10:08
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 4};
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int cap = sum / 2;
        int[] dp = new int[cap + 1];
        for (int num : nums) {
            for (int j = cap; j >= j - num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        return cap == dp[cap];
    }
}
