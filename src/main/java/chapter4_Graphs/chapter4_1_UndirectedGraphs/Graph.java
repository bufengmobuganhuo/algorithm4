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
    //每个顶点的度数
    private int[] degree;

    public Graph(int vertexNum) {
        this.vertexNum = vertexNum;
        adj=new LinkedList[vertexNum];
        for (int i=0;i<vertexNum;i++){
            adj[i]=new LinkedList<>();
        }
    }

    public Graph(Graph graph){
        this.vertexNum=graph.getVertexNum();
        adj=new LinkedList[vertexNum];
        adj=graph.adj;
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
     * @param vertex1
     * @param vertex2
     * @return 返回两个顶点之间是否有边相连
     */
    public boolean hasEdge(int vertex1,int vertex2){
        return adj[vertex1].contains(vertex2);
    }

    /**
     * @param vertex
     * @return 获取某个顶点的度数
     */
    public int degree(int vertex){
        return degree[vertex];
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
        if (vertex1==vertex2){
            throw new IllegalArgumentException("不允许自环");
        }
        /*if (adj[vertex1].contains(vertex2)){
            throw new IllegalArgumentException("不允许含有平行边");
        }*/
        adj[vertex1].add(vertex2);
        degree[vertex1]+=1;
        adj[vertex2].add(vertex1);
        degree[vertex2]+=1;
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
