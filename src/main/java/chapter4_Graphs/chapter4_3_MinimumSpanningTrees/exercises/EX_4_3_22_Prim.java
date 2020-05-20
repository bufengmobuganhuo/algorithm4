package chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.PrimMST;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/5/12 10:21
 * 练习4.3.22:不一定连通的权重图的最小生成树 or 森林
 */
public class EX_4_3_22_Prim {
    /**
     * 顶点是否在最小生成树中
     */
    private boolean[] marked;

    /**
     * edgeTo[v]：顶点v与树中某个顶点相连的最短边
     */
    private Edge[] edgeTo;

    /**
     * distTo[v]：顶点v与树中某个顶点相连的最短边的权重
     */
    private double[] distTo;

    /**
     * 索引优先队列，索引为顶点
     */
    private IndexMinPQ<Double> indexMinPq;

    public EX_4_3_22_Prim(EdgeWeightedGraph weightedGraph) {
        marked=new boolean[weightedGraph.getVertexNum()];
        edgeTo=new Edge[weightedGraph.getVertexNum()];
        distTo=new double[weightedGraph.getVertexNum()];
        for (int i=0;i<distTo.length;i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        for (int i=0;i<weightedGraph.getVertexNum();i++){
            //遍历在一个连通分量中的顶点
            if (!marked[i]){
                scan(weightedGraph, i);
            }
        }
    }

    /**
     * @param weightedGraph
     * @param vertex 从vertex开始扫描
     */
    private void scan(EdgeWeightedGraph weightedGraph,int vertex){
        distTo[vertex]=0.0;
        indexMinPq.insert(vertex,0.0);
        while (!indexMinPq.isEmpty()){
            prim(weightedGraph,indexMinPq.delMin());
        }
    }

    private void prim(EdgeWeightedGraph weightedGraph, int vertex){
        marked[vertex]=true;
        for (Edge edge:weightedGraph.adj(vertex)){
            int adjVertex=edge.other(vertex);
            if (marked[adjVertex]){
                continue;
            }
            //更新最小权重以及对应的边
            if (edge.getWeight()<distTo[adjVertex]){
                distTo[adjVertex]=edge.getWeight();
                edgeTo[adjVertex]=edge;
                if (indexMinPq.contains(adjVertex)){
                    indexMinPq.decreaseKey(adjVertex,edge.getWeight());
                }else{
                    indexMinPq.insert(adjVertex, edge.getWeight());
                }
            }
        }
    }

    /**
     * @return 获取最小生成树（图是连通的）or 最小生成森林（图是非连通的）
     */
    public Iterable<Edge> edges(){
        LinkedList<Edge> edges=new LinkedList<>();
        for (int i=0;i<edgeTo.length;i++){
            //如果是两个连通分量之间，则edgeTo[v]=null
            if (edgeTo[i]!=null){
                edges.add(edgeTo[i]);
            }
        }
        return edges;
    }
}
