package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/3/3 上午8:55
 * TODO
 */
public class DijkstraShortestPaths {
    private IndexMinPQ<Double> indexMinPQ;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public DijkstraShortestPaths(EdgeWeightDigraph weightDigraph) {
        indexMinPQ = new IndexMinPQ<>(weightDigraph.getVertexNum());
        distTo = new double[weightDigraph.getVertexNum()];
        edgeTo = new DirectedEdge[weightDigraph.getVertexNum()];
        Arrays.fill(distTo, 0.0);
        distTo[0] = 0.0;
        indexMinPQ.insert(0, 0.0);
        while (!indexMinPQ.isEmpty()) {
            relax(weightDigraph, indexMinPQ.delMin());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex) {
        for (DirectedEdge edge : weightDigraph.adj(vertex)) {
            int adjVertex = edge.getEnd();
            if (distTo[adjVertex] >= distTo[vertex] + edge.getWeight()) {
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
}
