package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1561 {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length;
        int ans = 0;
        for (int first = n - 1, second = n - 2, third = 0; third < second ; first -= 2, second -= 2, third++) {
            ans += piles[second];
        }
        return ans;
    }
}
