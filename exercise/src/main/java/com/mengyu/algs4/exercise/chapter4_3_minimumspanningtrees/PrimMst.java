package com.mengyu.algs4.exercise.chapter4_3_minimumspanningtrees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import java.util.Arrays;

/**
 * @author yu zhang
 */
public class PrimMst {

    private boolean[] marked;

    private double[] distTo;

    private Edge[] edgeTo;

    private IndexMinPQ<Double> que;

    public PrimMst(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];
        distTo = new double[graph.V()];
        edgeTo = new Edge[graph.V()];
        que = new IndexMinPQ<>(graph.V());
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[0] = 0.0;
        que.insert(0, 0.0);
        while (!que.isEmpty()) {
            visit(graph, que.delMin());
        }
    }

    private void visit(EdgeWeightedGraph graph, int vertex) {
        marked[vertex] = true;
        for (Edge edge : graph.adj(vertex)) {
            int adjVertex = edge.other(vertex);
            if (marked[adjVertex]) {
                continue;
            }
            if (distTo[adjVertex] > edge.weight()) {
                distTo[adjVertex] = edge.weight();
                edgeTo[adjVertex] = edge;
                if (que.contains(adjVertex)) {
                    que.decreaseKey(adjVertex, edge.weight());
                } else {
                    que.insert(adjVertex, edge.weight());
                }
            }
        }
    }
}
