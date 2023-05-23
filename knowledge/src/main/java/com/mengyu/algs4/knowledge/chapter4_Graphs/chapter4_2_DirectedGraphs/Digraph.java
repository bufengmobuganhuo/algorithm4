package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;

/**
 * @author zhangyu
 * 2020/4/29 10:36
 * 有向图
 */
public class Digraph {
    private final int vertexNum;
    private int edgeNum;
    private LinkedList<Integer>[] adj;
    //入度
    private int[] indegree;

    public Digraph(int vertexNum) {
        this.vertexNum = vertexNum;
        adj = new LinkedList[vertexNum];
        indegree = new int[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public Digraph(In in) {
        this(in.readInt());
        int edgeNum = in.readInt();
        for (int i = 0; i < edgeNum; i++) {
            int vertex1 = in.readInt();
            int vertex2 = in.readInt();
            addEdge(vertex1, vertex2);
        }
    }

    /**
     * @param vertex
     * @return 顶点vertex的出度
     */
    public int outdegree(int vertex) {
        return adj[vertex].size();
    }

    /**
     * @param vertex
     * @return 顶点vertex的入度
     */
    public int indegree(int vertex) {
        return indegree[vertex];
    }

    /**
     * 添加一条从vertex1 -> vertex2的有向边
     *
     * @param vertex1
     * @param vertex2
     */
    public void addEdge(int vertex1, int vertex2) {
        adj[vertex1].add(vertex2);
        indegree[vertex2]++;
        edgeNum++;
    }

    /**
     * @param vertex1
     * @param vertex2
     * @return 是否有一条 vertex1 -> vertex2的边
     */
    public boolean hasEdge(int vertex1, int vertex2) {
        return adj[vertex1].contains(vertex2);
    }

    /**
     * @param vertex
     * @return 获取vertex能到达的边
     */
    public Iterable<Integer> adj(int vertex) {
        return adj[vertex];
    }

    /**
     * @return 将当前有向图翻转
     */
    public Digraph reverse() {
        Digraph digraph = new Digraph(vertexNum);
        for (int i = 0; i < vertexNum; i++) {
            for (int vertex : adj(i)) {
                digraph.addEdge(vertex, i);
            }
        }
        return digraph;
    }

    public int getVertexNum() {
        return vertexNum;
    }
}
