package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex1519 {
    public static void main(String[] args) {
        int[][] edges = {{0,2},{0,3},{1,2}};
        System.out.println(Arrays.toString(new Ex1519().countSubTrees(4, edges, "aeed")));
    }
    private int[] ans;

    private boolean[] marked;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        this.ans = new int[n];
        this.marked = new boolean[n];
        LinkedList<Integer>[] adj = new LinkedList[n];
        for (int[] edge : edges) {
            int start = edge[0], other = edge[1];
            if (adj[start] == null) {
                adj[start] = new LinkedList<>();
            }
            adj[start].offerLast(other);
            if (adj[other] == null) {
                adj[other] = new LinkedList<>();
            }
            adj[other].offerLast(start);
        }
        dfs(adj, labels, 0);
        return ans;
    }

    private int[] dfs(LinkedList<Integer>[] adj, String labels, int idx) {
        int[] count = new int[26];
        marked[idx] = true;
        if (adj[idx] == null) {
            count[labels.charAt(idx) - 'a']++;
            ans[idx] = 1;
            return count;
        }
        for (int adjVertex : adj[idx]) {
            if (marked[adjVertex]) {
                continue;
            }
            int[] countInSub = dfs(adj, labels, adjVertex);
            for (int i = 0; i < 26; i++) {
                count[i] += countInSub[i];
            }
        }
        ans[idx] = (++count[labels.charAt(idx) - 'a']);
        return count;
    }
}
