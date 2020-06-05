package chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/21 10:43
 * TODO
 */
public class EX_4_4_24 {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyEWDAG.TXT";
        In in=new In(path);

        EdgeWeightDigraph weightDigraph=new EdgeWeightDigraph(in);
        int[] startVertexes={5,3};
        EX_4_4_24 ex_4_4_24=new EX_4_4_24(weightDigraph,startVertexes);
        Stack<DirectedEdge> paths=ex_4_4_24.pathTo(6);
        while (!paths.isEmpty()){
            DirectedEdge edge = paths.pop();
            System.out.println(edge.getStart()+"->"+edge.getEnd());
        }
    }
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> indexMinPQ;

    public EX_4_4_24(EdgeWeightDigraph weightDigraph,int[] startVertexes) {
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        indexMinPQ=new IndexMinPQ<>(weightDigraph.getVertexNum());
        distTo= new double[weightDigraph.getVertexNum()];
        for (int i=0;i<distTo.length;i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        for (int i=0;i<startVertexes.length;i++){
            distTo[startVertexes[i]]=0.0;
            indexMinPQ.insert(startVertexes[i],0.0);
        }
        while (!indexMinPQ.isEmpty()){
            relax(weightDigraph, indexMinPQ.delMin());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex){
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

    public boolean hasPathTo(int target){
        return distTo[target]!=Double.POSITIVE_INFINITY;
    }

    public Stack<DirectedEdge> pathTo(int target){
        if (!hasPathTo(target)){
            return null;
        }
        Stack<DirectedEdge> path=new Stack<>();
        for (DirectedEdge edge = edgeTo[target];edge!=null;edge=edgeTo[edge.getStart()]){
            path.push(edge);
        }
        return path;
    }

}
