package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2740 {

    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            ans = Math.min(ans, nums[i + 1] - nums[i]);
        }
        return ans;
    }
}
