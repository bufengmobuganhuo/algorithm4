package com.mengyu.algs4.exercise.leetcode.dfs;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3040 {

    public static void main(String[] args) {
        System.out.println(new Ex3040().maxOperations(new int[]{3,3,2,4,1,5,2,4,6,4}));
    }

    private int[][] cache;

    public int maxOperations(int[] nums) {
        int n = nums.length;
        cache = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }
        int max = search(nums, nums[0] + nums[1], 2, n - 1) + 1;
        max = Math.max(max, search(nums, nums[n - 2] + nums[n - 1], 0, n - 3) + 1);
        return Math.max(max, search(nums, nums[0] + nums[n - 1], 1, n - 2) + 1);
    }

    private int search(int[] nums, int target, int start, int end) {
        if (end <= start) {
            return 0;
        }
        if (cache[start][end] != -1) {
            return cache[start][end];
        }
        int max = 0;
        if (nums[start] + nums[start + 1] == target) {
            if (start + 2 <= end) {
                max = Math.max(search(nums, target, start + 2, end) + 1, max);
            }
        }
        if (nums[start] + nums[end] == target) {
            if (start + 1 <= end && end - 1 >= start) {
                max = Math.max(search(nums, target, start + 1, end - 1) + 1, max);
            }
        }
        if (nums[end - 1] + nums[end] == target) {
            if (end - 2 >= start) {
                max = Math.max(search(nums, target, start, end - 2) + 1, max);
            }
        }
        cache[start][end] = max;
        return max;
    }
}
