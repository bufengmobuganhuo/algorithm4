package com.mengyu.algs4.exercise.leetcode.rank.year2023.february12;

/**
 * @author yuzhang
 * @date 2023/2/12 10:03
 * TODO
 */
public class Ex1 {
    public long findTheArrayConcVal(int[] nums) {
        int len = nums.length;
        int leftPtr = 0, rightPtr = len - 1;
        long ans = 0;
        while (leftPtr < rightPtr) {
           int left = nums[leftPtr], right = nums[rightPtr];
           String num = String.valueOf(left) + right;
           ans += Long.parseLong(num);
           leftPtr++;
           rightPtr--;
        }
        if (leftPtr == rightPtr) {
            ans += nums[leftPtr];
        }

        return ans;
    }
}
