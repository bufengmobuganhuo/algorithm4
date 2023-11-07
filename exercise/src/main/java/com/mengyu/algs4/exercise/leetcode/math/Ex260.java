package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex260 {
    public int[] singleNumber(int[] nums) {
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type0 = 0;
        for (int num : nums) {
            if ((lsb & num) == 0) {
                type0 ^= num;
            } else {
                type1 ^= num;
            }
        }
        return new int[]{type0, type1};
    }
}
