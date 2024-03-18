package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex2789 {

    public static void main(String[] args) {
        System.out.println(new Ex2789().maxArrayValue(new int[]{2, 3, 7, 9, 3}));
    }

    public long maxArrayValue(int[] nums) {
        long ans = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            ans = nums[i] <= ans ? nums[i] + ans : nums[i];
        }
        return ans;
    }
}
