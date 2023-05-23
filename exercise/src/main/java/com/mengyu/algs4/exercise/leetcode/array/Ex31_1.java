package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/3/3 上午9:29
 * TODO
 */
public class Ex31_1 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        Ex31_1 ex31_1 = new Ex31_1();
        ex31_1.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int index = nums.length - 1;
        while (index > 0 && nums[index - 1] >= nums[index]) {
            index--;
        }
        if (index > 0) {
            int i = nums.length - 1;
            while (i > 0 && nums[i] < nums[index - 1]) {
                i--;
            }
            exch(nums, i, index - 1);
        }
        reverse(nums, index);

    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            exch(nums, left, right);
            left++;
            right--;
        }
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
