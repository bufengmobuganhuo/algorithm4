package com.mengyu.algs4.exercise.leetcode.rank.year2023.january8;

/**
 * @author yuzhang
 * @date 2023/1/8 10:07
 * TODO
 */
public class Ex1 {
    public int maximumCount(int[] nums) {
        int pos = 0, neg = 0;
        for (int num : nums) {
            if (num > 0) {
                pos++;
            } else if (num < 0) {
                neg++;
            }
        }
        return Math.max(pos, neg);
    }
}
