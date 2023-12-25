package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex162_1 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(new Ex162_1().findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr <= rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            int mid = nums[midPtr];
            int left = midPtr > 0 ? nums[midPtr - 1] : Integer.MIN_VALUE;
            int right = midPtr < nums.length - 1 ? nums[midPtr + 1] : Integer.MIN_VALUE;
            if (mid > left && mid > right) {
                return midPtr;
            } else if (mid < right) {
                leftPtr = midPtr + 1;
            } else if (mid > right) {
                rightPtr = midPtr - 1;
            }
        }
        return -1;
    }
}
