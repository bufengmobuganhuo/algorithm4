package com.mengyu.algs4.exercise.leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex3158 {
    public int duplicateNumbersXOR(int[] nums) {
        int ans = 0;
        long visited = 0;
        for (int num : nums) {
            if (((visited >> num) & 1) == 1) {
                ans ^= num;
            } else {
                visited |= (1L << num);
            }
        }
        return ans;
    }
}
