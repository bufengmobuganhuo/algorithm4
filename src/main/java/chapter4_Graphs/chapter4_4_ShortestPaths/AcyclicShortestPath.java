package chapter4_Graphs.chapter4_4_ShortestPaths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/14 17:11
 * 基于拓扑排序查找无环加权有向图的最短路径
 */
public class AcyclicShortestPath {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyEWDAG.TXT";
        In in=new In(path);
        EdgeWeightDigraph weightDigraph=new EdgeWeightDigraph(in);

        AcyclicShortestPath acyclicShortestPath =new AcyclicShortestPath(weightDigraph,5);
        Stack<DirectedEdge> paths= acyclicShortestPath.pathTo(6);
        while (!paths.isEmpty()){
            DirectedEdge edge = paths.pop();
            System.out.println(edge.getStart()+"->"+edge.getEnd()+" "+edge.getWeight());
        }
    }
    private IndexMinPQ<Double> indexMinPQ;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicShortestPath(EdgeWeightDigraph weightDigraph, int start) {
        indexMinPQ=new IndexMinPQ<>(weightDigraph.getVertexNum());
        distTo=new double[weightDigraph.getVertexNum()];
        for (int i=0;i<distTo.length;i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        distTo[0]=0.0;
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        TopologicalEdgeWeightedDigraph topological=new TopologicalEdgeWeightedDigraph(weightDigraph);
        Stack<Integer> order=topological.order();
        distTo[start]=0.0;
        while (!order.isEmpty()){
            relax(weightDigraph, order.pop());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph,int vertex){
        for (DirectedEdge adjEdge:weightDigraph.adj(vertex)){
            int end=adjEdge.getEnd();
            if (distTo[end]>adjEdge.getWeight()+distTo[vertex]){
                distTo[end]=distTo[vertex]+adjEdge.getWeight();
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
     * @return 从起点到target的距离
     */
    public double distTo(int target){
        return distTo[target];
    }

    /**
     * @param target
     * @return 是否存在从起点到target的路径
     */
    public boolean hasPathTo(int target){
        return distTo[target]<Double.POSITIVE_INFINITY;
    }

    /**
     * @param target
     * @return 从起点到target的路径
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
