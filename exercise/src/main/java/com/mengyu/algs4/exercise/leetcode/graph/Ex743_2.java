package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex743_2 {

    public static void main(String[] args) {
        int[][] times = {
                {2,1,1},{2,3,1},{3,4,1}
        };
        System.out.println(new Ex743_2().networkDelayTime(times, 4, 2));
    }

    private LinkedList<int[]>[] adj;

    private PriorityQueue<int[]> que;

    private int[] distTo;

    public int networkDelayTime(int[][] times, int n, int k) {
        adj(times, n);
        que = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        distTo = new int[n + 1];
        Arrays.fill(distTo, Integer.MAX_VALUE);

        distTo[k] = 0;
        que.offer(new int[]{k, 0});
        while (!que.isEmpty()) {
            int[] edge = que.poll();
            relax(edge[0]);
        }
        distTo[0] = 0;
        int weight = Arrays.stream(distTo).max().getAsInt();
        return weight == Integer.MAX_VALUE ? -1 : weight;
    }

    private void adj(int[][] times, int n) {
        adj = new LinkedList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] time : times) {
            int from = time[0], to = time[1], weight = time[2];
            adj[from].offer(new int[]{to, weight});
        }
    }

    private void relax(int vertex) {
        for (int[] edge : adj[vertex]) {
            int to = edge[0];
            if (distTo[to] > distTo[vertex] + edge[1]) {
                distTo[to] = distTo[vertex] + edge[1];
                if (que.contains(edge)) {
                    que.remove(edge);

                }
                que.offer(new int[]{to, distTo[to]});
            }
        }
    }

    private static class Point{
        private int vertex;

        private int weight;
    }
}
