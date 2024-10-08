package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex2374 {

    public static void main(String[] args) {
        int[] edges = {2, 0, 0, 2};
        System.out.println(new Ex2374().edgeScore(edges));
    }

    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] cntMap = new long[n];
        long max = 0, maxNo = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            cntMap[edges[i]] += i;
            long cnt = cntMap[edges[i]];
            if (max < cnt || (max == cnt && maxNo > i)) {
                max = cnt;
                maxNo = i;
            }
        }
        return (int) maxNo;
    }
}
