package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/3/2 上午9:55
 * TODO
 */
public class PrimMstTry {
    private double[] distTo;
    private Edge[] edgeTo;
    private IndexMinPQ<Double> indexMinPQ;
    private boolean[] marked;

    public PrimMstTry(EdgeWeightedGraph weightedGraph) {
        distTo = new double[weightedGraph.getVertexNum()];
        edgeTo = new Edge[weightedGraph.getVertexNum()];
        indexMinPQ = new IndexMinPQ<>(weightedGraph.getVertexNum());
        marked = new boolean[weightedGraph.getVertexNum()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        indexMinPQ.insert(0, 0.0);
        distTo[0] = 0.0;
        while (!indexMinPQ.isEmpty()) {
            visit(weightedGraph, indexMinPQ.delMin());
        }
    }

    private void visit(EdgeWeightedGraph weightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge edge : weightedGraph.adj(vertex)) {
            int adjVertex = edge.other(vertex);
            if (marked[adjVertex]) {
                continue;
            }
            if (distTo[adjVertex] > edge.getWeight()) {
                distTo[adjVertex] = edge.getWeight();
                edgeTo[adjVertex] = edge;
                if (indexMinPQ.contains(adjVertex)) {
                    indexMinPQ.changeKey(adjVertex, edge.getWeight());
                } else {
                    indexMinPQ.insert(adjVertex, edge.getWeight());
                }
            }
        }
    }

    public Queue<Edge> mst() {
        Queue<Edge> mst = new LinkedList<>();
        for (int i = 1; i < edgeTo.length; i++) {
            mst.offer(edgeTo[i]);
        }
        return mst;
    }
}
