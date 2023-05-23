package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;

/**
 * @author zhangyu
 * 2020/5/24 17:02
 * 练习4.4.31：此处说的最短路径，是指路径的权重和
 */
public class EX_4_4_31 {
    public static void main(String[] args) {
        EdgeWeightedGraph weightedGraph=new EdgeWeightedGraph(6);
        weightedGraph.addEdge(new Edge(0,1,1));
        weightedGraph.addEdge(new Edge(1,2,3));
        weightedGraph.addEdge(new Edge(2,3,2));
        weightedGraph.addEdge(new Edge(3,4,4));
        weightedGraph.addEdge(new Edge(4,5,7));
        EX_4_4_31 ex_4_4_31=new EX_4_4_31(weightedGraph);
        System.out.println(ex_4_4_31.getShortestPath(3,5));
    }
    private double[] distTo;

    public EX_4_4_31(EdgeWeightedGraph weightedGraph) {
        distTo=new double[weightedGraph.getVertexNum()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        int start=weightedGraph.findVertexGivenDegree(1);
        distTo[start]=0.0;
        dfs(weightedGraph, start);

    }

    private void dfs(EdgeWeightedGraph weightedGraph,int start){
        for (Edge adjEdge:weightedGraph.adj(start)){
            int vertex=adjEdge.other(start);
            if (distTo[vertex]==Double.POSITIVE_INFINITY){
                distTo[vertex]=distTo[start]+adjEdge.getWeight();
                dfs(weightedGraph,vertex);
            }
        }
    }

    public double getShortestPath(int vertex1,int vertex2){
        return Math.abs(distTo[vertex2]-distTo[vertex1]);
    }
}
