package chapter4_Graphs.chapter4_3_MinimumSpanningTrees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/11/20 上午10:20
 * TODO
 */
public class PrimMST2 {
    public static void main(String[] args) {
        /**
         * 1—7 0.19
         * 0—2 0.26
         * 1—3 0.29
         * 4—5 0.35
         * 5—7 0.28
         * 6—2 0.40
         * 0—7 0.16
         */
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyEWG.txt";
        In in = new In(path);
        EdgeWeightedGraph weightedGraph = new EdgeWeightedGraph(in);
        PrimMST2 primMST = new PrimMST2(weightedGraph);
        Iterator<Edge> iterator = primMST.edges().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
    private IndexMinPQ<Double> indexMinPQ;
    private Edge[] edgeTo;
    private double[] distTo;
    private double weight;
    private boolean[] marked;

    public PrimMST2(EdgeWeightedGraph weightedGraph) {
        indexMinPQ = new IndexMinPQ<>(weightedGraph.getVertexNum());
        edgeTo = new Edge[weightedGraph.getVertexNum()];
        distTo = new double[weightedGraph.getVertexNum()];
        marked = new boolean[weightedGraph.getVertexNum()];
        Arrays.fill(distTo, Double.MAX_VALUE);
        distTo[0] = 0.0;
        indexMinPQ.insert(0, 0.0);
        while (!indexMinPQ.isEmpty()) {
            visit(weightedGraph, indexMinPQ.delMin());
        }
    }

    private void visit(EdgeWeightedGraph weightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge edge : weightedGraph.adj(vertex)) {
            int adjVertex = edge.other(vertex);
            if (marked[adjVertex]) {
                continue;
            }
            if (distTo[adjVertex] > edge.getWeight()) {
                weight -= distTo[adjVertex] == Double.MAX_VALUE ? 0 : distTo[adjVertex];
                distTo[adjVertex] = edge.getWeight();
                edgeTo[adjVertex] = edge;
                weight += distTo[adjVertex];
                if (indexMinPQ.contains(adjVertex)) {
                    indexMinPQ.decreaseKey(adjVertex, edge.getWeight());
                } else {
                    indexMinPQ.insert(adjVertex, edge.getWeight());
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> edges = new LinkedList<>();
        for (Edge edge : edgeTo) {
            if (edge != null) {
                edges.offer(edge);
            }
        }
        return edges;
    }

}
