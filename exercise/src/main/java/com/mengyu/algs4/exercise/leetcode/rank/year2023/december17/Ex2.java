package com.mengyu.algs4.exercise.leetcode.rank.year2023.december17;

import java.util.Arrays;

/**
 * @date 2023/12/17 10:28
 * TODO
 */
public class Ex2 {
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int idx = 0;
        int[][] ans = new int[n / 3][3];
        for (int i = 2; i < n; i += 3) {
            if (nums[i] - nums[i - 2] <= k) {
                System.arraycopy(nums, i - 2, ans[idx], 0, 3);
                idx++;
            } else {
                return new int[][]{};
            }
        }
        return ans;
    }
}
