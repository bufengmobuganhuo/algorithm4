package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/23 上午8:57
 * TODO
 */
public class DijkstraSP2 {
    private IndexMinPQ<Double> indexMinPQ;
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private final int start;

    public DijkstraSP2(EdgeWeightDigraph digraph, int start) {
        indexMinPQ = new IndexMinPQ<>(digraph.getVertexNum());
        distTo = new double[digraph.getVertexNum()];
        edgeTo = new DirectedEdge[digraph.getVertexNum()];
        this.start = start;
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[start] = 0.0;
        indexMinPQ.insert(start, 0.0);
        while (!indexMinPQ.isEmpty()) {
            relax(digraph, indexMinPQ.delMin());
        }
    }

    private void relax(EdgeWeightDigraph digraph, int vertex) {
        for (DirectedEdge edge : digraph.adj(vertex)) {
            int adjVertex = edge.getEnd();
            if (distTo[adjVertex] > distTo[vertex] + edge.getWeight()) {
                distTo[adjVertex] = distTo[vertex] + edge.getWeight();
                edgeTo[adjVertex] = edge;
                if (indexMinPQ.contains(adjVertex)) {
                    indexMinPQ.changeKey(adjVertex, distTo[adjVertex]);
                } else {
                    indexMinPQ.insert(adjVertex, distTo[adjVertex]);
                }
            }
        }
    }

    public boolean hasPathTo(int target) {
        return distTo[target] != Double.POSITIVE_INFINITY;
    }

    public Stack<DirectedEdge> pathTo(int target) {
        Stack<DirectedEdge> path = new Stack<>();
        if (!hasPathTo(target)) {
            return path;
        }
        for (DirectedEdge tmp = edgeTo[target]; tmp != null; tmp = edgeTo[tmp.getStart()]) {
            path.push(tmp);
        }
        return path;
    }
}
