package leetcode.graph;

/**
 * @author yuzhang
 * @date 2020/8/13 8:47 下午
 * TODO
 */
public class Ex785 {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable;

    public boolean isBipartite(int[][] graph) {
        marked = new boolean[graph.length];
        color = new boolean[graph.length];
        isTwoColorable = true;
        for (int i = 0; i < graph.length; i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
        return isTwoColorable;
    }

    private void dfs(int[][] graph, int startVertex) {
        marked[startVertex] = true;
        for (int adjVertex : graph[startVertex]) {
            if (!marked[adjVertex]) {
                color[adjVertex] = !color[startVertex];
                dfs(graph, adjVertex);
            } else if (color[startVertex] == color[adjVertex]) {
                isTwoColorable = false;
                return;
            }
        }
    }
}
