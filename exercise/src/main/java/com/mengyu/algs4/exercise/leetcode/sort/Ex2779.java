package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2779 {

    public static void main(String[] args) {

    }

    public int maximumBeauty2(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, j = 0; j < n; j++) {
            while (nums[j] - nums[i] > 2 * k) {
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    public int maximumBeauty(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int leftPtr = 0, rightPtr = 0;
        int ans = 0;
        while (rightPtr < n) {
            int num1 = nums[leftPtr], num2 = nums[rightPtr];
            while (leftPtr <= rightPtr && !overlapped(num1 + k, num1 - k, num2 - k)) {
                leftPtr++;
                num1 = nums[leftPtr];
            }
            ans = Math.max(ans, rightPtr - leftPtr + 1);
            rightPtr++;
        }
        return Math.max(ans, rightPtr - leftPtr);
    }

    private boolean overlapped(int range11, int range10, int range20) {
        return range11 >= range20 || range20 == range10;
    }
}
