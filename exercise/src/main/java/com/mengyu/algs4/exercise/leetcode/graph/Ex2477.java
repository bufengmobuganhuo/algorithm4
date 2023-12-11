package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex2477 {

    private long res = 0;

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length;
        res = 0;
        LinkedList<Integer>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] road : roads) {
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }
        dfs(0, -1, seats, adj);
        return res;
    }

    private int dfs(int cur, int father, int seats, LinkedList<Integer>[] adj) {
        int peopleSum = 1;
        for (int next : adj[cur]) {
            if (next != father) {
                int peopleCnt = dfs(next, cur, seats, adj);
                peopleSum += peopleCnt;
                res += (peopleCnt + seats - 1) / seats;
            }
        }
        return peopleSum;
    }
}
