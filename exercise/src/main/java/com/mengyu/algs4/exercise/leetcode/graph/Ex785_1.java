package com.mengyu.algs4.exercise.leetcode.graph;

/**
 * @author yuzhang
 * @date 2021/3/2 上午10:59
 * TODO
 */
public class Ex785_1 {
    private boolean[] color;
    private boolean[] marked;
    private boolean flag;

    public boolean isBipartite(int[][] graph) {
        color = new boolean[graph.length];
        marked = new boolean[graph.length];
        flag = true;
        for (int i = 0; i < graph.length; i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
        return flag;
    }

    private void dfs(int[][] graph, int vertex) {
        if (!flag) {
            return;
        }
        marked[vertex] = true;
        for (int adjVertex : graph[vertex]) {
            if (!marked[adjVertex]) {
                color[adjVertex] = !color[vertex];
                dfs(graph, adjVertex);
            } else if (color[adjVertex] == color[vertex]) {
                flag = false;
                return;
            }
        }
    }
}
