package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex1466 {
    public int minReorder(int n, int[][] connections) {
        LinkedList<int[]>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] connection : connections) {
            adj[connection[0]].add(new int[]{connection[1], 1});
            adj[connection[1]].add(new int[]{connection[0], 0});
        }
        return dfs(0, -1, adj);
    }

    private int dfs(int cur, int pre, LinkedList<int[]>[] adj) {
        int res = 0;
        for (int[] adjV : adj[cur]) {
            int v = adjV[0];
            if (v == pre) {
                continue;
            }
            res += adjV[1] + dfs(v, cur, adj);
        }
        return res;
    }
}
