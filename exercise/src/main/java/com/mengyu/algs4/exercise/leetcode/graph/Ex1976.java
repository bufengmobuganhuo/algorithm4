package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1976 {

    public static void main(String[] args) {
        System.out.println(new Ex1976().countPaths(7, new int[][]{
                {0,1,1},{1,2,4},{0,4,3},{3,2,5},{3,4,1},{3,0,5},{1,3,1}
        }));
    }

    private long[] distTo;

    private long[] cnts;

    private PriorityQueue<long[]> que;

    public int countPaths(int n, int[][] roads) {
        LinkedList<int[]>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] road : roads) {
            adj[road[0]].add(new int[]{road[1], road[2]});
            adj[road[1]].add(new int[]{road[0], road[2]});
        }
        que = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));
        distTo = new long[n];
        cnts = new long[n];
        Arrays.fill(distTo, Long.MAX_VALUE);
        distTo[0] = 0;
        cnts[0] = 1;
        que.offer(new long[]{0, 0});
        while (!que.isEmpty()) {
            long[] edge = que.poll();
            if (distTo[(int) edge[1]] < edge[0]) {
                continue;
            }
            relax(adj, (int) edge[1]);
        }
        return (int) cnts[n - 1];
    }

    private void relax(LinkedList<int[]>[] adj, int vertex) {
        for (int[] edge : adj[vertex]) {
            int adjVertex = edge[0], weight = edge[1];
            if (distTo[adjVertex] > distTo[vertex] + weight) {
                distTo[adjVertex] = distTo[vertex] + weight;
                que.offer(new long[]{distTo[adjVertex], adjVertex});
                cnts[adjVertex] = cnts[vertex];
            } else if (distTo[adjVertex] == distTo[vertex] + weight) {
                cnts[adjVertex] = (cnts[vertex] + cnts[adjVertex]) % 1_000_000_007;
            }
        }
    }
}
