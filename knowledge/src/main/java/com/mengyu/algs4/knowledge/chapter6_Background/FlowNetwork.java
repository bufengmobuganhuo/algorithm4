package com.mengyu.algs4.knowledge.chapter6_Background;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/6/23 8:58 上午
 * 类似无向加权图
 */
public class FlowNetwork {
    private final int vertexNum;
    private int edgeNum;
    private LinkedList<FlowEdge>[] adj;

    public FlowNetwork(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("number of edges must be nonnegative");
        }
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            double capacity = in.readDouble();
            addEdge(new FlowEdge(v, w, capacity));
        }
    }

    public FlowNetwork(int vertexNum) {
        this.vertexNum = vertexNum;
        adj = new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(FlowEdge flowEdge) {
        edgeNum++;
        int vertex1 = flowEdge.from();
        int vertex2 = flowEdge.to();
        adj[vertex1].offer(flowEdge);
        adj[vertex2].offer(flowEdge);
    }

    public Iterable<FlowEdge> adj(int vertex) {
        return adj[vertex];
    }

    public Iterable<FlowEdge> edges() {
        LinkedList<FlowEdge> flowEdges = new LinkedList<>();
        for (int i = 0; i < adj.length; i++) {
            for (FlowEdge edge :
                    adj[i]) {
                flowEdges.offer(edge);
            }
        }
        return flowEdges;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }
}
