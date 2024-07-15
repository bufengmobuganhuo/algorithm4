package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex724 {

    public static void main(String[] args) {
        System.out.println(new Ex724().pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }

    public int pivotIndex(int[] nums) {
        int n = nums.length;
        long total = 0;
        for (int num : nums) {
            total += num;
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (total - sum - nums[i] == sum) {
                return i + 1;
            }
            sum += nums[i];
        }
        return -1;
    }
}
