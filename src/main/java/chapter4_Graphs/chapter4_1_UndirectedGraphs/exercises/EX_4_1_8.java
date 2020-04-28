package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Graph;
import edu.princeton.cs.algs4.In;

/**
 * @author zhangyu
 * 2020/4/22 16:23
 * 练习4.1.8：使用union-find实现Search
 */
public class EX_4_1_8{
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyG.txt";
        Graph graph=new Graph(new In(path));
        EX_4_1_8 ex_4_1_8=new EX_4_1_8(graph,0);
        System.out.println(ex_4_1_8.marked(4));
        System.out.println(ex_4_1_8.count());
    }
    /**
     * treeSize[0] 顶点0的连通分量的大小
     */
    private int[] treeSize;

    /**
     * 各个顶点所在的连通分量Id
     */
    private int[] connectedComponentId;

    private final int start;

    public EX_4_1_8(Graph graph,int start) {
        this.start=start;
        treeSize=new int[graph.getVertexNum()];
        connectedComponentId=new int[graph.getVertexNum()];
        for (int i=start;i<graph.getVertexNum();i++){
            //一开始所有顶点独立,分量为1，根触点是自己
            treeSize[i]=1;
            connectedComponentId[i]=i;
        }
        for (int i=start;i<graph.getVertexNum();i++){
            for (Integer vertex:graph.adj(i)){
                union(i,vertex);
            }
        }
    }

    private void union(int vertex1,int vertex2){
        int rootVertex1=find(vertex1);
        int rootVertex2=find(vertex2);
        if (rootVertex1==rootVertex2){
            return;
        }
        //将小连通分量挂载到大连通分量上
        if (treeSize[rootVertex1]>treeSize[rootVertex2]){
            connectedComponentId[rootVertex2]=rootVertex1;
            treeSize[rootVertex1]+=treeSize[rootVertex2];
        }else{
            connectedComponentId[rootVertex1]=rootVertex2;
            treeSize[rootVertex2]+=treeSize[rootVertex1];
        }
    }

    /**
     * @param vertex
     * @return 顶点对应的连通分量根（connectedComponentId[vertex]=vertex）
     */
    private int find(int vertex){
        while (connectedComponentId[vertex]!=vertex){
            vertex=connectedComponentId[vertex];
        }
        return vertex;
    }

    /**
     * @param vertex
     * @return 顶点vertex是否和起点start连通
     */
    public boolean marked(int vertex){
        return find(vertex)==find(start);
    }

    /**
     * @return 与起点连通的顶点数
     */
    public int count(){
        return treeSize[find(start)];
    }
}
