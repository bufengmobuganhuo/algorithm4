package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees;

import com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_5_UnionFind.UF_WeightedQuickUnion;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/5/11 10:56
 * 最小生成树的Kruska算法
 */
public class KruskalMST {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyEWG.txt";
        In in = new In(path);
        EdgeWeightedGraph weightedGraph = new EdgeWeightedGraph(in);

        KruskalMST kruskalMST = new KruskalMST(weightedGraph);
        Iterator<Edge> edges = kruskalMST.edges().iterator();
        while (edges.hasNext()) {
            System.out.println(edges.next());
        }
        System.out.println((kruskalMST.lazyWeight() == kruskalMST.nonLazyWeight()) + ":" + kruskalMST.lazyWeight() + "-" + kruskalMST.nonLazyWeight());
    }

    private Queue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph weightedGraph) {
        mst = new LinkedList<>();
        //用于判断是否有环，以及连接两棵树
        UF_WeightedQuickUnion unionFind = new UF_WeightedQuickUnion(weightedGraph.getVertexNum());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        //把所有边加入优先队列
        for (Edge edge : weightedGraph.edges()) {
            priorityQueue.add(edge);
        }
        while (!priorityQueue.isEmpty() && mst.size() != weightedGraph.getVertexNum() - 1) {
            //从优先队列中取出最小权重边
            Edge edge = priorityQueue.poll();
            int vertex1 = edge.either();
            int vertex2 = edge.other(vertex1);
            //如果两个顶点已经用别的边相连，如果再相连则会出现环
            if (unionFind.connected(vertex1, vertex2)) {
                continue;
            }
            //将两颗树相连
            unionFind.union(vertex1, vertex2);
            mst.offer(edge);
            weight += edge.getWeight();
        }
    }

    /**
     * @return 获取最小生成树的所有边
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * @return 延时获取最小生成树的总权重
     */
    public double lazyWeight() {
        double weight = 0;
        for (Edge edge : mst) {
            weight += edge.getWeight();
        }
        return weight;
    }

    /**
     * @return 即时获取最小生成树总权重
     */
    public double nonLazyWeight() {
        return weight;
    }

}
