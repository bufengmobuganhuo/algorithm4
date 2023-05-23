package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_5_UnionFind.UF_WeightedQuickUnion;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/5/12 11:55
 * 练习4.3.22:不一定连通的权重图的最小生成树 or 森林
 */
public class EX_4_3_22_Kruskal {
    /**
     * 最小生成树 or 森林
     */
    private Queue<Edge> mst;

    public EX_4_3_22_Kruskal(EdgeWeightedGraph weightedGraph) {
        this.mst = new LinkedList<>();
        PriorityQueue<Edge> priorityQueue=new PriorityQueue<>();
        UF_WeightedQuickUnion quickUnion=new UF_WeightedQuickUnion(weightedGraph.getVertexNum());
        //一开始有vertexNum个森林
        for (Edge edge:weightedGraph.edges()){
            priorityQueue.add(edge);
        }
        //如果是非连通图，则最小生成森林边数>vertexNum-1
        while (!priorityQueue.isEmpty()&&mst.size()<weightedGraph.getVertexNum()-1){
            Edge edge=priorityQueue.poll();
            int vertex1=edge.either();
            int vertex2=edge.other(vertex1);
            //如果已经相连，则失效
            if (quickUnion.connected(vertex1,vertex2)){
                continue;
            }
            //将两个顶点所在的连通成一颗树，有边edge，则原图中肯定是连通的
            quickUnion.union(vertex1,vertex2);
            mst.offer(edge);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
}
