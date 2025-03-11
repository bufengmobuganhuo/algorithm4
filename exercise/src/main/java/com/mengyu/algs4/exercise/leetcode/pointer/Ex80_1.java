package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex80_1 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(new Ex80_1().removeDuplicates(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }
        int l = 2, r = 2;
        while (r < nums.length) {
            if (nums[l - 2] != nums[r]) {
                nums[l] = nums[r];
                l++;
            }
            r++;
        }
        return l;
    }
}
