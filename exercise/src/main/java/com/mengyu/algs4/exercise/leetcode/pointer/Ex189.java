package com.mengyu.algs4.exercise.leetcode.pointer;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex189 {

    public static void main(String[] args) {
        int[] nums = {-1, -100, 3, 99};
        new Ex189().rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, (k % n) - 1);
        reverse(nums, k % n, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
