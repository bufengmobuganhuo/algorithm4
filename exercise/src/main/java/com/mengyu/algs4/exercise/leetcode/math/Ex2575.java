package com.mengyu.algs4.exercise.leetcode.math;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2575 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Ex2575().divisibilityArray("998244353", 3)));
    }

    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] ans = new int[n];
        long a = 10 % m;
        long mod = 0;
        for (int i = 0; i < n; i++) {
            int y = word.charAt(i) - '0';
            mod = ((a * mod) % m + y % m) % m;
            ans[i] = mod == 0 ? 1 : 0;
        }
        return ans;
    }
}
