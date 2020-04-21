package chapter4_Graphs.chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zhangyu
 * 2020/4/20 20:07
 * TODO
 */
public class Graph{
    //顶点数
    private final int vertexNum;
    //边数
    private int edgeNum;
    //邻接表数组
    private LinkedList<Integer>[] adj;

    public Graph(int vertexNum) {
        this.vertexNum = vertexNum;
        adj=new LinkedList[vertexNum];
        for (int i=0;i<vertexNum;i++){
            adj[i]=new LinkedList<>();
        }
    }

    public Graph(In in){
        this(in.readInt());
        int edgeNum=in.readInt();
        for (int i=0;i<edgeNum;i++){
            int vertex1=in.readInt();
            int vertex2=in.readInt();
            addEdge(vertex1,vertex2);
        }
    }

    /**
     * @return 获取顶点数
     */
    public int getVertexNum(){
        return vertexNum;
    }

    /**
     * @return 获取边数
     */
    public int getEdgeNum(){
        return edgeNum;
    }

    /**
     * 添加一条边
     * @param vertex1
     * @param vertex2
     */
    public void addEdge(int vertex1,int vertex2){
        adj[vertex1].add(vertex2);
        adj[vertex2].add(vertex1);
        edgeNum++;
    }

    /**
     * 获取某个顶点的所有边
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
