package chapter4_Graphs.chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;

/**
 * @author zhangyu
 * 2020/4/21 15:00
 * 连通分量
 */
public class ConnectedComponent {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph=new Digraph(new In(path));
        ConnectedComponent connectedComponent=new ConnectedComponent(graph);
        System.out.print(connectedComponent.count);
    }
    private boolean[] marked;
    /**
     * id[vertex]=1，表示顶点vertex在id=1的连通分量中
     */
    private int[] id;

    /**
     * 连通分量个数
     */
    private int count;

    public ConnectedComponent(Digraph graph) {
        id=new int[graph.getVertexNum()];
        marked=new boolean[graph.getVertexNum()];
        for (int start=0;start<graph.getVertexNum();start++){
            //深度优先搜索未被标记的顶点
            if (!marked[start]){
                dfs(graph,start);
                //没有被深度优先搜索到的属于另外一个连通分量
                count++;
            }
        }
    }

    private void dfs(Digraph graph, int start){
        marked[start]=true;
        //被深度优先搜索到的节点都和start连通
        // 在同一个连通分量中
        id[start]=count;
        for (int vertex:graph.adj(start)){
            if (!marked[vertex]){
                dfs(graph,vertex);
            }
        }
    }

    /**
     * @param vertex1
     * @param vertex2
     * @return 两个顶点是否连通
     */
    public boolean connected(int vertex1,int vertex2){
        //如果在同一个连通分量，则是连通的
        return id[vertex1]==id[vertex2];
    }

    /**
     * @return 连通分量个数
     */
    public int count(){
        return count;
    }

}
