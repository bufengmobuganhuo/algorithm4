package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

/**
 * @author yuzhang
 * @date 2021/2/23 上午10:50
 * TODO
 */
public class ConnectedComponentTry2 {
    private int[] id;
    private int count;
    private boolean[] marked;

    public ConnectedComponentTry2(Digraph digraph) {
        id = new int[digraph.getVertexNum()];
        marked = new boolean[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
                count++;
            }
        }
    }

    private void dfs(Digraph digraph, int start) {
        marked[start] = true;
        id[start] = count;
        for (int adjVertex : digraph.adj(start)) {
            if (!marked[adjVertex]) {
                dfs(digraph, adjVertex);
            }
        }
    }
}
