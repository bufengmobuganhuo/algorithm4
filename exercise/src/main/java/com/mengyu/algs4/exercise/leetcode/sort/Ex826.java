package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yu zhang
 */
public class Ex826 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int m = difficulty.length, n = worker.length;
        int[][] pairs = new int[m][2];
        for (int i = 0; i < m; i++) {
            pairs[i] = new int[]{difficulty[i], profit[i]};
        }
        Arrays.sort(pairs, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(worker);
        int ans = 0, idx = 0, max = 0;
        for (int w : worker) {
            while (idx < m && w >= pairs[idx][0]) {
                max = Math.max(max, pairs[idx][1]);
                idx++;
            }
            ans += max;
        }
        return ans;
    }
}
