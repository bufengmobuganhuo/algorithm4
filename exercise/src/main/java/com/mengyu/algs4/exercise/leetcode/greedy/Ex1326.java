package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex1326 {
    public static void main(String[] args) {
        int[] ranges = {3, 4, 1, 1, 0, 0};
        System.out.println(new Ex1326().minTaps(5, ranges));
    }


    public int minTaps2(int n, int[] ranges) {
        int[] rightMost = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            rightMost[i] = i;
        }
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            rightMost[start] = Math.max(rightMost[start], end);
        }
        int last = 0, ret = 0, pre = 0;
        for (int i = 0; i < n; i++) {
            last = Math.max(last, rightMost[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                ret++;
                pre = last;
            }
        }
        return ret;
    }

    /**
     * 1. rightMost[i]，以位置i为左端点的子区间中，能到达的最远（右）位置
     */
    public int minTaps(int n, int[] ranges) {
        int[] rightMost = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            // 因为ranges.length = n + 1，并且ranges[i] >= 0，则说明最起码每个点会有覆盖
            rightMost[i] = i;
        }
        // 考虑了水龙头后，更新rightMost
        for (int i = 0; i < n + 1; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            rightMost[start] = Math.max(rightMost[start], end);
        }
        int count = 0, last = -1, preLast = 0;
        for (int i = 0; i < n; i++) {
            // 记录当前位置所在的所有区间中，能到达的最右位置
            last = Math.max(last, rightMost[i]);
            // 如果相等，则说明[i, i + 1]之间没有覆盖，说明无法覆盖
            if (last == i) {
                return -1;
            }
            // 如果上一次的最右位置到达了，则说明子区间用完了，需要开启一个新的子区间
            if (preLast == i) {
                count++;
                preLast = last;
            }
        }
        return count;
    }
}
