package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex713 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Ex713().numSubarrayProductLessThanK(nums, 0));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int leftPtr = 0, rightPtr = 0;
        int multi = 1;
        while (rightPtr < nums.length) {
            multi *= nums[rightPtr];
            while (leftPtr <= rightPtr && multi >= k) {
                multi /= nums[leftPtr];
                leftPtr++;
            }
            // 必须以nums[rightPtr]结尾的子数组个数
            // nums[rightPtr]
            // nums[rightPtr - 1], nums[rightPtr]
            // nums[rightPtr - 2], nums[rightPtr - 1], nums[rightPtr]
            // ...
            // nums[leftPtr], nums[leftPtr + 1] ... , nums[rightPtr - 1], nums[rightPtr]
            count += rightPtr - leftPtr + 1;
            rightPtr++;
        }
        return count;
    }
}
