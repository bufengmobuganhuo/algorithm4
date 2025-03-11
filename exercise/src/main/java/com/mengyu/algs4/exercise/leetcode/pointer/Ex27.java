package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex27 {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int k = 0;
        for (int l = 0, r = 0; r < n;) {
            if (nums[r] != val) {
                if (l != r) {
                    int tmp = nums[r];
                    nums[r] = nums[l];
                    nums[l] = tmp;
                }
                l++;
                k++;
            }
            r++;
        }
        return k;
    }
}
