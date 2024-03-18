package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex2834 {

    public static void main(String[] args) {
        System.out.println(new Ex2834().minimumPossibleSum(2, 3));
    }

    private static final int MOD = 1_000_000_007;

    public int minimumPossibleSum(int n, int target) {
        int m = target / 2;
        if (m >= n) {
            return (int) ((long) (1 + n) * n / 2 % MOD);
        }
        return (int) (((long) (1 + m) * m / 2 +
                ((long) target + target + (n - m) - 1) * (n - m) / 2) % MOD);
    }
}
