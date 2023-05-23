package com.mengyu.algs4.exercise.leetcode.rank.year2022.july17;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2022/7/17 11:42
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {
        int[] nums = {2,3,2,4,3}, numsDivide = {9,6,9,3,15};
        System.out.println(new Ex4().minOperations(nums, numsDivide));
    }

    public int minOperations(int[] nums, int[] numsDivide) {
        int gcd = numsDivide[0];
        for (int num : numsDivide) {
            gcd = gcd(gcd, num);
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (gcd % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int gcd(int a, int b) {
        int mod = 0;
        do {
            mod = a % b;
            a = b;
            b = mod;
        } while (b != 0);
        return a;
    }
}
