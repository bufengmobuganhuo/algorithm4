package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/10 10:07 上午
 * TODO
 */
public class CycleEdgeWightedDigraph {
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;
    private DirectedEdge[] edgeTo;
    private boolean[] marked;

    public CycleEdgeWightedDigraph(EdgeWeightDigraph weightDigraph) {
        marked=new boolean[weightDigraph.getVertexNum()];
        onStack=new boolean[weightDigraph.getVertexNum()];
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        for (int i = 0; i < weightDigraph.getVertexNum(); i++) {
            if (!marked[i]){
                dfs(weightDigraph,i);
            }
        }
    }

    private void dfs(EdgeWeightDigraph weightDigraph,int vertex){
        onStack[vertex]=true;
        marked[vertex]=true;
        for (DirectedEdge adjEdge : weightDigraph.adj(vertex)) {
            int endVertex=adjEdge.getEnd();
            if (hasCycle()){
                return;
            }else if (!marked[endVertex]){
                edgeTo[endVertex]=adjEdge;
                dfs(weightDigraph,endVertex);
            }else if (onStack[endVertex]){
                cycle=new Stack<>();
                DirectedEdge edge=adjEdge;
                while(edge.getStart()!=endVertex){
                    cycle.push(edge);
                    edge=edgeTo[edge.getStart()];
                }
                cycle.push(edge);
                return;
            }
        }
        onStack[vertex]=false;
    }

    public boolean hasCycle(){
        return cycle!=null;
    }

    public Iterable<DirectedEdge> cycle(){
        return cycle;
    }
}
