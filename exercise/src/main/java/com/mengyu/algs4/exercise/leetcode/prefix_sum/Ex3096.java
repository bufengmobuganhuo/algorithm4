package com.mengyu.algs4.exercise.leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex3096 {

    public static void main(String[] args) {
        System.out.println(new Ex3096().minimumLevels(new int[]{1,1}));
    }

    public int minimumLevels2(int[] possible) {
        int n = possible.length;
        int total = 0;
        for (int pos : possible) {
            total += (pos == 0 ? -1 : 1);
        }
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur += (possible[i] == 0 ? -1 : 1);
            if (cur > total / 2 && i < n - 1) {
                return i + 1;
            }
        }
        return -1;
    }

    public int minimumLevels(int[] possible) {
        int n = possible.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + (possible[i - 1] == 0 ? -1 : 1);
        }
        int ans = -1;
        for (int i = 1; i < n + 1; i++) {
            if (prefixSum[i] > prefixSum[n] - prefixSum[i]) {
                ans = i;
                break;
            }
        }
        return ans == n ? -1 : ans;
    }
}
