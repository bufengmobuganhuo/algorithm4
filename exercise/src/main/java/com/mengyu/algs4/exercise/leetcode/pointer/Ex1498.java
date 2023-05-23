package com.mengyu.algs4.exercise.leetcode.pointer;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1498 {

    public static void main(String[] args) {
        int[] nums = {7, 10, 7, 3, 7, 5, 4};
        System.out.println(new Ex1498().numSubseq2(nums, 12));
    }
    private static final int MOD = (int) (Math.pow(10, 9) + 7);

    private int[] f = new int[100000];

    public Ex1498() {
        f[0] = 1;
        for (int i = 1; i < 100000; i++) {
            f[i] = (int) (((long) f[i-1] << 1) % MOD);
        }
    }

    public int numSubseq2(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        long count = 0;
        for (int i = 0; i < len; i++) {
            int idx = find(nums, target - nums[i]);
            if (idx < i) {
                continue;
            }
            count = (count + f[idx - i]) % MOD;
        }
        return (int) count;
    }

    private int find(int[] nums, int target) {
        int start = -1, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }
        if (start < nums.length - 1 && nums[start + 1] == target) {
            return start + 1;
        }
        return start;
    }

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int leftPtr = 0, len = nums.length, rightPtr = len -1;
        long count = 0;
        while (leftPtr <= rightPtr) {
            if (nums[leftPtr] + nums[rightPtr] > target) {
                rightPtr--;
            } else {
                count = (count + f[rightPtr - leftPtr]) % MOD;
                leftPtr++;
            }
        }
        return (int) count;
    }
}
