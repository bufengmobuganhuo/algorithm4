package com.mengyu.algs4.exercise.leetcode.graph;

/**
 * @author yu zhang
 */
public class Ex2924 {
    public int findChampion(int n, int[][] edges) {
        int[] degree = new int[n];
        for (int[] edge : edges) {
            degree[edge[1]]++;
        }
        int champoin = -1;
        for (int i = 0; i < n; i++) {
            if (degree[i] != 0) {
                continue;
            }
            if (champoin == -1) {
                champoin = i;
            } else {
                return -1;
            }
        }
        return champoin;
    }
}
