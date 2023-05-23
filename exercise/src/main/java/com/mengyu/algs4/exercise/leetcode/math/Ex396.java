package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex396 {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int max = Integer.MIN_VALUE, f = 0;
        // 找f(0)
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
            sum += nums[i];
        }
        max = Math.max(max, f);
        for (int i = 1; i < n; i++) {
            f = f + sum - n * nums[n - i];
            max = Math.max(f, max);
        }
        return max;
    }
}
