package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/18 10:30
 * TODO
 */
public class EdgeWeightedDigraphOrder {
    private Queue<Integer> preOrder;
    private Queue<Integer> postOrder;
    private Stack<Integer> reversePostOrder;
    private boolean[] marked;

    public EdgeWeightedDigraphOrder(EdgeWeightDigraph weightDigraph) {
        marked = new boolean[weightDigraph.getVertexNum()];
        preOrder = new LinkedList<>();
        postOrder = new LinkedList<>();
        reversePostOrder = new Stack<>();
        for (int i = 0; i < weightDigraph.getVertexNum(); i++) {
            if (!marked[i]) {
                dfs(weightDigraph, i);
            }
        }
    }

    private void dfs(EdgeWeightDigraph weightDigraph, int startVertex) {
        preOrder.offer(startVertex);
        marked[startVertex] = true;
        for (DirectedEdge adjEdge : weightDigraph.adj(startVertex)) {
            int end = adjEdge.getEnd();
            if (!marked[end]) {
                dfs(weightDigraph, end);
            }
        }
        postOrder.offer(startVertex);
        reversePostOrder.push(startVertex);
    }

    public Queue<Integer> getPreOrder() {
        return preOrder;
    }

    public Queue<Integer> getPostOrder() {
        return postOrder;
    }

    public Stack<Integer> getReversePostOrder() {
        return reversePostOrder;
    }
}
