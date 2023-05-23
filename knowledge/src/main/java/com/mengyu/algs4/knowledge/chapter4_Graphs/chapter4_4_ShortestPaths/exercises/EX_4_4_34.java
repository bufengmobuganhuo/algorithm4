package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/26 11:04
 * 使用改进的Dijikstra
 */
public class EX_4_4_34 {
    public static void main(String[] args) {
        String path = "F:\\Algorithm4\\src\\main\\resources\\tinyEWG.txt";
        In in = new In(path);
        EdgeWeightDigraph weightDigraph = new EdgeWeightDigraph(in);
        EX_4_4_34 ex_4_4_34 = new EX_4_4_34(weightDigraph, 0);
        Stack<DirectedEdge> paths = ex_4_4_34.pathTo(6);
        while (!paths.isEmpty()) {
            DirectedEdge edge = paths.pop();
            System.out.println(edge.getStart() + "->" + edge.getEnd() + " " + edge.getWeight());
        }
    }

    private IndexMinPQ<Double> indexMinPQ;
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public EX_4_4_34(EdgeWeightDigraph weightDigraph, int start) {
        indexMinPQ = new IndexMinPQ<>(weightDigraph.getVertexNum());
        edgeTo = new DirectedEdge[weightDigraph.getVertexNum()];
        distTo = new double[weightDigraph.getVertexNum()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[start] = 0.0;
        edgeTo[start] = new DirectedEdge(-1, 0, 0.0);
        indexMinPQ.insert(start, 0.0);
        while (!indexMinPQ.isEmpty()) {
            relax(weightDigraph, indexMinPQ.delMin());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex) {
        for (DirectedEdge edge : weightDigraph.adj(vertex)) {
            int end = edge.getEnd();
            //一方面保证最短路径，另一方面保证权重是递增的
            if (distTo[end] > distTo[vertex] + edge.getWeight() && edgeTo[vertex].getWeight() < edge.getWeight()) {
                distTo[end] = distTo[vertex] + edge.getWeight();
                edgeTo[end] = edge;
                if (indexMinPQ.contains(end)) {
                    indexMinPQ.changeKey(end, distTo[end]);
                } else {
                    indexMinPQ.insert(end, distTo[end]);
                }
            }
        }
    }

    public boolean hasPathTo(int target) {
        return distTo[target] != Double.POSITIVE_INFINITY;
    }

    public Stack<DirectedEdge> pathTo(int target) {
        if (!hasPathTo(target)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge edge = edgeTo[target]; edge.getStart() != -1; edge = edgeTo[edge.getStart()]) {
            path.push(edge);
        }
        return path;
    }
}
