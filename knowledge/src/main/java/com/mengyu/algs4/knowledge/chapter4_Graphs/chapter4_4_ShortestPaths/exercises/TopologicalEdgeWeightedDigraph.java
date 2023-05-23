package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/10 10:01 上午
 * TODO
 */
public class TopologicalEdgeWeightedDigraph {
    private Stack<Integer> reverseOrder;
    private boolean[] marked;

    public TopologicalEdgeWeightedDigraph(EdgeWeightDigraph weightDigraph) {
        CycleEdgeWightedDigraph cycleEdgeWightedDigraph=new CycleEdgeWightedDigraph(weightDigraph);
        if (cycleEdgeWightedDigraph.hasCycle()){
            return;
        }
        reverseOrder=new Stack<>();
        marked=new boolean[weightDigraph.getVertexNum()];
        for (int i = 0; i < weightDigraph.getVertexNum(); i++) {
            if (!marked[i]){
                dfs(weightDigraph,i);
            }
        }
    }

    private void dfs(EdgeWeightDigraph weightDigraph,int vertex){
        marked[vertex]=true;
        for (DirectedEdge adjVertex : weightDigraph.adj(vertex)) {
            if (!marked[adjVertex.getEnd()]){
                dfs(weightDigraph,adjVertex.getEnd());
            }
        }
        reverseOrder.push(vertex);
    }

    public Iterable<Integer> reverseOrder(){
        return reverseOrder;
    }
}
