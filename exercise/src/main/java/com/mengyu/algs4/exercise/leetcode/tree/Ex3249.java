package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex3249 {

    public static void main(String[] args) {
        int[][] edges = {{5, 0}, {4, 5}, {3, 4}, {1, 4}, {2, 5}, {6, 5}};
        System.out.println(new Ex3249().countGoodNodes(edges));
    }

    private int cnt;

    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        LinkedList<Integer>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        cnt = 0;
        dfs(adj, 0, -1);
        return cnt;
    }

    private int dfs(LinkedList<Integer>[] adj, int vertex, int parent) {
        int children = -1;
        boolean isGoodVertex = true;
        int sum = 0;
        for (int child : adj[vertex]) {
            if (child == parent) {
                continue;
            }
            int cnt = dfs(adj, child, vertex);
            sum += cnt;
            if (children == -1) {
                children = cnt;
            } else if (cnt != children) {
                isGoodVertex = false;
            }
        }
        if (isGoodVertex) {
            cnt++;
        }
        return sum + 1;
    }
}
