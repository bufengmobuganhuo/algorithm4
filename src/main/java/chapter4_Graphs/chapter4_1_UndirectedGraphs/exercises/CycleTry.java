package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

/**
 * @author yuzhang
 * @date 2020/8/3 9:51 上午
 * 使用深度优先搜索判断是否有环
 */
public class CycleTry {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph=new Digraph(new In(path));
        CycleTry cycle=new CycleTry(graph);
        System.out.print(cycle.hasCycle);
    }
    private boolean[] marked;
    private boolean hasCycle;

    public CycleTry(Digraph graph) {
        marked=new boolean[graph.getVertexNum()];
        for (int i = 0; i < graph.getVertexNum(); i++) {
            if (!marked[i]){
                dfs(graph,i,i);
            }
        }
    }

    private void dfs(Digraph graph, int start, int lastLayerVertex){
        marked[start]=true;
        for (int adjVertex:graph.adj(start)){
            if (!marked[adjVertex]){
                dfs(graph,adjVertex,start);
            }else if (adjVertex!=lastLayerVertex){
                hasCycle=true;
            }
        }
    }

}
