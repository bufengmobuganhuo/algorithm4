package com.mengyu.algs4.exercise.chapter4_1_undirectedgraphs;

import edu.princeton.cs.algs4.Graph;

/**
 * @author yu zhang
 */
public class TwoColor {
    private boolean[] marked;

    private boolean[] color;

    private boolean isTwoColorable;

    public TwoColor(Graph digraph) {
        marked = new boolean[digraph.V()];
        color = new boolean[digraph.V()];
        isTwoColorable = true;
        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Graph digraph, int vertex) {
        marked[vertex] = true;
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[adjVertex]) {
                color[adjVertex] = !marked[vertex];
                dfs(digraph, adjVertex);
            } else if (color[adjVertex] == color[vertex]) {
                isTwoColorable = false;
            }
        }
    }
}
