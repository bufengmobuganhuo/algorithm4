package com.mengyu.algs4.exercise.chapter4_2_directedgraphs;

import edu.princeton.cs.algs4.Digraph;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class DirectedCycle {
    private boolean[] marked;

    private int[] edgeTo;

    private boolean[] onStack;

    private boolean hasCycle;

    private Stack<Integer> stack;

    public DirectedCycle(Digraph digraph) {
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        onStack = new boolean[digraph.V()];
        hasCycle = false;
        stack = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Digraph digraph, int vertex) {
        marked[vertex] = true;
        onStack[vertex] = true;
        for (int adjVertex : digraph.adj(vertex)) {
            if (hasCycle) {
                return;
            } else if (!marked[adjVertex]) {
                edgeTo[adjVertex] = vertex;
                dfs(digraph, adjVertex);
            } else if (onStack[adjVertex]) {
                hasCycle = true;
                for (int tmp = vertex; tmp != adjVertex; tmp = edgeTo[tmp]) {
                    stack.push(tmp);
                }
                stack.push(adjVertex);
                stack.push(vertex);
            }
        }
        onStack[vertex] = false;
    }
}
