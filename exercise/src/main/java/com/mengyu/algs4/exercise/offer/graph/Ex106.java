package com.mengyu.algs4.exercise.offer.graph;

/**
 * @author yu zhang
 */
public class Ex106 {
    public static void main(String[] args) {
        int[][] graph =
                {{3,4,6},{3,6},{3,6},{0,1,2,5},{0,7,8},{3},{0,1,2,7},{4,6},{4},{}};
        System.out.println(new Ex106().isBipartite(graph));
    }
    private int[] color;

    public boolean isBipartite(int[][] graph) {
        color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                if (!dfs(graph, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int vertex) {
        boolean flag = true;
        for (int adjVertex: graph[vertex]) {
            if (color[adjVertex] == 0) {
                color[adjVertex] = color[vertex] == 1 ? 2 : 1;
                flag = flag && dfs(graph, adjVertex);
            } else if (color[adjVertex] == color[vertex]) {
                flag = false;
            }
        }
        return flag;
    }
}
