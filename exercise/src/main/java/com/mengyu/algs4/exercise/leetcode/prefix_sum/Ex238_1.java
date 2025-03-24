package com.mengyu.algs4.exercise.leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex238_1 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int multi = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = multi;
            multi *= nums[i];
        }
        multi = 1;
        for (int i = 0; i < n; i++) {
            res[i] = multi * res[i];
            multi *= nums[i];
        }
        return res;
    }
}
