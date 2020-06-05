package chapter4_Graphs.chapter4_4_ShortestPaths;

import chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/14 15:46
 * 迪杰克斯拉算法找加权有向图的最短路径
 */
public class DijkstraSP {
    public static void main(String[] args) {
    }
    private IndexMinPQ<Double> indexMinPQ;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public DijkstraSP(EdgeWeightDigraph weightDigraph,int start) {
        indexMinPQ=new IndexMinPQ<>(weightDigraph.getVertexNum());
        distTo=new double[weightDigraph.getVertexNum()];
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        for (int i=0;i<weightDigraph.getVertexNum();i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        distTo[start]=0.0;
        indexMinPQ.insert(start,0.0);
        while (!indexMinPQ.isEmpty()){
            //从权重最小的顶点开始松弛，松弛的过程实际上就是更新最短路径的过程
            relax(weightDigraph, indexMinPQ.delMin());
        }
    }

    /**
     * @param weightDigraph
     * @param vertex 放松从vertex指出的所有边
     */
    private void relax(EdgeWeightDigraph weightDigraph,int vertex){
        for (DirectedEdge adjEdge:weightDigraph.adj(vertex)){
            int end=adjEdge.getEnd();
            if (distTo[end]>adjEdge.getWeight()+distTo[vertex]){
                distTo[end]=adjEdge.getWeight()+distTo[vertex];
                edgeTo[end]=adjEdge;
                if (indexMinPQ.contains(end)){
                    indexMinPQ.changeKey(end,distTo[end]);
                }else{
                    indexMinPQ.insert(end,distTo[end]);
                }
            }
        }
    }

    /**
     * @param target
     * @return 从起点到target的最短路径的总权重
     */
    public double distTo(int target){
        return distTo[target];
    }

    /**
     * @param target
     * @return 从起点是否有到target的最短路径
     */
    public boolean hasPathTo(int target){
        return distTo[target]<Double.POSITIVE_INFINITY;
    }

    /**
     * @param target
     * @return
     */
    public Stack<DirectedEdge> pathTo(int target){
        if (!hasPathTo(target)){
            return null;
        }
        Stack<DirectedEdge> path=new Stack<>();
        for (DirectedEdge edge=edgeTo[target];edge!=null;edge=edgeTo[edge.getStart()]){
            path.push(edge);
        }
        return path;
    }
}
