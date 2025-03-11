package com.mengyu.algs4.exercise.leetcode.boyerMoore;

/**
 * @author yu zhang
 */
public class Ex169_3 {
    public int majorityElement(int[] nums) {
        int candidate = -1, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
