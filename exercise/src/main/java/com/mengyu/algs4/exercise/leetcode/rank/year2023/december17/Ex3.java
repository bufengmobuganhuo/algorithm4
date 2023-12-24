package com.mengyu.algs4.exercise.leetcode.rank.year2023.december17;

import java.util.*;

/**
 * @date 2023/12/17 10:42
 * TODO
 */
public class Ex3 {

    public static void main(String[] args) {

    }

    public long minimumCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int mid = nums[n / 2];
        int x1 = 0, x2 = 0;
        // 排序后的中位数 附近 的回文数 就是结果
        for (int i = mid; i >= 0; i--) {
            if (isPalindrome(i)) {
                x1 = i;
                break;
            }
        }
        for (int i = mid; i < 1e9; i++) {
            if (isPalindrome(i)) {
                x2 = i;
                break;
            }
        }
        long cost1 = 0, cost2 = 0;
        for (int i = 0; i < n; i++) {
            cost1 += Math.abs(nums[i] - x1);
            cost2 += Math.abs(nums[i] - x2);
        }
        return Math.min(cost1, cost2);
    }

    private boolean isPalindrome(int x) {
        // 翻转数位后如果和原数相等，则是回文数
        int reverse = 0, tmp = x;
        while (x != 0) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return reverse == tmp;
    }
}
