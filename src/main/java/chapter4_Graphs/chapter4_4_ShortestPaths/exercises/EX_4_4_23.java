package chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.PatriciaSET;

import java.lang.annotation.Target;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/21 10:15
 * 练习4.4.23
 */
public class EX_4_4_23 {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyEWDAG.TXT";
        In in=new In(path);

        EdgeWeightDigraph weightDigraph=new EdgeWeightDigraph(in);
        EX_4_4_23 ex_4_4_23=new EX_4_4_23(weightDigraph,5,6);
        Stack<DirectedEdge> paths=ex_4_4_23.path();
        while (!paths.isEmpty()){
            DirectedEdge edge = paths.pop();
            System.out.println(edge.getStart()+"->"+edge.getEnd());

        }
    }
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> indexMinPQ;
    private final int target;

    public EX_4_4_23(EdgeWeightDigraph weightDigraph,int start,int end) {
        target=end;
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        indexMinPQ=new IndexMinPQ<>(weightDigraph.getVertexNum());
        distTo=new double[weightDigraph.getVertexNum()];
        for (int i=0;i<distTo.length;i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        distTo[start]=0.0;
        indexMinPQ.insert(start,0.0);
        while (!indexMinPQ.isEmpty()){
            int vertex=indexMinPQ.delMin();
            //找到目标顶点后，则不继续向下找
            if (vertex== end){
                break;
            }
            relax(weightDigraph, vertex);
        }
    }

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

    public Stack<DirectedEdge> path(){
        if (distTo[target]==Double.POSITIVE_INFINITY){
            return null;
        }
        Stack<DirectedEdge> path=new Stack<>();
        for (DirectedEdge edge = edgeTo[target];edge!=null;edge=edgeTo[edge.getStart()]){
            path.push(edge);
        }
        return path;
    }

}
