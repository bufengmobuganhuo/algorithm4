package chapter4_Graphs.chapter4_3_MinimumSpanningTrees;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Set;

/**
 * @author zhangyu
 * 2020/5/8 10:24
 * 加权无向图
 */
public class EdgeWeightedGraph {
    private final int vertexNum;
    private int edgeNum;
    private LinkedList<Edge>[] adj;

    public EdgeWeightedGraph(int vertexNum) {
        this.vertexNum = vertexNum;
        this.edgeNum=0;
        adj=new LinkedList[vertexNum];
        for (int i=0;i<vertexNum;i++){
            adj[i]=new LinkedList<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int edgeNum = in.readInt();
        if (edgeNum < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        }
        for (int i = 0; i < edgeNum; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge edge=new Edge(v,w,weight);
            addEdge(edge);
        }
    }

    /**
     * @return 图的顶点数
     */
    public int getVertexNum() {
        return vertexNum;
    }

    /**
     * @return 图的边数
     */
    public int getEdgeNum() {
        return edgeNum;
    }

    /**
     * @param edge 添加一条边
     */
    public void addEdge(Edge edge){
        int vertex1=edge.either();
        int vertex2=edge.other(vertex1);
        adj[vertex1].add(edge);
        adj[vertex2].add(edge);
        edgeNum++;
    }

    /**
     * @param degree
     * @return 找到给定度数的顶点
     */
    public int findVertexGivenDegree(int degree){
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].size()==degree){
                return i;
            }
        }
        return -1;
    }


    /**
     * @param vertex
     * @return 返回vertex的边
     */
    public Iterable<Edge> adj(int vertex){
        return adj[vertex];
    }

    /**
     * @return 返回所有边
     */
    public Iterable<Edge> edges(){
        LinkedList<Edge> linkedList=new LinkedList<>();
        for (int vertex=0;vertex<vertexNum;vertex++){
            for (Edge edge:adj[vertex]){
                //由于是两个顶点连一个边，所以只选大的顶点连接的边，防止重复
                if (edge.other(vertex)>vertex){
                    linkedList.add(edge);
                }
            }
        }
        return linkedList;
    }
}
