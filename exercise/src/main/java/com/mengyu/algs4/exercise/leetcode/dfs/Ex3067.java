package com.mengyu.algs4.exercise.leetcode.dfs;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex3067 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Ex3067().countPairsOfConnectableServers(new int[][]{{1,0,1},{2,1,1},{3,2,4},{4,0,3},{5,4,1},{6,5,3}}
                , 2)));
    }

    private boolean[] marked;

    private double[] cnt;

    private int total;

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        LinkedList<int[]>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int either = edge[0], other = edge[1], weight = edge[2];
            adj[either].add(new int[]{other, weight});
            adj[other].add(new int[]{either, weight});
        }
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            marked = new boolean[n];
            cnt = new double[n];
            total = 0;
            dfs(adj, i, i, 0, signalSpeed);
            double c = 0;
            for (int[] adjVertex : adj[i]) {
                int vertex = adjVertex[0];
                c += ((total - cnt[vertex]) * cnt[vertex]) / 2;
            }
            count[i] = (int) c;
        }
        return count;
    }

    private int dfs(LinkedList<int[]>[] adj, int startVertex, int root, int weightTrack, int signalSpeed) {
        marked[startVertex] = true;
        int sum = weightTrack % signalSpeed == 0 ? 1 : 0;
        for (int[] adjVertex : adj[startVertex]) {
            int vertex = adjVertex[0], weight = adjVertex[1];
            int count = 0;
            if (!marked[vertex]) {
                count += dfs(adj, vertex, root, weightTrack + weight, signalSpeed);
            }
            sum += count;
        }
        if (startVertex == root) {
            total += sum - 1;
        }
        cnt[startVertex] = sum;
        return sum;
    }
}
