package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/2/25 上午8:51
 * TODO
 */
public class BreadFirstDirectedPathsTry {
    private int[] edgeTo;
    private boolean[] marked;
    private int startVertex;

    public BreadFirstDirectedPathsTry(Digraph digraph, int startVertex) {
        this.startVertex = startVertex;
        bfs(digraph, startVertex);
    }

    private void bfs(Digraph digraph, int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        marked[vertex] = true;
        while (!queue.isEmpty()) {
            vertex = queue.poll();
            for (int adjVertex : digraph.adj(vertex)) {
                if (!marked[adjVertex]) {
                    marked[adjVertex] = true;
                    edgeTo[adjVertex] = vertex;
                    queue.offer(vertex);
                }
            }

        }
    }

    public Stack<Integer> pathTo(int vertex) {
        if (!marked[vertex]) {
            return new Stack<>();
        }
        Stack<Integer> path = new Stack<>();
        for (int tmp = vertex; tmp != startVertex; tmp = edgeTo[tmp]) {
            path.push(tmp);
        }
        path.push(startVertex);
        return path;
    }
}
