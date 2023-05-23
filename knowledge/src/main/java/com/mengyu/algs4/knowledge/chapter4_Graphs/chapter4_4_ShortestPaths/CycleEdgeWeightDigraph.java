package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/18 10:12
 * 判定加权有向图是否有环
 */
public class CycleEdgeWeightDigraph {
    private boolean[] marked;
    /**
     * edgeTo[vertex]：到达顶点vertex的边
     */
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public CycleEdgeWeightDigraph(EdgeWeightDigraph weightDigraph) {
        marked=new boolean[weightDigraph.getVertexNum()];
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        onStack=new boolean[weightDigraph.getVertexNum()];
        for (int i=0;i<weightDigraph.getVertexNum();i++){
            if (!marked[i]){
                dfs(weightDigraph,i);
            }
        }
    }

    private void dfs(EdgeWeightDigraph weightDigraph,int start){
        marked[start]=true;
        //开始递归，在栈上
        onStack[start]=true;
        for (DirectedEdge adjEdge:weightDigraph.adj(start)){
            int end=adjEdge.getEnd();
            //如果已经有环，则直接返回
            if (hasCycle()){
                return;
                //如果没有被访问过，则继续
            }else if (!marked[end]){
                edgeTo[end]=adjEdge;
                dfs(weightDigraph, end);
                //如果边的终点也在同一个递归上，则说明有环
            }else if (onStack[end]){
                cycle = new Stack<>();
                DirectedEdge edge=adjEdge;
                while (edge.getStart() != end){
                    cycle.push(edge);
                    edge = edgeTo[edge.getStart()];
                }
                cycle.push(edge);
                return;
            }
        }
        onStack[start]=false;
    }

    public boolean hasCycle(){
        return cycle!=null;
    }

    public Stack<DirectedEdge> getCycle(){
        return cycle;
    }
}
