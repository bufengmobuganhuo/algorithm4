package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

/**
 * @author yuzhang
 * @date 2021/2/23 上午10:56
 * TODO
 */
public class CycleTry2 {
    private boolean[] marked;
    private boolean hasCycle;

    public CycleTry2(Digraph digraph) {
        marked=new boolean[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]){
                dfs(digraph,i,i);
            }
        }
    }

    private void dfs(Digraph digraph, int start, int lastVertex) {
        marked[start] = true;
        for (int adjVertex : digraph.adj(start)) {
            if (!marked[adjVertex]) {
                dfs(digraph, adjVertex, start);
            } else if (adjVertex != lastVertex) {
                hasCycle = true;
            }
        }
    }
}
