package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2580 {

    public static void main(String[] args) {
        int[][] ranges = {
                {10, 11}, {25, 28}, {29, 34}, {49, 51}, {58, 66}, {77, 84}, {91, 91}, {100, 104}, {120, 121}, {127, 128}, {151, 151}, {156, 168}, {0, 8}
        };
        System.out.println(new Ex2580().countWays(ranges));
    }

    private static final int mod = 1_000_000_007;

    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int n = ranges.length;
        int[] last = ranges[0];
        int cnt = 1;
        long ans = 1;
        for (int i = 1; i < n; i++) {
            int start1 = last[0], end1 = last[1];
            int start2 = ranges[i][0], end2 = ranges[i][1];
            if (!(start2 <= end1 || start1 == start2)) {
                last = ranges[i];
                cnt++;
                continue;
            }
            last[0] = Math.min(last[0], ranges[i][0]);
            last[1] = Math.max(last[1], ranges[i][1]);
        }
        for (int i = 0; i < cnt; i++) {
            ans = ans * 2 % mod;
        }
        return (int) ans;
    }

    private long cal(long n, long k) {
        long a = 1, b = 1;
        if (k > n / 2) {
            k = n - k;
        }
        for (long i = 1; i <= k; i++) {
            a = (a * (n + 1 - i)) % mod;
            b = (b * i) % mod;
        }
        return ((a % mod) / (b % mod)) % mod;
    }
}
