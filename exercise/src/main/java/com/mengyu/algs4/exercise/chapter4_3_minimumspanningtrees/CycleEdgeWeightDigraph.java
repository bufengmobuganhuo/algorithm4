package com.mengyu.algs4.exercise.chapter4_3_minimumspanningtrees;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class CycleEdgeWeightDigraph {
    private boolean[] onStack;

    private boolean[] marked;

    private DirectedEdge[] edgeTo;

    private Stack<DirectedEdge> cycle;

    public CycleEdgeWeightDigraph(EdgeWeightedDigraph digraph) {
        onStack = new boolean[digraph.V()];
        marked = new boolean[digraph.V()];
        edgeTo = new DirectedEdge[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph digraph, int startVertex) {
        marked[startVertex] = true;
        for (DirectedEdge adjEdge : digraph.adj(startVertex)) {
            int endVertex = adjEdge.to();
            if (cycle != null) {
                return;
            } else if (!marked[endVertex]) {
                edgeTo[endVertex] = adjEdge;
                dfs(digraph, endVertex);
            } else if (onStack[endVertex]) {
                cycle = new Stack<>();
                DirectedEdge edge = adjEdge;
                while (edge.from() != startVertex) {
                    cycle.push(edge);
                    edge = edgeTo[endVertex];
                }
                cycle.push(adjEdge);
            }
        }
        onStack[startVertex] = false;
    }

    public Stack<DirectedEdge> getCycle() {
        return cycle;
    }
}
