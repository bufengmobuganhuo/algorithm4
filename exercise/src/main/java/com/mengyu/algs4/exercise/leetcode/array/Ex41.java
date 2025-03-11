package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex41 {
    /**
     * 1. 对于一个长度为n的数组，它没出现的最小正整数一定在[1, n + 1]之间（因为最多就n个数）
     * 2. 我们把每个数映射到对应位置：如1在nums[0]，2在nums[1]...，如此映射下去，直到 nums[num - 1] != num时，这个num就是没出现的。
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // nums[i]应该在nums[i - 1]上
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
