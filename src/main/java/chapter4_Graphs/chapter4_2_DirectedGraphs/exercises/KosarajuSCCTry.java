package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/2/25 上午10:53
 * TODO
 */
public class KosarajuSCCTry {
    private int[] id;
    private boolean[] marked;
    private int count;

    public KosarajuSCCTry(Digraph digraph) {
        id = new int[digraph.getVertexNum()];
        marked = new boolean[digraph.getVertexNum()];
        DepthFirstOrderTry depthFirstOrderTry = new DepthFirstOrderTry(digraph.reverse());
        Stack<Integer> reverseOrder = depthFirstOrderTry.reverseOrder;
        while (!reverseOrder.isEmpty()) {
            int vertex = reverseOrder.pop();
            if (!marked[vertex]) {
                dfs(digraph, vertex);
                count++;
            }
        }
    }

    private void dfs(Digraph digraph, int vertex) {
        marked[vertex] = true;
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[adjVertex]) {
                id[adjVertex] = count;
            }
        }
    }
}
