package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1462 {

    public static void main(String[] args) {
        int[][] prerequisites = {{0,1},{1,2},{2,3},{3,4}};
        int[][] queries = {{0,4},{4,0},{1,3},{3,0}};
        System.out.println(new Ex1462().checkIfPrerequisite(5, prerequisites, queries).toString());
    }

    private boolean[] marked;

    private boolean[][] isPre;

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        LinkedList<Integer>[] adj = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] prerequisity : prerequisites) {
            int start = prerequisity[0];
            int end = prerequisity[1];
            adj[start].add(end);
        }
        marked = new boolean[numCourses];
        isPre = new boolean[numCourses][numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!marked[i]) {
                dfs(adj, i);
            }
        }
        List<Boolean> result = new LinkedList<>();
        for (int[] query : queries) {
            result.add(isPre[query[0]][query[1]]);
        }
        return result;
    }

    private void dfs(LinkedList<Integer>[] adj, int start) {
        marked[start] = true;
        for (int adjVertex : adj[start]) {
            isPre[start][adjVertex] = true;
            if (!marked[adjVertex]) {
                dfs(adj, adjVertex);
            }
            for (int i = 0; i < isPre.length; ++i) {
                isPre[start][i] = isPre[start][i] | isPre[adjVertex][i];
            }
        }
    }
}
