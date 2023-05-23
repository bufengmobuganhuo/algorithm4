package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;


import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/8/3 8:56 上午
 * 构造图，第一次尝试
 */
public class GraphTry {
    private LinkedList<Integer>[] adj;
    private int edgeNum;
    private int vertexNum;

    public GraphTry(int vertexNum) {
        this.vertexNum = vertexNum;
        adj=new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adj[i]=new LinkedList<>();
        }
    }

    public void addEdge(int vertex1,int vertex2){
        if (vertex1==vertex2){
            throw new IllegalArgumentException("不允许自环");
        }
        adj[vertex1].offer(vertex2);
        adj[vertex2].offer(vertex1);
        edgeNum++;
    }

    public Iterable<Integer> adj(int vertex){
        return adj[vertex];
    }
}
