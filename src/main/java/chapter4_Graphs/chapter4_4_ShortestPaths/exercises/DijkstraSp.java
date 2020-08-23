package chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/8/10 9:41 上午
 * TODO
 */
public class DijkstraSp {
    private IndexMinPQ<Double> indexMinPQ;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public DijkstraSp(EdgeWeightDigraph weightDigraph, int start) {
        distTo = new double[weightDigraph.getVertexNum()];
        edgeTo = new DirectedEdge[weightDigraph.getVertexNum()];
        indexMinPQ = new IndexMinPQ<>(weightDigraph.getVertexNum());
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[start] = 0.0;
        indexMinPQ.insert(start, 0.0);
        while (!indexMinPQ.isEmpty()) {
            relax(weightDigraph, indexMinPQ.delMin());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex) {
        for (DirectedEdge adjEdge : weightDigraph.adj(vertex)) {
            int endVertex = adjEdge.getEnd();
            if (distTo[endVertex] > distTo[vertex] + adjEdge.getWeight()) {
                distTo[endVertex] = distTo[vertex] + adjEdge.getWeight();
                edgeTo[endVertex] = adjEdge;
                if (indexMinPQ.contains(endVertex)) {
                    indexMinPQ.decreaseKey(endVertex, distTo[endVertex]);
                } else {
                    indexMinPQ.insert(endVertex, distTo[endVertex]);
                }
            }
        }
    }
}
