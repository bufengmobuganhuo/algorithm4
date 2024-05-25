package com.mengyu.algs4.exercise.leetcode.rank.year2023.december24;

import java.util.Arrays;

/**
 * @date 2023/12/24 10:08
 * TODO
 */
public class Ex1 {
    public int[] numberGame(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] ans = new int[n];
        for (int i = 0; i < n; i += 2) {
           ans[i + 1] = nums[i];
           ans[i] = nums[i + 1];
        }
        return ans;
    }
}
