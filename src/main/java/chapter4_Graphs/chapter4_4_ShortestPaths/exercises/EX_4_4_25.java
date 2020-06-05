package chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * @author zhangyu
 * 2020/5/22 10:13
 * 练习4.4.25
 */
public class EX_4_4_25 {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> indexMinPQ;
    private final int[] targets;

    public EX_4_4_25(EdgeWeightDigraph weightDigraph,int[] starts,int[] targets) {
        this.targets=targets;
        distTo=new double[weightDigraph.getVertexNum()];
        for (int i=0;i<distTo.length;i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        indexMinPQ=new IndexMinPQ<>(weightDigraph.getVertexNum());
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        for (int i=0;i<starts.length;i++){
            distTo[starts[i]]=0;
            indexMinPQ.insert(starts[i],0.0);
        }
        while (!indexMinPQ.isEmpty()){
            relax(weightDigraph, indexMinPQ.delMin());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex){
        for (DirectedEdge adjEdge:weightDigraph.adj(vertex)){
            int end=adjEdge.getEnd();
            if (distTo[end]>distTo[vertex]+adjEdge.getWeight()){
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
}
