package com.mengyu.algs4.exercise.chapter4_1_undirectedgraphs;

import edu.princeton.cs.algs4.Graph;

/**
 * @author yu zhang
 */
public class Cycle {
    private boolean[] marked;

    private boolean hasCycle;

    public Cycle(Graph digraph) {
        marked = new boolean[digraph.V()];
        hasCycle = false;
        for (int i = 0; i < digraph.V(); i++) {
            if (hasCycle) {
                return;
            }
            if (!marked[i]) {
                dfs(digraph, i, -1);
            }
        }
    }

    private void dfs(Graph digraph, int vertex, int lastVisitVertex) {
        marked[vertex] = true;
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[adjVertex]) {
                dfs(digraph, adjVertex, vertex);
            } else if (adjVertex != lastVisitVertex) {
                hasCycle = true;
                return;
            }
        }
    }
}
