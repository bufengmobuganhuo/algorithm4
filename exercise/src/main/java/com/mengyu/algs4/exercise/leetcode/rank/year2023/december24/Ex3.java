package com.mengyu.algs4.exercise.leetcode.rank.year2023.december24;

import java.util.*;

/**
 * @date 2023/12/24 10:19
 * TODO
 */
public class Ex3 {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        LinkedList<int[]>[] adj = new LinkedList[26];
        for (int i = 0; i < 26; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int i = 0; i < original.length; i++) {
            int start = original[i] - 'a', end = changed[i] - 'a', weight = cost[i];
            adj[start].add(new int[]{end, weight});
        }
        int[][] distTo = shortestPath(adj);
        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            int s = source.charAt(i) - 'a', t = target.charAt(i) - 'a';
            if (s != t) {
                if (distTo[s][t] == Integer.MAX_VALUE) {
                    return -1;
                }
                ans += distTo[s][t];
            }
        }
        return ans;
    }

    private int[][] shortestPath(LinkedList<int[]>[] adj) {
        int[][] result = new int[26][26];
        for (int i = 0; i < 26; i++) {
            result[i] = cal(i, adj);
        }
        return result;
    }

    private int[] cal(int start, LinkedList<int[]>[] adj) {
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] distTo = new int[26];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
        que.offer(new int[]{start, 0});
        while (!que.isEmpty()) {
            int[] edges = que.poll();
            relax(edges[0], adj, distTo, que);
        }
        return distTo;
    }

    private void relax(int vertex, LinkedList<int[]>[] adj, int[] distTo, PriorityQueue<int[]> que) {
        for (int[] edges : adj[vertex]) {
            int end = edges[0], weight = edges[1];
            if (distTo[end] > distTo[vertex] + weight) {
                distTo[end] = distTo[vertex] + weight;
                que.offer(new int[]{end, distTo[end]});
            }
        }
    }
}
