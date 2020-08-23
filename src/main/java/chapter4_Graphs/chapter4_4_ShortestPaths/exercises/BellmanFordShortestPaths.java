package chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/10 10:51 上午
 * TODO
 */
public class BellmanFordShortestPaths {
    private boolean[] onQueue;
    private int cost;
    private Stack<DirectedEdge> cycle;
    private Queue<Integer> queue;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public BellmanFordShortestPaths(EdgeWeightDigraph weightDigraph, int start) {
        onQueue = new boolean[weightDigraph.getVertexNum()];
        queue = new LinkedList<>();
        distTo = new double[weightDigraph.getVertexNum()];
        edgeTo = new DirectedEdge[weightDigraph.getVertexNum()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[start] = 0.0;
        queue.offer(start);
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int vertex = queue.poll();
            onQueue[vertex] = false;
            relax(weightDigraph, vertex);
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph, int vertex) {
        for (DirectedEdge adjEdge : weightDigraph.adj(vertex)) {
            int endVertex = adjEdge.getEnd();
            if (distTo[endVertex] > distTo[vertex] + adjEdge.getWeight()) {
                distTo[endVertex] = distTo[vertex] + adjEdge.getWeight();
                edgeTo[endVertex] = adjEdge;
                if (!onQueue[endVertex]) {
                    onQueue[endVertex] = true;
                    queue.offer(endVertex);
                }
            }
            if (++cost % weightDigraph.getVertexNum() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {

                }
            }
        }
    }

    private void findNegativeCycle() {
        int vertexNum = edgeTo.length;
        EdgeWeightDigraph weightDigraph = new EdgeWeightDigraph(vertexNum);
        for (DirectedEdge directedEdge : edgeTo) {
            if (directedEdge != null) {
                DirectedEdge edge = new DirectedEdge(directedEdge.getStart(), directedEdge.getEnd(), directedEdge.getWeight());
                weightDigraph.addEdge(edge);
            }
        }
        CycleEdgeWightedDigraph cycleEdgeWightedDigraph = new CycleEdgeWightedDigraph(weightDigraph);
        cycle = (Stack<DirectedEdge>) cycleEdgeWightedDigraph.cycle();
    }

    private boolean hasNegativeCycle() {
        return cycle != null;
    }
}
