package com.mengyu.algs4.exercise.lcp.math;

/**
 * @author yu zhang
 */
public class Ex06 {
    public int minCount(int[] coins) {
        int cnt = 0;
        for (int coin : coins) {
            cnt += (coin / 2);
            cnt += (coin % 2);
        }
        return cnt;
    }
}
