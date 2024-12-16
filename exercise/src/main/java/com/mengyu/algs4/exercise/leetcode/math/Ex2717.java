package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2717 {
    public int semiOrderedPermutation(int[] nums) {
        int n = nums.length;
        int idx1 = -1, idxN = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                idx1 = i;
            } else if (nums[i] == n) {
                idxN = i;
            }
        }
        if (idx1 < idxN) {
            return idx1 + n - idxN - 2;
        }
        return idx1 + n - idxN - 1;
    }
}
