package com.mengyu.algs4.exercise.chapter4_3_minimumspanningtrees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class LazyPrimMst {
    private boolean[] marked;

    private PriorityQueue<Edge> que;

    private Queue<Edge> mst;

    public LazyPrimMst(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];
        que = new PriorityQueue<>();
        mst = new LinkedList<>();
        visit(graph, 0);
        while (!que.isEmpty()) {
            Edge edge = que.poll();
            int vertex1 = edge.either();
            int vertex2 = edge.other(vertex1);
            if (marked[vertex1] && marked[vertex2]) {
                continue;
            }
            if (!marked[vertex1]) {
                visit(graph, vertex1);
            }
            if (!marked[vertex2]) {
                visit(graph, vertex2);
            }
        }
    }

    private void visit(EdgeWeightedGraph graph, int vertex) {
        for (Edge edge : graph.adj(vertex)) {
            if (!marked[edge.other(vertex)]) {
                que.offer(edge);
            }
        }
    }
}
