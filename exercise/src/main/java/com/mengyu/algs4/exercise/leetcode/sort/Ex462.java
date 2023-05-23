package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex462 {
    public static void main(String[] args) {
        int[] nums = {1, 4, 7,5, 9};
        System.out.println(new Ex462().minMoves3(nums));
    }
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int mid = nums[nums.length/2];
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs((num - mid));
        }
        return sum;
    }

    private int[] nums;

    // 使用快速排序中的切分
    public int minMoves3(int[] nums) {
        this.nums = nums;
        int mid = select(0, nums.length - 1, nums.length/2);
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - mid);
        }
        return sum;
    }

    private int select(int start, int end, int idx) {
        if (start >= end) {
            return nums[start];
        }
        int partitionIdx = partition(start, end);
        if (partitionIdx > idx) {
            return select(start, partitionIdx - 1, idx);
        } else if (partitionIdx < idx) {
            return select(partitionIdx + 1, end, idx);
        } else {
            return nums[partitionIdx];
        }
    }

    private int partition(int start, int end) {
        int left = start, right = end + 1;
        int partitionEle = nums[left];
        while (true) {
            while (nums[++left] < partitionEle) {
                if (left == end) {
                    break;
                }
            }
            while (nums[--right] > partitionEle) {
                if (right == start) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            swap(left, right);
        }
        swap(start, right);
        return right;
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
