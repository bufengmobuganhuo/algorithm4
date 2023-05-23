package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/2/3 上午9:51
 * TODO
 */
public class Ex698_1 {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        Ex698_1 ex698_1 = new Ex698_1();
        System.out.println(ex698_1.canPartitionKSubsets(nums, 4));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return k == 0;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        sort(nums);
        int pointer = 0;
        if (nums[pointer] > target) {
            return false;
        }
        while (pointer < nums.length && nums[pointer] == target) {
            pointer++;
            k--;
        }
        return backtracking(new int[k], target, pointer, nums);
    }

    private boolean backtracking(int[] group, int target, int pointer, int[] nums) {
        if (pointer >= nums.length) {
            return true;
        }
        int value = nums[pointer++];
        for (int i = 0; i < group.length; i++) {
            if (group[i] + value <= target) {
                group[i] += value;
                if (backtracking(group, target, pointer, nums)) {
                    return true;
                }
                group[i] -= value;
            }
            if (group[i] == 0) {
                break;
            }
        }
        return false;
    }

    private void sort(int[] arr) {
        int len = arr.length;
        for (int i = (len - 1) / 2 - 1; i >= 0; i--) {
            sink(arr, i, len);
        }
        while (len > 0) {
            exch(arr, 0, --len);
            sink(arr, 0, len);
        }
    }

    // 小顶堆
    private void sink(int[] arr, int k, int len) {
        while (2 * k + 1 < len) {
            int j = 2 * k + 1;
            if (j + 1 < len && arr[j + 1] < arr[j]) {
                j++;
            }
            if (arr[k] < arr[j]) {
                break;
            }
            exch(arr, k, j);
            k = j;
        }
    }

    private void exch(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
