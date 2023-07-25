package com.mengyu.algs4.exercise.chapter4_3_minimumspanningtrees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class KruskalMst {
    private Queue<Edge> mst;

    private int[] weights;

    private int[] roots;

    public KruskalMst(EdgeWeightedGraph graph) {
        mst = new LinkedList<>();
        PriorityQueue<Edge> que = new PriorityQueue<>();
        for (Edge edge : graph.edges()) {
            que.offer(edge);
        }
        while (!que.isEmpty() && mst.size() != graph.V() - 1) {
            Edge edge = que.poll();
            int vertex1 = edge.either();
            int vertex2 = edge.other(vertex1);
            if (isConnected(vertex1, vertex2)) {
                continue;
            }
            connect(vertex1, vertex2);
            mst.offer(edge);
        }
    }

    private boolean isConnected(int vertex1, int vertex2) {
        return find(vertex1) == find(vertex2);
    }

    private void connect(int vertex1, int vertex2) {
        int root1 = find(vertex1);
        int root2 = find(vertex2);
        if (root1 == root2) {
            return;
        }
        if (weights[root1] > weights[root2]) {
            weights[root1] += weights[root2];
            roots[root2] = root1;
        } else {
            weights[root2] += weights[root1];
            roots[root1] = root2;
        }
    }

    private int find(int vertex) {
        while (vertex != roots[vertex]) {
            roots[vertex] = roots[roots[vertex]];
            vertex = roots[vertex];
        }
        return vertex;
    }
}
