package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/19 上午9:02
 * TODO
 */
public class Ex_4_2_20_DirectedEulerCycle_2 {
    private final Digraph digraph;
    private Stack<Integer> cycle;

    public Ex_4_2_20_DirectedEulerCycle_2(Digraph digraph) {
        this.digraph = digraph;
        if (!check()){
            return;
        }
        findCycle();
    }

    private void findCycle(){
        Iterator<Integer>[] adj = new Iterator[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            adj[i] = digraph.adj(i).iterator();
        }

        cycle = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        int nonIsolateVertex = findNonIsolateVertex();
        stack.push(nonIsolateVertex);
        while (!stack.isEmpty()){
            int vertex = stack.pop();
            while (adj[vertex].hasNext()){
                stack.push(vertex);
                vertex = adj[vertex].next();
            }
            cycle.push(vertex);
        }
    }

    private int findNonIsolateVertex(){
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (digraph.outdegree(i)>0){
                return i;
            }
        }
        return -1;
    }

    private boolean check() {
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (digraph.outdegree(i) != digraph.indegree(i)) {
                return false;
            }
        }
        return true;
    }
}
