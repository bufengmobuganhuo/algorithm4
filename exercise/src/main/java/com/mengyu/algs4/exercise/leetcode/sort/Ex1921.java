package com.mengyu.algs4.exercise.leetcode.sort;

/**
 * @author yu zhang
 */
public class Ex1921 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        // timesCnt[i]: 有几个怪物的到达时间=i
        int[] timesCnt = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int times = Math.min((dist[i] + speed[i] - 1) / speed[i], n);
            timesCnt[times]++;
        }
        int cnt = 0;
        for (int i = 0; i < n + 1; i++) {
            cnt += timesCnt[i];
            if (cnt > i) {
                // 一次只能消灭一个怪物
                return i;
            }
        }
        return cnt;
    }
}
