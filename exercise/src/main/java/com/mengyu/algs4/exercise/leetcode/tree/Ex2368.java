package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2368 {

    private boolean[] marked;

    private LinkedList<Integer>[] adj;

    private int cnt;

    private Set<Integer> restrictedNodes;

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        marked = new boolean[n];
        adj = new LinkedList[n];
        cnt = 0;
        this.restrictedNodes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int ths = edge[0], that = edge[1];
            adj[ths].offerLast(that);
            adj[that].offerLast(ths);
        }
        for (int vertex : restricted) {
            this.restrictedNodes.add(vertex);
        }
        dfs(0);
        return cnt;
    }

    private void dfs(int vertex) {
        marked[vertex] = true;
        cnt++;
        for (int adjVertex : adj[vertex]) {
            if (!marked[adjVertex] && !restrictedNodes.contains(adjVertex)) {
                dfs(adjVertex);
            }
        }
    }
}
