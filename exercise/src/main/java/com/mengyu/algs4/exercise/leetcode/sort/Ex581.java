package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex581 {

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(new Ex581().findUnsortedSubarray(nums));
    }

    public int findUnsortedSubarray2(int[] nums) {
        int r = -1, curMax = Integer.MIN_VALUE;
        // 从左往右看应该递增
        for (int i = 0; i < nums.length; i++) {
            // 如果已经出现的最大值 <= 更右边的数，说明是递增的，不需要动
            if (curMax <= nums[i]) {
                curMax = nums[i];
            } else {
                // 如果已经出现的最大值 > 更右边的数，则说明至少从左截止到nums[i]是乱序的
                r = i;
            }
        }

        // 从右往左看应该递减
        int l = -1, curMin = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 如果右边已经出现的数 >= 左边的数，则说明是递减的，不需要动
            if (curMin >= nums[i]) {
                curMin = nums[i];
            } else {
                // 如果右边已经出现的数 < 左边的数，则说明至少从右截止到nums[i]是乱序的
                l = i;
            }
        }
        return r == -1 ? 0 : r - l + 1;
    }


    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] tmp = new int[n];
        System.arraycopy(nums, 0, tmp, 0, n);
        Arrays.sort(tmp);
        int l = 0, r = n - 1;
        while (l < n && nums[l] == tmp[l]) {
            l++;
        }
        while (r >= 0 && nums[r] == tmp[r]) {
            r--;
        }
        return l < n ? r - l + 1 : 0;
    }
}
