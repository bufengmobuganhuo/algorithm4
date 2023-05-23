package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/8/7 10:42 上午
 * 即时Prim算法
 */
public class PrimMst {
    private boolean[] marked;
    private IndexMinPQ<Double> indexMinPQ;
    private double[] distTo;
    private Edge[] edgeTo;
    private double weight;

    public PrimMst(EdgeWeightedGraph weightedGraph) {
        marked = new boolean[weightedGraph.getVertexNum()];
        indexMinPQ = new IndexMinPQ<>(weightedGraph.getVertexNum());
        distTo = new double[weightedGraph.getVertexNum()];
        edgeTo = new Edge[weightedGraph.getVertexNum()];
        Arrays.fill(distTo, Double.MAX_VALUE);
        indexMinPQ.insert(0, 0.0);
        distTo[0] = 0.0;
        while (!indexMinPQ.isEmpty()) {
            visit(weightedGraph, indexMinPQ.delMin());
        }
    }

    private void visit(EdgeWeightedGraph weightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge adjEdge : weightedGraph.adj(vertex)) {
            int otherVertex = adjEdge.other(vertex);
            if (marked[otherVertex]) {
                continue;
            }
            if (adjEdge.getWeight() < edgeTo[otherVertex].getWeight()) {
                weight -= distTo[otherVertex] == Double.MAX_VALUE ? 0 : distTo[otherVertex];
                edgeTo[otherVertex] = adjEdge;
                distTo[otherVertex] = adjEdge.getWeight();
                weight += distTo[otherVertex];
                if (indexMinPQ.contains(otherVertex)) {
                    indexMinPQ.decreaseKey(otherVertex, adjEdge.getWeight());
                } else {
                    indexMinPQ.insert(otherVertex, adjEdge.getWeight());
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        LinkedList<Edge> edges = new LinkedList<>();
        for (int i = 1; i < edgeTo.length; i++) {
            edges.offer(edgeTo[i]);
        }
        return edges;
    }
}
