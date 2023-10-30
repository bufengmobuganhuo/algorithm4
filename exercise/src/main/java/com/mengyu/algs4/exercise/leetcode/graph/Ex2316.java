package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex2316 {

    public static void main(String[] args) {

        System.out.println(new Ex2316().countPairs2(3, new int[][]{
                {0, 1}, {0, 2}, {1, 2}
        }));
    }

    private int[] roots;

    private int[] weights;

    public long countPairs2(int n, int[][] edges) {
        roots = new int[n];
        weights = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
            weights[i] = 1;
        }
        for (int[] edge : edges) {
            connect(edge[0], edge[1]);
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += (n - weights[find(i)]);
        }
        return res / 2;
    }

    private void connect(int point1, int point2) {
        int root1 = find(point1), root2 = find(point2);
        if (root1 == root2) {
            return;
        }
        if (weights[root1] > weights[root2]) {
            weights[root1] += weights[root2];
            roots[root2] = root1;
        } else {
            weights[root2] += weights[root1];
            roots[root1] = root2;
        }
    }

    private int find(int point) {
        while (point != roots[point]) {
            roots[point] = roots[roots[point]];
            point = roots[point];
        }
        return point;
    }

    private boolean[] marked;


    public long countPairs(int n, int[][] edges) {
        LinkedList<Integer>[] adj = adj(n, edges);

        marked = new boolean[n];
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (!marked[i]) {
                int cnt = dfs(i, adj);
                res += ((long) cnt * (n - cnt));
            }
        }
        return res / 2;
    }

    private int dfs(int vertex, LinkedList<Integer>[] adj) {
        marked[vertex] = true;
        int cnt = 1;
        for (int adjVertex : adj[vertex]) {
            if (!marked[adjVertex]) {
                cnt += dfs(adjVertex, adj);
            }
        }
        return cnt;
    }

    private LinkedList<Integer>[] adj(int n, int[][] edges) {
        LinkedList<Integer>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return adj;
    }
}
