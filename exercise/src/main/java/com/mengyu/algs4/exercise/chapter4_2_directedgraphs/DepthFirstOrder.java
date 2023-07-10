package com.mengyu.algs4.exercise.chapter4_2_directedgraphs;

import edu.princeton.cs.algs4.Digraph;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class DepthFirstOrder {
    private boolean[] marked;

    private Queue<Integer> preQue;

    private Queue<Integer> postQue;

    private Stack<Integer> reversePostStack;

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.V()];
        preQue = new LinkedList<>();
        postQue = new LinkedList<>();
        reversePostStack = new Stack<>();

        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Digraph digraph, int vertex) {
        marked[vertex] = true;
        preQue.offer(vertex);
        for (int adjVertex : digraph.adj(vertex)) {
            if(!marked[adjVertex]) {
                dfs(digraph, adjVertex);
            }
        }
        postQue.offer(vertex);
        reversePostStack.push(vertex);
    }
}
