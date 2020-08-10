package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.ConnectedComponent;
import chapter4_Graphs.chapter4_1_UndirectedGraphs.Graph;
import edu.princeton.cs.algs4.In;

/**
 * @author yuzhang
 * @date 2020/8/3 9:46 上午
 * 连通分量：第一次尝试
 */
public class ConnectedComponentTry {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Graph graph=new Graph(new In(path));
        ConnectedComponentTry connectedComponent=new ConnectedComponentTry(graph);
        System.out.print(connectedComponent.count);
    }
    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponentTry(Graph graph) {
        marked = new boolean[graph.getVertexNum()];
        id = new int[graph.getVertexNum()];
        for (int i = 0; i < graph.getVertexNum(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
                // 没被遍历到的id+1
                count++;
            }
        }
    }

    private void dfs(Graph graph, int start) {
        marked[start] = true;
        id[start] = count;
        for (int vertex : graph.adj(start)) {
            if (!marked[vertex]) {
                dfs(graph, vertex);
            }
        }
    }

    public boolean isConnected(int vertex1, int vertex2) {
        return id[vertex1] == id[vertex2];
    }
}
