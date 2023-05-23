package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/25 14:21
 * TODO
 */
public class EX_4_4_33_2 {
    public static void main(String[] args) {
        int[][] matrix={
                {1,3,7,6,4},
                {5,8,7,2,1},
                {0,7,4,8,9},
                {10,5,2,7,5},
                {11,4,4,3,0},
        };
        EX_4_4_33_2 ex_4_4_33_2=new EX_4_4_33_2(matrix);
        Stack<DirectedEdge> paths= ex_4_4_33_2.pathTo(24);
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
    private Stack<Integer> reversePost;
    private boolean[] marked;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public EX_4_4_33_2(int[][] matrix) {
        int N=matrix.length;
        EdgeWeightDigraph weightDigraph=new EdgeWeightDigraph(N*N);
        for (int i=0;i<N;i++){
            for (int j = 0; j < N; j++) {
                int vertex=i*N+j;
                int vertexRight=i*N+j+1;
                int vertexDown=(i+1)*N+j;
                if (j<N-1){
                    DirectedEdge directedEdge=new DirectedEdge(vertex,vertexRight,matrix[i][j]+matrix[i][j+1]);
                    weightDigraph.addEdge(directedEdge);
                }
                if (i<N-1){
                    DirectedEdge directedEdge=new DirectedEdge(vertex,vertexDown,matrix[i][j]+matrix[i+1][j]);
                    weightDigraph.addEdge(directedEdge);
                }
            }
        }

        topological(weightDigraph);
        findShortestPath(weightDigraph);
    }

    private void findShortestPath(EdgeWeightDigraph weightDigraph){
        distTo=new double[weightDigraph.getVertexNum()];
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        distTo[0]=0;
        while (!reversePost.isEmpty()){
            int vertex=reversePost.pop();
            relax(weightDigraph,vertex);
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph,int vertex){
        for (DirectedEdge adjEdge:weightDigraph.adj(vertex)){
            int end=adjEdge.getEnd();
            if (distTo[end]>distTo[vertex]+adjEdge.getWeight()){
                distTo[end]=distTo[vertex]+adjEdge.getWeight();
                edgeTo[end]=adjEdge;
            }
        }
    }

    private void topological(EdgeWeightDigraph weightDigraph){
        reversePost=new Stack<>();
        marked=new boolean[weightDigraph.getVertexNum()];
        for (int i=0;i<weightDigraph.getVertexNum();i++){
            if (!marked[i]){
                dfs(weightDigraph,i);
            }
        }
    }

    private void dfs(EdgeWeightDigraph weightDigraph,int vertex){
        marked[vertex]=true;
        for (DirectedEdge adjEdge:weightDigraph.adj(vertex)){
            int end=adjEdge.getEnd();
            if (!marked[end]){
                dfs(weightDigraph,end);
            }
        }
        reversePost.push(vertex);
    }

    public boolean hasPathTo(int target){
        return distTo[target]!=Double.POSITIVE_INFINITY;
    }

    public Stack<DirectedEdge> pathTo(int target){
        if (!hasPathTo(target)){
            return null;
        }
        Stack<DirectedEdge> path=new Stack<>();
        for(DirectedEdge edge = edgeTo[target];edge!=null;edge=edgeTo[edge.getStart()]){
            path.push(edge);
        }
        return path;
    }
}
