package chapter4_Graphs.chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;

/**
 * @author zhangyu
 * 2020/4/21 15:59
 * 判断一个无向图是否存在环
 */
public class Cycle {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyG.txt";
        Graph graph=new Graph(new In(path));
        Cycle cycle=new Cycle(graph);
        System.out.print(cycle.hasCycle);
    }
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked=new boolean[graph.getVertexNum()];
        for (int start=0;start<graph.getVertexNum();start++){
            if (!marked[start]){
                dfs(graph,start,start);
            }
        }
    }

    private void dfs(Graph graph,int start,int lastLevelVertex){
        marked[start]=true;
        for (int vertex:graph.adj(start)){
            if (!marked[vertex]){
                dfs(graph,vertex,start);
                //如果这个顶点被标记过，而且不是上一层的顶点
                // （从0 -> 1为上一层，从1 -> 0是下一层），
                // 说明存在一条路径，让起点经过这个路径后又回到了起点（环）
            }else if(vertex!=lastLevelVertex){
                hasCycle=true;
            }
        }
    }
}
