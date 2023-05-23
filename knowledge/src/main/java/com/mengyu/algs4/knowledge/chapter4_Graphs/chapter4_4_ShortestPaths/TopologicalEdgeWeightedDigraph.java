package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/14 17:02
 * 无环加权有向图的拓扑排序
 */
public class TopologicalEdgeWeightedDigraph {
    private Stack<Integer> reversePostStack;
    private boolean[] marked;

    public TopologicalEdgeWeightedDigraph(EdgeWeightDigraph weightDigraph) {
        CycleEdgeWeightDigraph cycleEdgeWeightDigraph = new CycleEdgeWeightDigraph(weightDigraph);
        if (cycleEdgeWeightDigraph.hasCycle()) {
            return;
        }
        EdgeWeightedDigraphOrder edgeWeightedDigraphOrder = new EdgeWeightedDigraphOrder(weightDigraph);
        reversePostStack = edgeWeightedDigraphOrder.getReversePostOrder();
    }

    public Stack<Integer> order() {
        return reversePostStack;
    }
}
