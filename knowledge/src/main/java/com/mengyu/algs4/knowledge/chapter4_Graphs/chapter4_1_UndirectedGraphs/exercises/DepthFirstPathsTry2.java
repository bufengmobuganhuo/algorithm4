package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/2/23 上午8:58
 * TODO
 */
public class DepthFirstPathsTry2 {
    private boolean[] marked;
    private int[] edgeTo;
    private int startVertex;

    public DepthFirstPathsTry2(Digraph graph, int startVertex) {
        marked = new boolean[graph.getVertexNum()];
        edgeTo = new int[graph.getVertexNum()];
        this.startVertex = startVertex;
        dfs(graph,startVertex);
    }

    private void dfs(Digraph graph, int startVertex){
        marked[startVertex] = true;
        for (int adjVertex : graph.adj(startVertex)) {
            if (!marked[adjVertex]){
                edgeTo[adjVertex]=startVertex;
                dfs(graph,adjVertex);
            }
        }
    }

    private Stack<Integer> pathTo(int targetVertex){
        Stack<Integer> path = new Stack<>();
        if (!marked[targetVertex]){
            return path;
        }
        for (int vertex = targetVertex; vertex != startVertex; vertex=edgeTo[vertex]) {
            path.push(vertex);
        }
        return path;
    }
}
