package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex310_3 {

    public static void main(String[] args) {
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        System.out.println(new Ex310_3().findMinHeightTrees(1 ,new int[][]{}));
    }

    private LinkedList<Integer>[] adj;

    private int[] degree;

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        buildGraph(n, edges);
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            res = new ArrayList<>();
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int vertex = que.poll();
                res.add(vertex);
                for (int adjVertex : adj[vertex]) {
                    degree[adjVertex]--;
                    if (degree[adjVertex] == 1) {
                        que.offer(adjVertex);
                    }
                }
            }
        }
        return res;
    }

    private void buildGraph(int n, int[][] edges) {
        adj = new LinkedList[n];
        degree = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
    }
}
