package com.mengyu.algs4.exercise.leetcode.prefix_sum;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2731 {

    public static void main(String[] args) {
        int[] nums = {-10, -13, 10, 14, 11};
        System.out.println(new Ex2731().sumDistance(nums, "LRLLR", 2));
    }

    private static final int MOD = 1000000007;

    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] pos = new long[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') {
                pos[i] = (long) nums[i] - d;
            } else {
                pos[i] = (long) nums[i] + d;
            }
        }
        Arrays.sort(pos);
        long res = 0;
        for (int i = 1; i < n; i++) {
            res += 1L * (pos[i] - pos[i - 1]) * i % MOD * (n - i) % MOD;
            res %= MOD;
        }
        return (int) res;
    }
}
