package chapter4_Graphs.chapter4_4_ShortestPaths;

import chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.security.spec.ECGenParameterSpec;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/13 14:30
 * 最短路径
 */
public class ShortestPath {
    /**
     * distTo[vertex]：从起点start到顶点vertex的距离
     */
    private double[] distTo;
    /**
     * edgeTo[vertex]:代表一条从 vertex0 ---> vertex的边
     */
    private DirectedEdge[] edgeTo;
    private final int start;

    public ShortestPath(int start) {
        this.start = start;
    }

    /**
     * @param vertex1ToVertex2Edge
     * 放松 vertex1 --> vertex2的边
     */
    private void relax(DirectedEdge vertex1ToVertex2Edge){
        int vertex1=vertex1ToVertex2Edge.getStart();
        int vertex2=vertex1ToVertex2Edge.getEnd();
        //如果start -> vertex2的权重 > start -> vertex1 -> vertex2,则放松
        if (distTo[vertex2]>distTo[vertex1]+vertex1ToVertex2Edge.getWeight()){
            distTo[vertex2]=distTo[vertex1]+vertex1ToVertex2Edge.getWeight();
            edgeTo[vertex2]=vertex1ToVertex2Edge;
        }
    }

    /**
     * @param weightedDigraph
     * @param vertex 放松所有从顶点指出的边
     */
    private void relax(EdgeWeightDigraph weightedDigraph,int vertex){
        for (DirectedEdge edge:weightedDigraph.adj(vertex)){
            int vertex1=edge.getStart();
            int vertex2= edge.getEnd();
            if (distTo[vertex2]>distTo[vertex1]+edge.getWeight()){
                distTo[vertex2]=distTo[vertex1]+edge.getWeight();
                edgeTo[vertex2]=edge;
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
