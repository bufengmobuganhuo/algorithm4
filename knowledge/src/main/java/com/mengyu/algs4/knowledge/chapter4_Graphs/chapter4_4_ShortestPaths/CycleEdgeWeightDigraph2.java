package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/23 上午10:36
 * TODO
 */
public class CycleEdgeWeightDigraph2 {
    private boolean[] onStack;
    private boolean[] marked;
    private Stack<DirectedEdge> cycle;
    private DirectedEdge[] edgeTo;

    public CycleEdgeWeightDigraph2(EdgeWeightDigraph digraph) {
        onStack = new boolean[digraph.getVertexNum()];
        marked = new boolean[digraph.getVertexNum()];
        edgeTo = new DirectedEdge[digraph.getVertexNum()];
    }

    private void dfs(EdgeWeightDigraph digraph, int vertex) {
        marked[vertex] = true;
        onStack[vertex] = true;
        for (DirectedEdge edge : digraph.adj(vertex)) {
            int adjVertex = edge.getEnd();
            if (hasCycle()) {
                return;
            } else if (!marked[adjVertex]) {
                edgeTo[adjVertex] = edge;
                dfs(digraph, adjVertex);
            } else if (onStack[adjVertex]) {
                cycle = new Stack<>();
                for (DirectedEdge tmp = edge; tmp.getStart() != adjVertex; tmp = edgeTo[tmp.getStart()]) {
                    cycle.push(tmp);
                }
                cycle.push(edgeTo[adjVertex]);
            }
        }
        onStack[vertex] = false;
    }

    private boolean hasCycle() {
        return cycle != null;
    }

    public Stack<DirectedEdge> cycle(){
        return cycle;
    }
}
