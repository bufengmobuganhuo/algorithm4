package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex540_1 {
    public int singleNonDuplicate(int[] nums) {
        int leftPtr = 0, rightPtr = nums.length;
        while (leftPtr <= rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            int mid = nums[midPtr];
            int midLeft = midPtr > 0 ? nums[midPtr - 1] : -1;
            int midRight = midLeft < nums.length - 1 ? nums[midPtr + 1] : -1;
            if (mid != midLeft && mid != midRight) {
                return mid;
            }
            if (mid == midLeft) {
                if ((midPtr + 1) % 2 != 0) {
                    rightPtr = midPtr;
                } else {
                    leftPtr = midPtr + 1;
                }
            } else {
                if ((nums.length - midPtr) % 2 != 0) {
                    leftPtr = midPtr;
                } else {
                    rightPtr = midPtr - 1;
                }
            }
        }
        return -1;
    }
}
