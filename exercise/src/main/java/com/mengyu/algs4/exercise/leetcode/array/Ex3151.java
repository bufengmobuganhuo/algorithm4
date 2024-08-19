package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex3151 {
    public boolean isArraySpecial(int[] nums) {
        boolean expectOdd = nums[0] % 2 == 0;
        for (int i = 1; i < nums.length; i++) {
            if (expectOdd && nums[i] % 2 == 0) {
                return false;
            } else if (!expectOdd && nums[i] % 2 != 0) {
                return false;
            }
            expectOdd = !expectOdd;
        }
        return true;
    }
}
