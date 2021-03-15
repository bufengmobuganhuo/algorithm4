package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

/**
 * @author yuzhang
 * @date 2021/2/23 上午11:00
 * TODO
 */
public class TwoColorTry {
    private boolean[] color;
    private boolean[] marked;
    private boolean isTwoColorable;

    public TwoColorTry(Digraph digraph) {
        color = new boolean[digraph.getVertexNum()];
        marked = new boolean[digraph.getVertexNum()];
        isTwoColorable = true;
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]){
                dfs(digraph,i);
            }
        }
    }

    private void dfs(Digraph digraph, int start) {
        marked[start] = true;
        for (int adjVertex : digraph.adj(start)) {
            if (!marked[adjVertex]) {
                color[adjVertex] = !color[start];
                dfs(digraph, adjVertex);
            } else if (color[start] == color[adjVertex]) {
                isTwoColorable = false;
            }
        }
    }
}
