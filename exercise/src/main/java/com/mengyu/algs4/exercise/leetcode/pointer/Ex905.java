package com.mengyu.algs4.exercise.leetcode.pointer;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex905 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2};
        System.out.println(Arrays.toString(new Ex905().sortArrayByParity(nums)));
    }
    public int[] sortArrayByParity(int[] nums) {
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (true) {
            while (leftPtr < rightPtr && nums[leftPtr] % 2 == 0) {
                leftPtr++;
            }
            while (rightPtr >= 0 && nums[rightPtr] % 2 == 1) {
                rightPtr--;
            }
            if (leftPtr >= rightPtr) {
                break;
            }
            int tmp = nums[leftPtr];
            nums[leftPtr] = nums[rightPtr];
            nums[rightPtr] = tmp;
        }
        return nums;
    }
}
