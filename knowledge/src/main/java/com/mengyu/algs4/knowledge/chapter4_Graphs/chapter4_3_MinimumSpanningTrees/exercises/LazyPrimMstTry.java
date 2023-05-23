package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/3/2 上午9:48
 * TODO
 */
public class LazyPrimMstTry {
    private boolean[] marked;
    private Queue<Edge> mst;
    private PriorityQueue<Edge> priorityQueue;

    public LazyPrimMstTry(EdgeWeightedGraph weightedGraph) {
        marked = new boolean[weightedGraph.getVertexNum()];
        mst = new LinkedList<>();
        priorityQueue = new PriorityQueue<>();
        visit(weightedGraph, 0);
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int vertex1 = edge.either();
            int vertex2 = edge.other(vertex1);
            if (marked[vertex1] && marked[vertex2]) {
                continue;
            }
            mst.offer(edge);
            if (!marked[vertex1]) {
                visit(weightedGraph, vertex1);
            }
            if (!marked[vertex2]) {
                visit(weightedGraph, vertex2);
            }
        }
    }

    private void visit(EdgeWeightedGraph weightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge edge : weightedGraph.adj(vertex)) {
            int adjVertex = edge.other(vertex);
            if (!marked[adjVertex]) {
                priorityQueue.offer(edge);
            }
        }
    }
}
