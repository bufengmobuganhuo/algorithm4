package com.mengyu.algs4.exercise.chapter4_3_minimumspanningtrees;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class BellmanFordShortestPath {
    private boolean[] onQue;

    private Queue<Integer> que;

    private double[] distTo;

    private DirectedEdge[] edgeTo;

    private int relaxCnt;

    private Stack<DirectedEdge> cycle;

    public BellmanFordShortestPath(EdgeWeightedDigraph digraph, int startVertex) {
        onQue = new boolean[digraph.V()];
        que = new LinkedList<>();
        distTo = new double[digraph.V()];
        edgeTo = new DirectedEdge[digraph.V()];
        relaxCnt = 0;
        Arrays.fill(distTo, Double.MAX_VALUE);

        distTo[startVertex] = 0.0;
        que.offer(startVertex);
        while(!que.isEmpty() && cycle == null) {
            int vertex = que.poll();
            onQue[vertex] = false;
            relax(digraph, vertex);
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int startVertex) {
        for (DirectedEdge adjEdge : digraph.adj(startVertex)) {
            int endVertex = adjEdge.to();
            if (distTo[endVertex] > distTo[startVertex] + adjEdge.weight()) {
                distTo[endVertex] = distTo[startVertex] + adjEdge.weight();
                edgeTo[endVertex] = adjEdge;
                if (!onQue[endVertex]) {
                    onQue[endVertex] = true;
                    que.offer(endVertex);
                }
            }

            if (++relaxCnt % digraph.V() == 0) {
                findNegativeCycle(digraph);
                if (cycle != null) {
                    return;
                }
            }
        }
    }

    private void findNegativeCycle(EdgeWeightedDigraph digraph) {
        EdgeWeightedDigraph newDigraph = new EdgeWeightedDigraph(digraph.V());
        for (int i = 0; i < digraph.V(); i++) {
            if (edgeTo[i] != null) {
                newDigraph.addEdge(edgeTo[i]);
            }
        }
        CycleEdgeWeightDigraph cycleEdgeWeightDigraph = new CycleEdgeWeightDigraph(newDigraph);
        cycle = cycleEdgeWeightDigraph.getCycle();
    }
}
