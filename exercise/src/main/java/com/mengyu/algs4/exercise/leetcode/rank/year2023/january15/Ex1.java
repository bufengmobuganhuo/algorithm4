package com.mengyu.algs4.exercise.leetcode.rank.year2023.january15;

/**
 * @author yuzhang
 * @date 2023/1/15 10:02
 * TODO
 */
public class Ex1 {
    public int differenceOfSum(int[] nums) {
        int eleSum = 0, digitSum = 0;
        for (int num : nums) {
            eleSum += num;
            while (num != 0) {
                int mod = num % 10;
                num /= 10;
                digitSum += mod;
            }
        }
        return Math.abs(eleSum - digitSum);
    }
}
