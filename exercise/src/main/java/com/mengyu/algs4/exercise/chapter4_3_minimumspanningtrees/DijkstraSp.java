package com.mengyu.algs4.exercise.chapter4_3_minimumspanningtrees;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import java.util.Arrays;

/**
 * @author yu zhang
 */
public class DijkstraSp {
    private IndexMinPQ<Double> que;

    private DirectedEdge[] edgeTo;

    private double[] distTo;

    public DijkstraSp(EdgeWeightedDigraph digraph, int startVertex) {
        que = new IndexMinPQ<>(digraph.V());
        edgeTo = new DirectedEdge[digraph.V()];
        distTo = new double[digraph.V()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);

        distTo[startVertex] = 0.0;
        que.insert(startVertex, 0.0);
        while (!que.isEmpty()) {
            relax(digraph, que.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int vertex) {
        for (DirectedEdge edge : digraph.adj(vertex)) {
            int adjVertex = edge.to();
            if (distTo[adjVertex] > distTo[vertex] + edge.weight()) {
                distTo[adjVertex] = distTo[vertex] + edge.weight();
                edgeTo[adjVertex] = edge;
                if (que.contains(adjVertex)) {
                    que.decreaseKey(adjVertex, distTo[adjVertex]);
                } else {
                    que.insert(adjVertex, distTo[adjVertex]);
                }
            }
        }
    }
}
