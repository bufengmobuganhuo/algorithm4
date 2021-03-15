package chapter4_Graphs.chapter4_3_MinimumSpanningTrees;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/11/20 上午9:42
 * TODO
 */
public class LazyPrimMST2 {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyEWG.txt";
        In in = new In(path);
        EdgeWeightedGraph weightedGraph = new EdgeWeightedGraph(in);
        LazyPrimMST2 lazyPrimMST = new LazyPrimMST2(weightedGraph);
        Iterable<Edge> edges = lazyPrimMST.edges();
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        System.out.println(lazyPrimMST.weight);
    }

    private PriorityQueue<Edge> priorityQueue;
    private boolean[] marked;
    private double weight;
    private Queue<Edge> mst;

    public LazyPrimMST2(EdgeWeightedGraph weightedGraph) {
        priorityQueue = new PriorityQueue<>(weightedGraph.getVertexNum());
        marked = new boolean[weightedGraph.getVertexNum()];
        mst = new LinkedList<>();
        visit(weightedGraph, 0);
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int vertex1 = edge.either();
            int vertex2 = edge.other(vertex1);
            if (marked[vertex1] && marked[vertex2]) {
                continue;
            }
            mst.offer(edge);
            weight += edge.getWeight();
            if (!marked[vertex1]) {
                visit(weightedGraph, vertex1);
            }
            if (!marked[vertex2]) {
                visit(weightedGraph, vertex2);
            }
        }
    }

    private void visit(EdgeWeightedGraph weightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge edge : weightedGraph.adj(vertex)) {
            int adjVertex = edge.other(vertex);
            if (!marked[adjVertex]) {
                priorityQueue.offer(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
