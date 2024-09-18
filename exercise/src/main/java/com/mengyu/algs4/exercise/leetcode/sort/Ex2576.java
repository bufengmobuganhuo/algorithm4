package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2576 {

    public static void main(String[] args) {
        int[] nums = {
                57, 40, 57, 51, 90, 51, 68, 100, 24, 39, 11, 85, 2, 22, 67, 29, 74, 82, 10, 96, 14, 35, 25, 76, 26, 54, 29, 44, 63, 49, 73, 50, 95, 89, 43, 62, 24, 88, 88, 36, 6, 16, 14, 2, 42, 42, 60, 25, 4, 58, 23, 22, 27, 26, 3, 79, 64, 20, 92
        };
        System.out.println(new Ex2576().maxNumOfMarkedIndices(nums));
    }

    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (2 * nums[0] > nums[n - 1]) {
            return 0;
        }
        int leftPtr = 0;
        for (int rightPtr = (n + 1) / 2; rightPtr < n; rightPtr++) {
            if (2 * nums[leftPtr] <= nums[rightPtr]) {
                leftPtr++;
            }
        }
        return 2 * leftPtr;
    }
}
