package com.mengyu.algs4.exercise.leetcode.boyerMoore;

/**
 * @author yu zhang
 */
public class Ex169_1 {

    public int majorityElement2(int[] nums) {
        int candidate = -1, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count++;
            } else if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    /**
     * 分治法（nlogn）
     */
    public int majorityElement(int[] nums) {
        return partition(nums, 0, nums.length - 1);
    }

    private int partition(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return nums[lo];
        }
        int mid = lo + (hi - lo) / 2;
        int left = partition(nums, lo, mid);
        int right = partition(nums, mid + 1, hi);

        if (left == right) {
            return left;
        }
        int count = count(nums, lo, hi, left);
        return count > (hi - lo + 1) / 2 ? left : right;
    }

    private int count(int[] nums, int lo, int hi, int candidate) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == candidate) {
                count++;
            }
        }
        return count;
    }
}
