package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

/**
 * @author yuzhang
 * @date 2021/2/25 上午9:01
 * TODO
 */
public class DepthFirstDirectedPathsTry {
    private boolean[] marked;

    private void dfs(Digraph digraph,int vertex){
        marked[vertex] = true;
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[vertex]){
                dfs(digraph,adjVertex);
            }
        }
    }
}
