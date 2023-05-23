package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/25 11:25
 * 练习4.4.33：可以上下左右移动（无向图）
 */
public class EX_4_4_33_1 {
    public static void main(String[] args) {
        int[][] matrix={
                {1,3,7,6,4},
                {5,8,7,2,1},
                {0,7,4,8,9},
                {10,5,2,7,5},
                {11,4,4,3,0},
        };
        EX_4_4_33_1 ex_4_4_33_1=new EX_4_4_33_1(matrix);
        Stack<DirectedEdge> paths= ex_4_4_33_1.pathTo(24);
        double weight=0;
        while (!paths.isEmpty()){
            DirectedEdge edge = paths.pop();
            int start=edge.getStart();
            int i=start/matrix.length;
            int j=start%matrix.length;
            weight+=matrix[i][j];
            System.out.println(edge.getStart()+"->"+edge.getEnd()+":"+weight);
        }
    }
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> indexMinPQ;

    public EX_4_4_33_1(int[][] matrix) {
        int N=matrix.length;
        EdgeWeightDigraph weightedGraph=new EdgeWeightDigraph(N*N);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (j<matrix.length-1){
                    int vertex1=N*i+j;
                    int vertex2=N*i+j+1;
                    DirectedEdge edge=new DirectedEdge(vertex1,vertex2,matrix[i][j]+matrix[i][j+1]);
                    weightedGraph.addEdge(edge);
                    DirectedEdge edge1=new DirectedEdge(vertex2,vertex1,matrix[i][j]+matrix[i][j+1]);
                    weightedGraph.addEdge(edge1);
                }
                if (i>0){
                    int vertex1=i*N+j;
                    int vertex2=(i-1)*N+j;
                    DirectedEdge edge=new DirectedEdge(vertex1,vertex2,matrix[i][j]+matrix[i-1][j]);
                    weightedGraph.addEdge(edge);
                    DirectedEdge edge1=new DirectedEdge(vertex2,vertex1,matrix[i][j]+matrix[i-1][j]);
                    weightedGraph.addEdge(edge1);
                }
            }
        }
        DijkstraSP(weightedGraph,0);
    }

    private void DijkstraSP(EdgeWeightDigraph weightDigraph,int start){
        distTo=new double[weightDigraph.getVertexNum()];
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        indexMinPQ=new IndexMinPQ<>(weightDigraph.getVertexNum());
        for (int i=0;i<distTo.length;i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        distTo[start]=0;
        indexMinPQ.insert(start,0.0);
        while (!indexMinPQ.isEmpty()){
            relax(weightDigraph,indexMinPQ.delMin());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph,int vertex){
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
