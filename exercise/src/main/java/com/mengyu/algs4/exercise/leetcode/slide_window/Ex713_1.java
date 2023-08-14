package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex713_1 {
    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        System.out.println(new Ex713_1().numSubarrayProductLessThanK(nums, 100));
    }
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int cur = 1, leftPtr = 0, rightPtr = 0;
        int cnt = 0;
        while (rightPtr < nums.length) {
            int num = nums[rightPtr];
            cur *= num;
            rightPtr++;
            while (cur >= k && leftPtr < rightPtr) {
                cur /= nums[leftPtr];
                leftPtr++;
            }
            cnt += (rightPtr - leftPtr);
        }
        return cnt;
    }
}
