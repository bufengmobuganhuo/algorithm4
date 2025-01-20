package com.mengyu.algs4.exercise.leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex2270 {
    public int waysToSplitArray(int[] nums) {
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }
        int n = nums.length;
        long preSum = 0;
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            preSum += nums[i];
            if (preSum >= sum - preSum) {
                cnt++;
            }
        }
        return cnt;
    }
}
