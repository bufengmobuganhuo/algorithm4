package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex795_1 {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int last1 = -1, last2 = -1;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                last1 = i;
            } else if (nums[i] > right) {
                last1 = -1;
                last2 = i;
            }
            if (last1 != -1) {
                cnt += (last1 - last2);
            }
        }
        return cnt;
    }
}
