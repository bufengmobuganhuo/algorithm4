package chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import chapter4_Graphs.chapter4_4_ShortestPaths.BellmanFordShortestPaths;
import chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import javax.sql.rowset.FilteredRowSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/22 10:41
 * 练习4.4.30：只实现了BellmanFord算法
 */
public class EX_4_4_30 {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyEWDnc.txt";
        In in = new In(path);
        EdgeWeightDigraph weightDigraph = new EdgeWeightDigraph(in);
        EX_4_4_30 bellmanFordShortestPaths = new EX_4_4_30(weightDigraph,0);
        Stack<DirectedEdge> cycle= bellmanFordShortestPaths.cycle;
        while (!cycle.isEmpty()){
            DirectedEdge edge = cycle.pop();
            System.out.println(edge.getStart()+"->"+edge.getEnd());
        }
    }
    private double[] distTo;
    private Stack<DirectedEdge> cycle;
    private Queue<Integer> queue;
    private int cost;
    private DirectedEdge[] edgeTo;
    private boolean[] onQue;
    private boolean[] marked;
    private boolean[] onStack;
    private DirectedEdge[] edgeToCycle;

    public EX_4_4_30(EdgeWeightDigraph weightDigraph,int start) {
        distTo=new double[weightDigraph.getVertexNum()];
        for (int i=0;i<distTo.length;i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        queue=new LinkedList<>();
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        onQue=new boolean[weightDigraph.getVertexNum()];
        distTo[start]=0;
        queue.offer(start);
        onQue[start]=true;
        while (!queue.isEmpty()&&!hasNegativeCyle()){
            int vertex=queue.poll();
            onQue[vertex]=false;
            relax(weightDigraph,vertex);
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex){
        for (DirectedEdge adjEdge:weightDigraph.adj(vertex)){
            int end=adjEdge.getEnd();
            if (distTo[end]>distTo[vertex]+adjEdge.getWeight()){
                distTo[end]=distTo[vertex]+adjEdge.getWeight();
                edgeTo[end]=adjEdge;
                if (!onQue[end]){
                    onQue[end]=true;
                    queue.offer(end);
                }
            }
            if (cost++%weightDigraph.getVertexNum()==0){
                findNegativeCycle();
                if (hasNegativeCyle()){
                    return;
                }
            }
        }
    }

    private void findNegativeCycle(){
        EdgeWeightDigraph weightDigraph=new EdgeWeightDigraph(edgeTo.length);
        for (int i=0;i<weightDigraph.getVertexNum();i++){
            if (edgeTo[i]!=null){
                weightDigraph.addEdge(edgeTo[i]);
            }
        }
        marked=new boolean[weightDigraph.getVertexNum()];
        onStack=new boolean[weightDigraph.getVertexNum()];
        edgeToCycle=new DirectedEdge[weightDigraph.getVertexNum()];
        cycle=null;
        for (int i=0;i<weightDigraph.getVertexNum();i++){
            if (!marked[i]){
                dfs(weightDigraph,i);
            }
        }
    }

    private void dfs(EdgeWeightDigraph weightDigraph, int vertex){
        marked[vertex]=true;
        onStack[vertex]=true;
        for (DirectedEdge adjEdge:weightDigraph.adj(vertex)){
            int end=adjEdge.getEnd();
            if (cycle!=null){
                return;
            }else if (!marked[end]){
                edgeToCycle[end]=adjEdge;
                dfs(weightDigraph, end);
            }else if(onStack[end]){
                cycle=new Stack<>();
                DirectedEdge edge = adjEdge;
                while (edge.getStart()!=end){
                    cycle.push(edge);
                    edge=edgeToCycle[edge.getStart()];
                }
                cycle.push(edge);
                return;
            }
        }
        onStack[vertex]=false;
    }

    public boolean hasNegativeCyle(){
        return cycle != null;
    }

    public Stack<DirectedEdge> getNegativeCycle(){
        return cycle;
    }

    public double distTo(int target){
        return distTo[target];
    }

    public boolean hasPathTo(int target){
        return distTo[target] != Double.POSITIVE_INFINITY;
    }

    public Stack<DirectedEdge> pathTo(int target){
        if (!hasPathTo(target)){
            return null;
        }
        Stack<DirectedEdge> paths=new Stack<>();
        for (DirectedEdge edge = edgeTo[target];edge!=null;edge = edgeTo[edge.getStart()]){
            paths.push(edge);
        }
        return paths;
    }
}
