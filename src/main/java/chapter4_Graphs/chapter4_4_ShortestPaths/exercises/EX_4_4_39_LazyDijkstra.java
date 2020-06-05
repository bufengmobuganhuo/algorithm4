package chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.In;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/26 15:09
 * 延时版本
 */
public class EX_4_4_39_LazyDijkstra {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyEWDAG.TXT";
        In in=new In(path);
        EdgeWeightDigraph weightDigraph=new EdgeWeightDigraph(in);
        EX_4_4_39_LazyDijkstra lazyDijkstra=new EX_4_4_39_LazyDijkstra(weightDigraph,5);
        Stack<DirectedEdge> paths= lazyDijkstra.pathTo(6);
        while (!paths.isEmpty()){
            DirectedEdge edge = paths.pop();
            System.out.println(edge.getStart()+"->"+edge.getEnd()+" "+edge.getWeight());
        }
    }
    private java.util.PriorityQueue<DirectedEdge> priorityQueue;
    private boolean[] marked;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public EX_4_4_39_LazyDijkstra(EdgeWeightDigraph weightDigraph,int start) {
        priorityQueue=new PriorityQueue<>(new ByDistanceFromSource());
        marked=new boolean[weightDigraph.getVertexNum()];
        distTo=new double[weightDigraph.getVertexNum()];
        edgeTo=new DirectedEdge[weightDigraph.getVertexNum()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        distTo[start]=0.0;
        relax(weightDigraph, start);
        while(!priorityQueue.isEmpty()){
            DirectedEdge edge = priorityQueue.poll();
            int startVertex=edge.getStart();
            int endVertex=edge.getEnd();
            //如果前面已经放松过，说明endVertex先出优先队列，那么权重肯定比现在这个小
            if (!marked[endVertex]){
                relax(weightDigraph, endVertex);
            }
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph,int vertex){
        marked[vertex]=true;
        for (DirectedEdge edge:weightDigraph.adj(vertex)){
            int end=edge.getEnd();
            if (distTo[end]>distTo[vertex]+edge.getWeight()){
                distTo[end]=distTo[vertex]+edge.getWeight();
                edgeTo[end]=edge;
                priorityQueue.offer(edge);
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

    private class ByDistanceFromSource implements Comparator<DirectedEdge>{

        @Override
        public int compare(DirectedEdge o1, DirectedEdge o2) {
            double dist1=distTo[o1.getStart()]+o1.getWeight();
            double dist2=distTo[o2.getStart()]+o2.getWeight();
            return Double.compare(dist1,dist2);
        }
    }
}
