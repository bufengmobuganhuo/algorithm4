package chapter4_Graphs.chapter4_4_ShortestPaths;

import edu.princeton.cs.algs4.BellmanFordSP;
import edu.princeton.cs.algs4.In;
import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/20 19:30
 * 求一般加权有向图（可能有环）的最短路径
 */
public class BellmanFordShortestPaths {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyEWDnc.txt";
        In in = new In(path);
        EdgeWeightDigraph weightDigraph = new EdgeWeightDigraph(in);
        BellmanFordShortestPaths bellmanFordShortestPaths = new BellmanFordShortestPaths(weightDigraph,0);
        Stack<DirectedEdge> cycle= bellmanFordShortestPaths.pathTo(3);
        while (!cycle.isEmpty()){
            DirectedEdge edge = cycle.pop();
            System.out.println(edge.getStart()+"->"+edge.getEnd());
        }
    }
    /**
     * 存储distTo[v]发生变化的顶点v
     */
    private Queue<Integer> queue;
    /**
     * 顶点是否在队列内，防止重复放入队列
     */
    private boolean[] onQue;
    /**
     * distTo[v]，从起点到v的路径长度
     */
    private double[] distTo;
    /**
     * edgeTo[v]：从某个顶点 -->  v的边
     */
    private DirectedEdge[] edgeTo;
    /**
     * 放松了cost轮
     */
    private int cost;
    /**
     * 负权重环
     */
    private Stack<DirectedEdge> cycle;

    public BellmanFordShortestPaths(EdgeWeightDigraph weightDigraph, int start) {
        queue = new LinkedList<>();
        onQue = new boolean[weightDigraph.getVertexNum()];
        distTo = new double[weightDigraph.getVertexNum()];
        for (int i=0;i<distTo.length;i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        edgeTo = new DirectedEdge[weightDigraph.getVertexNum()];
        //从起点开始放松
        queue.offer(start);
        distTo[start] = 0.0;
        onQue[start] = true;
        while (!queue.isEmpty()&&!hasNegativeCyle()){
            int vertex = queue.poll();
            onQue[vertex] = false;
            relax(weightDigraph, vertex);
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex){
        for (DirectedEdge adjEdge:weightDigraph.adj(vertex)){
            int end=adjEdge.getEnd();
            if (distTo[end]>adjEdge.getWeight()+distTo[vertex]){
                distTo[end] = adjEdge.getWeight()+distTo[vertex];
                edgeTo[end] = adjEdge;
                if (!onQue[end]){
                    queue.offer(end);
                    onQue[end] = true;
                }
            }
            //放松了vertexNum轮之后，可以尝试查找负权重环
            if (cost++%weightDigraph.getVertexNum()==0){
                findNegativeCycle();
                if (hasNegativeCyle()){
                    return;
                }
            }
        }
    }

    private void findNegativeCycle(){
        int vertexNum=edgeTo.length;
        EdgeWeightDigraph weightDigraph = new EdgeWeightDigraph(vertexNum);
        //如果放松V轮后队列不为空，则edgeTo[]一定包含一个总权重为负的环
        for (int i=0;i<vertexNum;i++){
            if (edgeTo[i]!=null){
                weightDigraph.addEdge(edgeTo[i]);
            }
        }
        CycleEdgeWeightDigraph cycleEdgeWeightDigraph = new CycleEdgeWeightDigraph(weightDigraph);
        cycle = cycleEdgeWeightDigraph.getCycle();
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
