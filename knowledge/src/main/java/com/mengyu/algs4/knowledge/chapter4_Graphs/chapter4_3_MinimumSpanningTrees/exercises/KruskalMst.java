package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/7 11:36 上午
 * TODO
 */
public class KruskalMst {

    private Queue<Edge> mst;
    private double weight;

    public KruskalMst(EdgeWeightedGraph weightedGraph) {
        mst=new LinkedList<>();
        PriorityQueue<Edge> priorityQueue=new PriorityQueue<>();
        WeightedQuickUnion weightedQuickUnion=new WeightedQuickUnion(weightedGraph.getVertexNum());
        for (Edge edge:weightedGraph.edges()){
            priorityQueue.offer(edge);
        }
        while(!priorityQueue.isEmpty()&&mst.size()!=weightedGraph.getVertexNum()-1){
            Edge minWeightEdge=priorityQueue.poll();
            int vertex1=minWeightEdge.either();
            int vertex2=minWeightEdge.other(vertex1);
            if (weightedQuickUnion.connected(vertex1,vertex2)){
                continue;
            }
            weightedQuickUnion.connect(vertex1,vertex2);
            mst.offer(minWeightEdge);
            weight+=minWeightEdge.getWeight();
        }
    }
}
