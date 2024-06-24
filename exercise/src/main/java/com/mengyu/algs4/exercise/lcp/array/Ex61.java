package com.mengyu.algs4.exercise.lcp.array;

/**
 * @author yu zhang
 */
public class Ex61 {
    public static void main(String[] args) {
        System.out.println(new Ex61().temperatureTrend(new int[]{21,18,18,18,31}, new int[]{34,32,16,16,17}));
    }
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        int ans = 0, cur = 0;
        for (int i = 1; i < n; i++) {
            int trend1 = getTrend(temperatureA[i - 1], temperatureA[i]);
            int trend2 = getTrend(temperatureB[i - 1], temperatureB[i]);
            if (trend1 == trend2) {
                cur++;
            } else {
                ans = Math.max(ans, cur);
                cur = 0;
            }
        }
        return ans;
    }

    private int getTrend(int a, int b) {
        if (a == b) {
            return 0;
        }
        return a > b ? 1 : -1;
    }
}
