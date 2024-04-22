package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2192 {

    public static void main(String[] args) {
        int[][] edges = {{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        System.out.println(new Ex2192().getAncestors(8, edges));
    }

    private boolean[] marked;

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        LinkedList<Integer>[] reversedAdj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            reversedAdj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int start = edge[0], end = edge[1];
            reversedAdj[end].offerLast(start);
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            marked = new boolean[n];
            List<Integer> parents = new ArrayList<>();
            dfs(reversedAdj, i, parents);
            Collections.sort(parents);
            list.add(parents);
        }
        return list;
    }

    private void dfs(LinkedList<Integer>[] reversedAdj, int vertex, List<Integer> parents) {
        marked[vertex] = true;
        for (int adjVertex : reversedAdj[vertex]) {
            if (!marked[adjVertex]) {
                parents.add(adjVertex);
                dfs(reversedAdj, adjVertex, parents);
            }
        }
    }
}
