package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/11/20 上午10:45
 * TODO
 */
public class KruskalMST2 {
    public static void main(String[] args) {
        /**
         * 0—7 0.16
         * 1—7 0.19
         * 0—2 0.26
         * 5—7 0.28
         * 1—3 0.29
         * 4—5 0.35
         * 6—2 0.40
         */
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyEWG.txt";
        In in=new In(path);
        EdgeWeightedGraph weightedGraph=new EdgeWeightedGraph(in);

        KruskalMST kruskalMST=new KruskalMST(weightedGraph);
        Iterator<Edge> edges=kruskalMST.edges().iterator();
        while (edges.hasNext()){
            System.out.println(edges.next());
        }
    }
    private double weight;
    private int[] connectedIds;
    private int[] connectedWeights;
    private PriorityQueue<Edge> priorityQueue;
    private Queue<Edge> mst;

    public KruskalMST2(EdgeWeightedGraph weightedGraph) {
        connectedIds = new int[weightedGraph.getVertexNum()];
        connectedWeights = new int[weightedGraph.getVertexNum()];
        priorityQueue = new PriorityQueue<>();
        mst = new LinkedList<>();
        for (int i = 0; i < weightedGraph.getVertexNum(); i++) {
            connectedWeights[i] = 1;
            connectedIds[i] = i;
        }
        for (Edge edge : weightedGraph.edges()) {
            priorityQueue.offer(edge);
        }
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int vertex1 = edge.either();
            int vertex2 = edge.other(vertex1);
            if (connected(vertex1, vertex2)) {
                continue;
            }
            connect(vertex1, vertex2);
            mst.offer(edge);
            weight += edge.getWeight();
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }


    private void connect(int vertex1, int vertex2) {
        int root1 = find(vertex1);
        int root2 = find(vertex2);
        if (root1 == root2) {
            return;
        }
        if (connectedWeights[root1] > connectedWeights[root2]) {
            connectedWeights[root1] += connectedWeights[root2];
            connectedIds[root2] = root1;
        } else {
            connectedIds[root2] += connectedIds[root1];
            connectedIds[root1] = root2;
        }
    }


    private boolean connected(int vertex1, int vertex2) {
        return find(vertex1) == find(vertex2);
    }

    private int find(int vertex) {
        while (vertex != connectedIds[vertex]) {
            // 路径压缩
            connectedIds[vertex] = connectedIds[connectedIds[vertex]];
            vertex = connectedIds[vertex];
        }
        return vertex;
    }
}
