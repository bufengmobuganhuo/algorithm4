package com.mengyu.algs4.exercise.leetcode.rank.year2022.october16;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2022/10/16 10:08
 * TODO
 */
public class Ex1 {
    public int findMaxK(int[] nums) {
        Arrays.sort(nums);
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr < rightPtr) {
            if (nums[leftPtr] + nums[rightPtr] == 0) {
                return nums[rightPtr];
            } else if (-nums[leftPtr] > nums[rightPtr]) {
                leftPtr++;
            } else {
                rightPtr--;
            }
        }
        return -1;
    }
}
