package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class DijkstraSP3 {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {

        LinkedList<int[]>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
            adj[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        int[] distTo = new int[n];
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Arrays.fill(distTo, -1);
        distTo[0] = 0;
        que.offer(new int[]{0, 0});
        while (!que.isEmpty()) {
            int[] edge = que.poll();
            int weight = edge[0], vertex = edge[1];
            // 因为放松时没有清理已经存在的节点，所以有些节点可能会重复。但是distTo中的值是最新的，所以这里只保留和distTo相同的放松项
            if (weight != distTo[vertex]) {
                continue;
            }
            for (int[] next : adj[vertex]) {
                int dist = weight + next[1];
                int adjVertex = next[0];
                if ((distTo[adjVertex] == -1 || dist < distTo[adjVertex]) && dist < disappear[adjVertex]) {
                    distTo[adjVertex] = dist;
                    que.offer(new int[]{dist, adjVertex});
                }
            }
        }
        return distTo;
    }
}
