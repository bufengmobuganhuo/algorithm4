package com.mengyu.algs4.exercise.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yu zhang
 */
public class Ex826 {

    public static void main(String[] args) {
        Ex826 ex826 = new Ex826();
        System.out.println(ex826.maxProfitAssignment(new int[]{2,4,6,8,10}, new int[]{10,20,30,40,50}, new int[]{4,5,6,7}));
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int m = difficulty.length, n = worker.length;
        int[][] pairs = new int[m][2];
        for (int i = 0; i < m; i++) {
            pairs[i] = new int[]{difficulty[i], profit[i]};
        }
        Arrays.sort(worker);
        Arrays.sort(pairs, Comparator.comparingInt(o -> o[0]));
        int i = 0, ans = 0, max = 0;
        for (int w : worker) {
            while (i < m && pairs[i][0] <= w) {
                max = Math.max(pairs[i][1], max);
                i++;
            }
            ans += max;
        }
        return ans;
    }
}
