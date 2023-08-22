package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex698_3 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        sum /= k;
        Arrays.sort(nums);
        int rightPtr = nums.length - 1;
        if (nums[rightPtr] > sum) {
            return false;
        }
        while (rightPtr >= 0 && nums[rightPtr] == sum) {
            rightPtr--;
            k--;
        }
        int[] groups = new int[k];
        return backtracking(groups, nums, (int)sum, rightPtr);
    }

    private boolean backtracking(int[] groups, int[] nums, int sum, int rightPtr) {
        if (rightPtr < 0) {
            return true;
        }
        int val = nums[rightPtr--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + val > sum) {
                continue;
            }
            groups[i] += val;
            if (backtracking(groups, nums, sum, rightPtr)) {
                return true;
            }
            groups[i] -= val;
        }
        return false;
    }
}
