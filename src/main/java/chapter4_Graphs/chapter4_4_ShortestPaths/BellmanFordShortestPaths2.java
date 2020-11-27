package chapter4_Graphs.chapter4_4_ShortestPaths;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/23 上午10:44
 * TODO
 */
public class BellmanFordShortestPaths2 {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyEWDnc.txt";
        In in = new In(path);
        EdgeWeightDigraph weightDigraph = new EdgeWeightDigraph(in);
        BellmanFordShortestPaths2 bellmanFordShortestPaths = new BellmanFordShortestPaths2(weightDigraph, 0);
        Stack<DirectedEdge> cycle = bellmanFordShortestPaths.pathTo(3);
        while (!cycle.isEmpty()) {
            DirectedEdge edge = cycle.pop();
            System.out.println(edge.getStart() + "->" + edge.getEnd());
        }
    }
    private Queue<Integer> queue;
    private boolean[] onQue;
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private final int start;
    private Stack<DirectedEdge> cycle;
    private int cost;

    public BellmanFordShortestPaths2(EdgeWeightDigraph digraph, int start) {
        this.start = start;
        queue = new LinkedList<>();
        onQue = new boolean[digraph.getVertexNum()];
        edgeTo = new DirectedEdge[digraph.getVertexNum()];
        distTo = new double[digraph.getVertexNum()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[start] = 0.0;
        queue.offer(start);
        onQue[start] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int vertex = queue.poll();
            onQue[vertex] = false;
            relax(digraph, vertex);
        }
    }

    private void relax(EdgeWeightDigraph digraph, int vertex) {
        for (DirectedEdge edge : digraph.adj(vertex)) {
            int adjVertex = edge.getEnd();
            if (distTo[adjVertex] > distTo[vertex] + edge.getWeight()) {
                distTo[adjVertex] = distTo[vertex] + edge.getWeight();
                edgeTo[adjVertex] = edge;
                if (!onQue[adjVertex]) {
                    queue.offer(adjVertex);
                    onQue[adjVertex] = true;
                }
            }
            if (++cost % digraph.getVertexNum() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {
                    return;
                }
            }
        }
    }

    private void findNegativeCycle() {
        EdgeWeightDigraph digraph = new EdgeWeightDigraph(edgeTo.length);
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (edgeTo[i] != null) {
                digraph.addEdge(edgeTo[i]);
            }
        }
        CycleEdgeWeightDigraph2 cycleEdgeWeightDigraph2 = new CycleEdgeWeightDigraph2(digraph);
        cycle = cycleEdgeWeightDigraph2.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public boolean hasPathTo(int target) {
        return edgeTo[target] != null;
    }

    public Stack<DirectedEdge> pathTo(int target) {
        Stack<DirectedEdge> path = new Stack<>();
        if (!hasPathTo(target)) {
            return path;
        }
        for (DirectedEdge tmp = edgeTo[target]; tmp != null; tmp = edgeTo[tmp.getStart()]) {
            path.push(tmp);
        }
        return path;
    }
}
