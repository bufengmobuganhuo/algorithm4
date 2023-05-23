package com.mengyu.algs4.exercise.leetcode.rank.year2021.november28;

/**
 * @author yuzhang
 * @date 2021/11/28 10:47 上午
 * TODO
 */
public class Ex3 {
    public int minimumDeletions(int[] nums) {
        int len = nums.length;
        int minIdx = 0, maxIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[minIdx] > nums[i]) {
                minIdx = i;
            }
            if (nums[maxIdx] < nums[i]) {
                maxIdx = i;
            }
        }
        int leftPtr = Math.min(minIdx, maxIdx);
        int rightPtr = Math.max(minIdx, maxIdx);
        int res = len - leftPtr;
        res = Math.min(res, rightPtr + 1);
        res = Math.min(res, leftPtr + 1 + len - rightPtr);
        return res;
    }
}
