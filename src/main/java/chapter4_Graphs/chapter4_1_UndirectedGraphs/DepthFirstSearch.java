package chapter4_Graphs.chapter4_1_UndirectedGraphs;

/**
 * @author zhangyu
 * 2020/4/21 10:35
 * 深度优先搜索
 */
public class DepthFirstSearch {
    //顶点是否被访问过
    private boolean[] marked;
    //搜索过程中的顶点访问次数
    private int count;

    public DepthFirstSearch(Digraph graph, int start) {
        //从start开始深度优先搜索
        dfs(graph,start);
    }

    /**
     * @param graph 深度优先搜索
     * @param start 起点
     */
    private void dfs(Digraph graph, int start){
        marked[start]=true;
        count++;
        for (int vertex:graph.adj(start)){
            if (!marked[vertex]){
                dfs(graph,vertex);
            }
        }
    }

    public int getCount(){
        return count;
    }

    /**
     * @param vertex 顶点vertex是否被访问过
     * @return
     */
    public boolean marked(int vertex){
        return marked[vertex];
    }


}
