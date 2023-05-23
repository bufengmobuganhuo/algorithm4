package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex152_1 {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] fmax = new int[len];
        int[] fmin = new int[len];
        fmax[0] = nums[0];
        fmin[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            fmax[i] = Math.max(fmin[i - 1] * nums[i], Math.max(fmax[i - 1] * nums[i], nums[i]));
            fmin[i] = Math.min(fmin[i - 1] * nums[i], Math.min(fmax[i - 1] * nums[i], nums[i]));
            max = Math.max(max, fmax[i]);
        }
        return max;
    }
}
