package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/2/25 上午9:03
 * TODO
 */
public class DepthFirstOrderTry {
    Queue<Integer> preOrder;
    Queue<Integer> postOrder;
    Stack<Integer> reverseOrder;
    boolean[] marked;

    public DepthFirstOrderTry(Digraph digraph) {
        preOrder = new LinkedList<>();
        postOrder = new LinkedList<>();
        reverseOrder = new Stack<>();
        marked = new boolean[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Digraph digraph, int vertex) {
        preOrder.offer(vertex);
        marked[vertex] = true;
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[adjVertex]) {
                dfs(digraph, adjVertex);
            }
        }
        postOrder.offer(vertex);
        reverseOrder.push(vertex);
    }
}
