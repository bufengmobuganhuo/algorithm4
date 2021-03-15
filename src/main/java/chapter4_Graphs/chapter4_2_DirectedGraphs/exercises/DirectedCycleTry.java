package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/2/25 上午10:18
 * TODO
 */
public class DirectedCycleTry {
    private boolean[] marked;
    private boolean[] onStack;
    private Stack<Integer> cycle;
    private int[] edgeTo;

    public DirectedCycleTry(Digraph digraph) {
        marked = new boolean[digraph.getVertexNum()];
        onStack = new boolean[digraph.getVertexNum()];
        cycle = new Stack<>();
        edgeTo = new int[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Digraph digraph, int start) {
        onStack[start] = true;
        marked[start] = true;
        if (hasCycle()) {
            return;
        }
        for (int adjVertex : digraph.adj(start)) {
            if (!marked[adjVertex]) {
                edgeTo[adjVertex] = start;
                dfs(digraph, adjVertex);
            } else if (onStack[adjVertex]) {
                cycle = new Stack<>();
                for (int tmp = start; tmp != adjVertex; tmp = edgeTo[tmp]) {
                    cycle.push(tmp);
                }
                cycle.push(adjVertex);
                cycle.push(start);
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }


}
