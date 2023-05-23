package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/18 上午10:02
 * TODO
 */
public class DirectedCycle2 {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyDG";
        In in = new In(path);
        Digraph digraph = new Digraph(in);
        DirectedCycle2 directedCycle = new DirectedCycle2(digraph);
        if (directedCycle.hasCycle()) {
            Stack<Integer> cycle = (Stack<Integer>) directedCycle.cycle();
            while (!cycle.isEmpty()) {
                System.out.print(cycle.pop() + "-");
            }
        }
    }

    private int[] edgeTo;
    private boolean[] marked;
    private Stack<Integer> path;
    private boolean[] onStack;

    public DirectedCycle2(Digraph digraph) {
        edgeTo = new int[digraph.getVertexNum()];
        marked = new boolean[digraph.getVertexNum()];
        onStack = new boolean[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Digraph digraph, int startVertex) {
        marked[startVertex] = true;
        onStack[startVertex] = true;
        for (int adjVertex : digraph.adj(startVertex)) {
            if (hasCycle()) {
                return;
            }
            if (!marked[adjVertex]) {
                edgeTo[adjVertex] = startVertex;
                dfs(digraph, adjVertex);
            } else if (onStack[adjVertex]) {
                path = new Stack<>();
                for (int tmp = startVertex; tmp != adjVertex; tmp = edgeTo[tmp]) {
                    path.push(tmp);
                }
                path.push(adjVertex);
                path.push(startVertex);
            }
        }
        onStack[startVertex] = false;
    }

    public boolean hasCycle() {
        return path != null;
    }

    public Iterable<Integer> cycle() {
        return path;
    }
}
