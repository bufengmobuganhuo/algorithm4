package chapter4_Graphs.chapter4_4_ShortestPaths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/18 11:03
 * 无环加权有向图的最长路径
 */
public class AcyclicLongestPath {
    public static void main(String[] args) {
        String path = "F:\\Algorithm4\\src\\main\\resources\\tinyEWDAG.TXT";
        In in = new In(path);
        EdgeWeightDigraph weightDigraph = new EdgeWeightDigraph(in);

        AcyclicLongestPath acyclicLongestPath = new AcyclicLongestPath(weightDigraph, 5);
        Stack<DirectedEdge> paths = acyclicLongestPath.pathTo(6);
        while (!paths.isEmpty()) {
            DirectedEdge edge = paths.pop();
            System.out.println(edge.getStart() + "->" + edge.getEnd() + " " + edge.getWeight());
        }
    }

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> indexMinPQ;

    public AcyclicLongestPath(EdgeWeightDigraph weightDigraph, int start) {
        distTo = new double[weightDigraph.getVertexNum()];
        for (int i = 0; i < weightDigraph.getVertexNum(); i++) {
            distTo[i] = Double.NEGATIVE_INFINITY;
        }
        distTo[start] = 0.0;
        edgeTo = new DirectedEdge[weightDigraph.getVertexNum()];
        indexMinPQ = new IndexMinPQ<>(weightDigraph.getVertexNum());
        TopologicalEdgeWeightedDigraph topologicalEdgeWeightedDigraph = new TopologicalEdgeWeightedDigraph(weightDigraph);
        Stack<Integer> order = topologicalEdgeWeightedDigraph.order();
        while (!order.isEmpty()) {
            relax(weightDigraph, order.pop());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex) {
        for (DirectedEdge adjEdge : weightDigraph.adj(vertex)) {
            int end = adjEdge.getEnd();
            if (distTo[end] < adjEdge.getWeight() + distTo[vertex]) {
                distTo[end] = adjEdge.getWeight() + distTo[vertex];
                edgeTo[end] = adjEdge;
                if (indexMinPQ.contains(end)) {
                    indexMinPQ.changeKey(end, distTo[end]);
                } else {
                    indexMinPQ.insert(end, distTo[end]);
                }
            }
        }
    }

    public boolean hasPathTo(int target) {
        return distTo[target] != Double.NEGATIVE_INFINITY;
    }

    /**
     * @param target
     * @return 从起点到终点的最长路径
     */
    public Stack<DirectedEdge> pathTo(int target) {
        if (!hasPathTo(target)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge edge = edgeTo[target]; edge != null; edge = edgeTo[edge.getStart()]) {
            path.push(edge);
        }
        return path;
    }

    public double distTo(int target) {
        return distTo[target];
    }

}
