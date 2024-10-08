package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2535 {
    public int differenceOfSum(int[] nums) {
        int sum1 = 0, sum2 = 0;
        for (int num : nums) {
            sum1 += num;
            while (num != 0) {
                sum2 += (num % 10);
                num /= 10;
            }
        }
        return Math.abs(sum1 - sum2);
    }
}
