package com.mengyu.algs4.exercise.chapter4_1_undirectedgraphs;

import edu.princeton.cs.algs4.Graph;

/**
 * @author yu zhang
 */
public class ConnectedComponent {
    private boolean[] marked;

    private int[] componentIds;

    private int componentId;

    public ConnectedComponent(Graph digraph) {
        marked = new boolean[digraph.V()];
        componentIds = new int[digraph.V()];
        componentId = 0;
        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
                componentId++;
            }
        }
    }

    private void dfs(Graph digraph, int vertex) {
        marked[vertex] = true;
        componentIds[vertex] = componentId;
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[adjVertex]) {
                dfs(digraph, adjVertex);
            }
        }
    }
}
