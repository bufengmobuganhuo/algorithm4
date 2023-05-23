package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/2/24 上午8:52
 * TODO
 */
public class Ex_4_1_17_3 {
    private int[] distTo;
    private int[] edgeTo;

    public int girth(Digraph digraph) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            min = Math.min(min, bfs(digraph, i));
        }
        return min;
    }

    private int bfs(Digraph graph, int vertex) {
        distTo = new int[graph.getVertexNum()];
        edgeTo = new int[graph.getVertexNum()];
        Arrays.fill(distTo, -1);
        distTo[vertex] = 0;
        Queue<Integer> que = new LinkedList<>();
        que.offer(vertex);
        int min = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            vertex = que.poll();
            for (int adjVertex : graph.adj(vertex)) {
                if (distTo[adjVertex] == -1) {
                    que.offer(adjVertex);
                    distTo[adjVertex] = distTo[vertex] + 1;
                    edgeTo[adjVertex] = vertex;
                } else if (edgeTo[vertex] != adjVertex) {
                    min = Math.min(min, distTo[adjVertex] + distTo[vertex] + 1);
                }
            }
        }
        return min;
    }
}
