package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;

/**
 * @author zhangyu
 * 2020/5/13 11:47
 * 有向权重图
 */
public class EdgeWeightDigraph {
    private int vertexNum;
    private int edgeNum;
    private LinkedList<DirectedEdge>[] adj;

    public EdgeWeightDigraph(In in) {
        this(in.readInt());
        int edgeNum = in.readInt();
        if (edgeNum < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        }
        for (int i = 0; i < edgeNum; i++) {
            int start = in.readInt();
            int end = in.readInt();
            double weight = in.readDouble();
            DirectedEdge edge=new DirectedEdge(start,end,weight);
            addEdge(edge);
        }
    }

    public EdgeWeightDigraph(int vertexNum) {
        this.vertexNum = vertexNum;
        adj=new LinkedList[vertexNum];
        for (int i=0;i<vertexNum;i++){
            adj[i]=new LinkedList<>();
        }
    }

    public void addEdge(DirectedEdge edge){
        int start=edge.getStart();
        adj[start].add(edge);
        edgeNum++;
    }

    public Iterable<DirectedEdge> adj(int vertex){
        return adj[vertex];
    }

    public Iterable<DirectedEdge> edges(){
        LinkedList<DirectedEdge> edges=new LinkedList<>();
        for (int i=0;i<vertexNum;i++){
            for (DirectedEdge edge:adj[i]){
                edges.add(edge);
            }
        }
        return edges;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }
}
