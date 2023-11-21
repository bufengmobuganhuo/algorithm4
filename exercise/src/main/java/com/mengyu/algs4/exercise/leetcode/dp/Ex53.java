package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex53 {
    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        int prefixSum = 0;
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            // 如果这个num不能让子序列和更大，则结束这个子序列，开始一个新子序列
            prefixSum = Math.max(prefixSum + num, num);
            res = Math.max(res, prefixSum);
        }
        return res;
    }
}
