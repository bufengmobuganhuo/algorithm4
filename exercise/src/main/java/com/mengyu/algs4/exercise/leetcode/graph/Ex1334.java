package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1334 {

    public static void main(String[] args) {
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        System.out.println(new Ex1334().findTheCity(4, edges, 4));
    }

    private int[][] distTo;

    private PriorityQueue<int[]> que;

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        LinkedList<int[]>[] adj = adj(edges, n);
        distTo = new int[n][n];
        for (int i = 0; i < n; i++) {
            que = new PriorityQueue<>((o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return 0;
                }
                return o1[1] - o2[1];
            });
            Arrays.fill(distTo[i], Integer.MAX_VALUE);
            distTo[i][i] = 0;
            que.offer(new int[]{i, 0});
            while (!que.isEmpty()) {
                int[] edge = que.poll();
                relax(adj, edge[0], i);
            }
        }
        int[] ans = new int[]{Integer.MAX_VALUE, -1};
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (distTo[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= ans[0]) {
                ans[0] = cnt;
                ans[1] = i;
            }
        }
        return ans[1];
    }

    private void relax(LinkedList<int[]>[] adj, int vertex, int startVertex) {
        for (int[] edge : adj[vertex]) {
            int adjVertex = edge[0], weight = edge[1];
            if (distTo[startVertex][adjVertex] > distTo[startVertex][vertex] + weight) {
                distTo[startVertex][adjVertex] = distTo[startVertex][vertex] + weight;
                que.remove(new int[]{adjVertex, distTo[startVertex][adjVertex]});
                que.offer(new int[]{adjVertex, distTo[startVertex][adjVertex]});
            }
        }
    }

    private LinkedList<int[]>[] adj(int[][] edges, int n) {
        LinkedList<int[]>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            adj[from].add(new int[]{to, weight});
            adj[to].add(new int[]{from, weight});
        }
        return adj;
    }
}
