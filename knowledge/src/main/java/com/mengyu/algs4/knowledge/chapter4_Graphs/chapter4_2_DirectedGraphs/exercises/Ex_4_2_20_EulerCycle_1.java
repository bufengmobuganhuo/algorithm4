package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/19 上午8:42
 * TODO
 */
public class Ex_4_2_20_EulerCycle_1 {
    private final Digraph graph;
    private Stack<Integer> cycle;

    public Ex_4_2_20_EulerCycle_1(Digraph graph) {
        this.graph = graph;
        this.cycle = new Stack<>();
        // 检查必要条件
        if (!check()) {
            return;
        }
        LinkedList[] adj = buildDigraph();
        findCycle(adj);
    }

    private void findCycle(LinkedList[] adj) {
        cycle = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        int nonIsolateVertex = findNonIsolateVertex();
        stack.push(nonIsolateVertex);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            while (!adj[vertex].isEmpty()) {
                Edge edge = (Edge) adj[vertex].poll();
                if (edge.isUsed) {
                    continue;
                }
                edge.isUsed = true;
                stack.push(vertex);
                vertex = edge.other(vertex);
            }
            cycle.push(vertex);
        }
    }

    private int findNonIsolateVertex() {
        for (int i = 0; i < graph.getVertexNum(); i++) {
            if (graph.degree(i) > 0) {
                return i;
            }
        }
        return -1;
    }

    private boolean check() {
        for (int i = 0; i < graph.getVertexNum(); i++) {
            if (graph.degree(i) % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    private LinkedList[] buildDigraph() {
        LinkedList[] adj = new LinkedList[graph.getVertexNum()];
        for (int i = 0; i < graph.getVertexNum(); i++) {
            adj[i] = new LinkedList();
        }
        for (int i = 0; i < graph.getVertexNum(); i++) {
            for (int adjVertex : graph.adj(i)) {
                // 避免重复构建边
                if (i <= adjVertex) {
                    Edge edge = new Edge(i, adjVertex);
                    adj[i].offer(edge);
                    adj[adjVertex].offer(edge);
                }
            }
        }
        return adj;
    }

    // 双向边
    static class Edge {
        int vertex1;
        int vertex2;
        boolean isUsed;

        public Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        public int other(int vertex) {
            if (vertex == vertex1) {
                return vertex2;
            }
            return vertex1;
        }
    }
}
